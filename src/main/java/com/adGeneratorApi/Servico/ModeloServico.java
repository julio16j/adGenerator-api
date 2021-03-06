package com.adGeneratorApi.Servico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Dominio.DTO.ModeloDTO;
import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Entidade.Titulo;
import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;
import com.adGeneratorApi.Dominio.Enum.StatusEnum;
import com.adGeneratorApi.Repositorio.ModeloRepositorio;

@Component
public class ModeloServico {
	
	@Autowired
	ModeloRepositorio repositorio;
	
	@Autowired
	ProdutoServico produtoServico;
	
	@Autowired
	TituloServico tituloServico;
	
	@Autowired
	DescricaoValorServico descricaoValorServico;
	
	@Autowired
	CartaoServico cartaoServico;
	
	@Autowired
	VariacaoModeloServico variacaoModeloServico;
	
	public List<Modelo> listarTodos () {
		return repositorio.findAll();
	}
	
	public Modelo cadastrarModelo (ModeloDTO dto) {
		Modelo novoModelo = new Modelo(dto);
		Modelo modeloSalvo = repositorio.save(novoModelo);
		return modeloSalvo;
		
	}

	public Modelo encontrarPorId(String modeloId) {
		Optional<Modelo> modeloEncontrado = repositorio.findById(modeloId);
		if (modeloEncontrado.isEmpty()) throw new RuntimeException("Modelo não encontrado");
		return modeloEncontrado.get();
	}
	
	public void delete(String modeloId) {
		repositorio.deleteById(modeloId);
		
	}

	public List<Modelo> encontrarPorNome(String nome) {
		return repositorio.filtrarPorNome(nome);
	}

	public List<VariacaoModelo> detalharModelos() {
		List<VariacaoModelo> variacoes = new ArrayList<>();
		List<Produto> produtos = produtoServico.listarTodos();
		List<Modelo> modelos = listarTodos();
		List<DescricaoValor> descricoes = descricaoValorServico.listarTodos();
		List<Cartao> cartoes = cartaoServico.listarTodos();
		for (Modelo modelo: modelos) {
			Integer numeroDescricoes = modelo.getDescricoes().size();
			Integer numeroCartoes = modelo.getCartoes().size();
			for (Produto produto : produtos) {
				List<Titulo> titulosProduto = tituloServico.encontrarPorFiltros(null, null, produto);
				for (Titulo titulo : titulosProduto) {
					VariacaoModelo novaVariacao = new VariacaoModelo();
					int contadorDescricao = 0;
					Set<DescricaoValor> descricoesNovas = new HashSet<>();
					for (int descricaoI = 0; descricaoI < numeroDescricoes; descricaoI++) {
						descricoesNovas.add(descricoes.get(contadorDescricao));
						if (descricoes.size() -1 > contadorDescricao) contadorDescricao++;
					}
					int contadorCartao = 0;
					Set<Cartao> cartoesNovas = new HashSet<>();
					for (int cartaoI = 0; cartaoI < numeroCartoes; cartaoI++) {
						cartoesNovas.add(cartoes.get(contadorCartao));
						if (cartoes.size() -1 > contadorCartao) contadorCartao++;
					}
					novaVariacao.setTitulo(titulo);
					novaVariacao.setProduto(produto);
					novaVariacao.setCartoes(cartoesNovas);
					novaVariacao.setDescricoes(descricoesNovas);
					novaVariacao.setModelo(modelo);
					try {
						novaVariacao.setChave(variacaoModeloServico.gerarChave(novaVariacao));
						novaVariacao.setStatus(StatusEnum.Neutro);
						variacoes.add(novaVariacao);
					} catch (Exception e) {}
				}
			}
		}
		return variacoes;
	}
	
}
