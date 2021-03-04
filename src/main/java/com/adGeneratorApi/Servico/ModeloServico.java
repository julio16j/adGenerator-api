package com.adGeneratorApi.Servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Controlador.VariacaoModeloDTO;
import com.adGeneratorApi.Dominio.DTO.ModeloDTO;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Entidade.Titulo;
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

	public List<VariacaoModeloDTO> detalharModelos() {
		List<VariacaoModeloDTO> variacoes = new ArrayList<>();
		List<Produto> produtos = produtoServico.listarTodos();
		for (Produto produto : produtos) {
			variacoes.addAll(criarVariacoesProduto(produto));
		}
		return variacoes;
	}

	private List<VariacaoModeloDTO> criarVariacoesProduto(Produto produto) {
		List<Titulo> titulosProduto = tituloServico.encontrarPorFiltros(null, null, produto);
		List<DescricaoValor> descricao = descricaoValorServico.listarTodos();
		for (Titulo titulo : titulosProduto) {
			VariacaoModeloDTO novaVariacao = new VariacaoModeloDTO();
			novaVariacao.setTitulo(titulo);
			novaVariacao.setDescricoes(descricao.subList(0, 2));
		}
		return null;
	}
	
	
}
