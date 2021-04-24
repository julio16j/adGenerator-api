package com.adGeneratorApi.Servico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.ContadorDTO;
import com.adGeneratorApi.Dominio.DTO.SetupVariacaoDTO;
import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Entidade.TemaCor;
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
	
	@Autowired
	TemaCorServico temaCorServico;
	
	public Page<VariacaoModelo> listarTodos (Integer pagina, Integer tamanho, Sort ordenarPor) {
		pagina = pagina == null ? 0 : pagina;
		tamanho = tamanho == null ? 5 : tamanho;
		ordenarPor = ordenarPor == null ? Sort.unsorted() : ordenarPor;
		Pageable paginavel = PageRequest.of(pagina, tamanho, ordenarPor);
		return repositorio.findAll(paginavel);
	}
	
	public String gerarChave(VariacaoModelo novaVariacao) {
		String chave = "";
		chave += novaVariacao.getModelo().getNome();
		chave += novaVariacao.getProduto().getTitulo();
		chave += novaVariacao.getTitulo().getDescricao();
		chave += novaVariacao.getInvertida();
		chave += novaVariacao.getTemaCor().getCorFundo();
		
		for (DescricaoValor descricao: novaVariacao.getDescricoes()) {
			chave += descricao.getDescricao();
		}
		
		for (Cartao cartao: novaVariacao.getCartoes()) {
			chave += cartao.getNome();
		}
		
		Optional<VariacaoModelo> variacaoEncontrada = repositorio.findById(chave);
		
		if (variacaoEncontrada.isPresent()) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chave já cadastrada");
		
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
				List<Titulo> titulosProduto = tituloServico.encontrarPorFiltros(null, null, produto.getTitulo());
				
				for (Titulo titulo : titulosProduto) {					
					gerarTodasVariacoes(new SetupVariacaoDTO(variacoes, descricoes, cartoes, modelo, 
							numeroDescricoes, numeroCartoes, produto, titulo));
				}
			}
		}
		List<VariacaoModelo> copiaVariacoes = gerarVariacoesInvertidas(variacoes);
		repositorio.saveAll(copiaVariacoes);
	}
	private List<VariacaoModelo> gerarVariacoesInvertidas(List<VariacaoModelo> variacoes) {
		List<VariacaoModelo> copiaVariacoes = new ArrayList<>(variacoes);
		for (VariacaoModelo variacao : variacoes) {
			VariacaoModelo variacaoCopia = new VariacaoModelo(variacao);
			variacaoCopia.setInvertida(true);
			try {
				variacaoCopia.setChave(gerarChave(variacaoCopia));
				copiaVariacoes.add(variacaoCopia);
			} catch (Exception e) {}
		}
		return copiaVariacoes;
	}
	
	private void gerarTodasVariacoes(SetupVariacaoDTO setup) {
		int contadorDescricao = 0;
		int contadorCartao = 0;
		int contadorDescricaoAuxiliar = 0;
		boolean deveContinuarDescricao = true;
		boolean deveContinuarCartao = true;
		
		if (setup.getNumeroDescricoes() == 0 && setup.getNumeroCartoes() == 0) {
			Set<DescricaoValor> descricoesNovas = new HashSet<>();
			Set<Cartao> cartoesNovas = new HashSet<>();
			
			gerarVariacoesComTema(setup, cartoesNovas, descricoesNovas);
			return;
		}
		
		if (setup.getNumeroDescricoes() == 0) {
			while (deveContinuarCartao) {
				Set<DescricaoValor> descricoesNovas = new HashSet<>();
				Set<Cartao> cartoesNovas = new HashSet<>();
				
				ContadorDTO contadorCartaoRetorno = adicionarCartoes(contadorCartao, setup, deveContinuarCartao, cartoesNovas);
				deveContinuarCartao = contadorCartaoRetorno.getDeveParar();
				contadorCartao = contadorCartaoRetorno.getContador();
				
				if (!deveContinuarCartao) break;
				
				gerarVariacoesComTema(setup, cartoesNovas, descricoesNovas);
			}
			return;
		}
		
		if (setup.getNumeroCartoes() == 0) {
			while (deveContinuarDescricao) {
				Set<DescricaoValor> descricoesNovas = new HashSet<>();
				Set<Cartao> cartoesNovas = new HashSet<>();
			
				ContadorDTO contadorDescricaoRetorno = adicionarDescricoes(contadorDescricaoAuxiliar, setup, deveContinuarDescricao, descricoesNovas);
				deveContinuarDescricao = contadorDescricaoRetorno.getDeveParar();
				contadorDescricaoAuxiliar = contadorDescricaoRetorno.getContador();
				
				if (!deveContinuarDescricao) break;

				gerarVariacoesComTema(setup, cartoesNovas, descricoesNovas);

			}
			return;
		}		
		while (deveContinuarDescricao) {
			deveContinuarCartao = true;
			contadorCartao = 0;
			
			while (deveContinuarCartao) {
				contadorDescricaoAuxiliar = contadorDescricao;

				Set<DescricaoValor> descricoesNovas = new HashSet<>();
				Set<Cartao> cartoesNovas = new HashSet<>();
				
				ContadorDTO contadorDescricaoRetorno = adicionarDescricoes(contadorDescricaoAuxiliar, setup, deveContinuarDescricao, descricoesNovas);
				ContadorDTO contadorCartaoRetorno = adicionarCartoes(contadorCartao, setup, deveContinuarCartao, cartoesNovas);
				
				deveContinuarCartao = contadorCartaoRetorno.getDeveParar();
				deveContinuarDescricao = contadorDescricaoRetorno.getDeveParar();
				contadorDescricaoAuxiliar = contadorDescricaoRetorno.getContador();
				contadorCartao = contadorCartaoRetorno.getContador();
				
				if (!deveContinuarCartao || !deveContinuarDescricao) break;

				gerarVariacoesComTema(setup, cartoesNovas, descricoesNovas);
			}
			
			contadorDescricao = contadorDescricaoAuxiliar;
		}
	}
	
	
	public ContadorDTO adicionarDescricoes(Integer contadorDescricaoAuxiliar, SetupVariacaoDTO setup, Boolean deveContinuarDescricao, Set<DescricaoValor> listaNova) {
		for (int descricaoI = 0; descricaoI < setup.getNumeroDescricoes(); descricaoI++) {
			if (setup.getDescricoes().size() <= contadorDescricaoAuxiliar) {
				deveContinuarDescricao = false;
				break;
			}
			listaNova.add(setup.getDescricoes().get(contadorDescricaoAuxiliar));
			contadorDescricaoAuxiliar++;
		}
		
		return new ContadorDTO(contadorDescricaoAuxiliar, deveContinuarDescricao);
	}
	
	public ContadorDTO adicionarCartoes(Integer contadorCartao, SetupVariacaoDTO setup, Boolean deveContinuarCartao, Set<Cartao> listaNova) {
		for (int cartaoI = 0; cartaoI < setup.getNumeroCartoes(); cartaoI++) {
			if (setup.getCartoes().size() <= contadorCartao) {
				deveContinuarCartao = false;
				break;
			}
			listaNova.add(setup.getCartoes().get(contadorCartao));
			contadorCartao++;
		}
		
		return new ContadorDTO(contadorCartao, deveContinuarCartao);
	}
	
	public void gerarVariacoesComTema(SetupVariacaoDTO setup, Set<Cartao> cartoes, Set<DescricaoValor> descricoes) {
		List<TemaCor> temas = temaCorServico.listarTodos();
		
		for (TemaCor tema : temas) {
			VariacaoModelo novaVariacao = new VariacaoModelo();
			novaVariacao.setTitulo(setup.getTitulo());
			novaVariacao.setProduto(setup.getProduto());
			novaVariacao.setCartoes(cartoes);
			novaVariacao.setDescricoes(descricoes);
			novaVariacao.setModelo(setup.getModelo());
			novaVariacao.setTemaCor(tema);
			
			try {
				novaVariacao.setChave(gerarChave(novaVariacao));
				novaVariacao.setStatus(StatusEnum.Neutro);
			} catch (Exception e) {}
			
			if (novaVariacao.getChave() != null) 
				setup.getVariacoes().add(novaVariacao);
		}
	}
	
	public VariacaoModelo encontrarPorId (String chave) {
		Optional<VariacaoModelo> variacaoEncontrada = repositorio.findById(chave);
		if (variacaoEncontrada.isPresent()) {
			return variacaoEncontrada.get();
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Variacão Não Encontrada");
	}

	public Page<VariacaoModelo> filtrar(String modeloId, String produtoId, String tituloId, Integer pagina, Integer tamanho) {
		Pageable paginavel = PageRequest.of(pagina, tamanho);
		return repositorio.filtrarPaginado(modeloId, tituloId, produtoId, paginavel);
	}
	
	public Page<VariacaoModelo> findVariacaoSemAnuncio(String produtoId, Integer pagina, Integer tamanho) {
		Pageable paginavel = PageRequest.of(pagina, tamanho);
		return repositorio.findVariacaoSemAnuncio(produtoId, paginavel);
	}
	
	public void deleteAll() {
		repositorio.deleteAll();
	}

	public void deleteByChave(String chave) {
		repositorio.deleteById(chave);
	}
}
