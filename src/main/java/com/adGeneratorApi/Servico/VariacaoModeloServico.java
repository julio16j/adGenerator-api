package com.adGeneratorApi.Servico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Entidade.Titulo;
import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;
import com.adGeneratorApi.Dominio.Enum.StatusEnum;
import com.adGeneratorApi.Repositorio.VariacaoModeloRepositorio;

@Component
public class VariacaoModeloServico {
	
	@Autowired
	VariacaoModeloRepositorio repositorio;
	
	@Autowired
	ProdutoServico produtoServico;
	
	@Autowired
	TituloServico tituloServico;
	
	@Autowired
	DescricaoValorServico descricaoValorServico;
	
	@Autowired
	CartaoServico cartaoServico;
	
	@Autowired
	ModeloServico modeloServico;
	
	public Page<VariacaoModelo> listarTodosPaginado (Integer pagina, Integer tamanho, Sort sort) {
		sort = sort == null ? Sort.by("produto") : sort;
		tamanho = tamanho == null ? 5 : tamanho;
		return repositorio.listarTodosPaginado(PageRequest.of(pagina, tamanho, sort));
	}
	
	public List<VariacaoModelo> listarTodos () {
		return repositorio.findAll();
	}
	
	public String gerarChave(VariacaoModelo novaVariacao) {
		String chave = "";
		chave += novaVariacao.getModelo().getNome();
		chave += novaVariacao.getProduto().getNome();
		chave += novaVariacao.getTitulo().getDescricao();
		for (DescricaoValor descricao: novaVariacao.getDescricoes()) {
			chave += descricao.getDescricao();
		}
		for (Cartao cartao: novaVariacao.getCartoes()) {
			chave += cartao.getNome();
		}
		Optional<VariacaoModelo> variacaoEncontrada = repositorio.findById(chave);
		if (variacaoEncontrada.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chave já cadastrada");
		return chave;
	}
	
	public void gerarVariacoes () {
		List<VariacaoModelo> variacoes = new ArrayList<>();
		List<Produto> produtos = produtoServico.listarTodos();
		List<Modelo> modelos = modeloServico.listarTodos();
		List<DescricaoValor> descricoes = descricaoValorServico.listarTodos();
		List<Cartao> cartoes = cartaoServico.listarTodos();
		for (Modelo modelo: modelos) {
			Integer numeroDescricoes = modelo.getDescricoes().size();
			Integer numeroCartoes = modelo.getCartoes().size();
			for (Produto produto : produtos) {
				List<Titulo> titulosProduto = tituloServico.encontrarPorFiltros(null, null, produto);
				for (Titulo titulo : titulosProduto) {
					int contadorDescricao = 0;
					int contadorCartao = 0;
					int contadorDescricaoAuxiliar = 0;
					boolean deveContinuarDescricao = true;
					while (contadorDescricao  < descricoes.size() && deveContinuarDescricao ) {
						boolean deveContinuarCartao = true;
						contadorCartao = 0;
						while (contadorCartao < cartoes.size() && deveContinuarCartao) {
							contadorDescricaoAuxiliar = contadorDescricao;
							VariacaoModelo novaVariacao = new VariacaoModelo();
							Set<DescricaoValor> descricoesNovas = new HashSet<>();
							Set<Cartao> cartoesNovas = new HashSet<>();
							for (int descricaoI = 0; descricaoI < numeroDescricoes; descricaoI++) {
								if (descricoes.size() <= contadorDescricao) {
									deveContinuarDescricao = false;
									break;
								}
								descricoesNovas.add(descricoes.get(contadorDescricaoAuxiliar));
								contadorDescricaoAuxiliar++;
							}
							for (int cartaoI = 0; cartaoI < numeroCartoes; cartaoI++) {
								if (cartoes.size() <= contadorCartao) {
									deveContinuarCartao = false;
									break;
								}
								cartoesNovas.add(cartoes.get(contadorCartao));
								contadorCartao++;
							}
							if (!deveContinuarCartao || !deveContinuarDescricao) break;
							novaVariacao.setTitulo(titulo);
							novaVariacao.setProduto(produto);
							novaVariacao.setCartoes(cartoesNovas);
							novaVariacao.setDescricoes(descricoesNovas);
							novaVariacao.setModelo(modelo);
							try {
								novaVariacao.setChave(gerarChave(novaVariacao));
								novaVariacao.setStatus(StatusEnum.Neutro);
								variacoes.add(novaVariacao);
							} catch (Exception e) {}
						}
						contadorDescricao = contadorDescricaoAuxiliar;
					}
				}
			}
		} repositorio.saveAll(variacoes);	
	}
	
	public VariacaoModelo encontrarPorId (String chave) {
		Optional<VariacaoModelo> variacaoEncontrada = repositorio.findById(chave);
		if (variacaoEncontrada.isPresent()) {
			return variacaoEncontrada.get();
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Variacão Não Encontrada");
	}
}
