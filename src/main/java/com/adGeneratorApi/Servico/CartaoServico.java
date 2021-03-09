package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.adGeneratorApi.Dominio.DTO.CartaoDTO;
import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Repositorio.CartaoRepositorio;
import com.adGeneratorApi.Utils.MapeadorObjeto;

@Component
public class CartaoServico {
	
	@Autowired
	CartaoRepositorio repositorio;
	@Autowired
	StorageServico storageServico;
	
	public List<Cartao> listarTodos () {
		return repositorio.findAll();
	}
	
	public Cartao cadastrarCartao (String cartao, MultipartFile imagemCartao) throws Exception {
		CartaoDTO dto = MapeadorObjeto.converterStringJson(cartao, CartaoDTO.class);
		if (repositorio.findById(dto.getNome()).isPresent()) throw new RuntimeException("Cartão já existente");
		
		Cartao novoCartao = new Cartao(dto);
		String caminhoImagem = salvarImagem(imagemCartao, dto.getNome());
		novoCartao.setCaminhoImagem(caminhoImagem);
		Cartao cartaoSalvo = repositorio.save(novoCartao);
		return cartaoSalvo;
	}
	
	public Cartao editarCartao(String cartao, MultipartFile imagemCartao) throws Exception {
		CartaoDTO dto = MapeadorObjeto.converterStringJson(cartao, CartaoDTO.class);
		Cartao cartaoAtual = encontrarPorId(dto.getNome());
		
		if (cartaoAtual == null) throw new RuntimeException("Cartão não encontrado");
		
		storageServico.deleteFile(cartaoAtual.getCaminhoImagem());

		Cartao novoCartao = new Cartao(dto);
		String caminhoImagem = salvarImagem(imagemCartao, dto.getNome());
		novoCartao.setCaminhoImagem(caminhoImagem);
		Cartao cartaoSalvo = repositorio.save(novoCartao);
		return cartaoSalvo;
	}

	private String salvarImagem(MultipartFile imagemCartao, String nome) throws Exception {
	  String caminho = storageServico.uploadFile(imagemCartao);
	  return caminho;
	}

	public Cartao encontrarPorId(String cartaoId) {
		Optional<Cartao> cartaoEncontrado = repositorio.findById(cartaoId);
		if (cartaoEncontrado.isEmpty()) throw new RuntimeException("Cartão não encontrado");
		return cartaoEncontrado.get();
	}
	
	public List<Cartao> encontrarPorFiltros(String nome) {
		return repositorio.findByFilters(nome);
	}
	
	public void delete(String cartaoId) {
		Cartao cartao = encontrarPorId(cartaoId);
		storageServico.deleteFile(cartao.getCaminhoImagem());
		repositorio.deleteById(cartaoId);
	}
}
