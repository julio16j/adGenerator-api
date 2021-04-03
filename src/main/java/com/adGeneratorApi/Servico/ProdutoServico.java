package com.adGeneratorApi.Servico;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.ProdutoDTO;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Repositorio.ProdutoRepositorio;
import com.adGeneratorApi.Utils.MapeadorObjeto;

@Component
public class ProdutoServico {
	
	@Autowired
	ProdutoRepositorio repositorio;
	@Autowired
	StorageServico storageServico;
	
	public List<Produto> listarTodos () {
		return repositorio.findAll();
	}
	
	public Produto cadastrarProduto (String produto, MultipartFile imagemProduto) throws Exception {
		ProdutoDTO dto = MapeadorObjeto.converterStringJson(produto, ProdutoDTO.class); 
		if (repositorio.findByTitulo(dto.getTitulo()).isPresent()) 
			throw new RuntimeException("Produto já existente");

		Produto novoProduto = new Produto(dto);
		String caminhoImagem = salvarImagem(imagemProduto, dto.getTitulo());
		novoProduto.setCaminhoImagem(caminhoImagem);
		Produto produtoSalvo = repositorio.save(novoProduto);
		return produtoSalvo;
	}
	
	public Produto editarProduto (String produto, MultipartFile imagemProduto) throws Exception {
		ProdutoDTO dto = MapeadorObjeto.converterStringJson(produto, ProdutoDTO.class);
		Produto produtoAtual = encontrarPorId(dto.getId());
		
		if (produtoAtual == null) throw new RuntimeException("Produto não encontrado");
		
		storageServico.deleteFile(produtoAtual.getCaminhoImagem());
		
		Produto novoProduto = new Produto(dto);
		String caminhoImagem = salvarImagem(imagemProduto, dto.getTitulo());
		novoProduto.setCaminhoImagem(caminhoImagem);
		Produto produtoSalvo = repositorio.save(novoProduto);
		return produtoSalvo;
	}

	private String salvarImagem(MultipartFile imagemProduto, String nome) throws Exception {
	  String caminho = storageServico.uploadFile(imagemProduto);
      return caminho;
	}

	public Produto encontrarPorId(Long produtoId) {
		Optional<Produto> produtoEncontrado = repositorio.findById(produtoId);
		if (produtoEncontrado.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Produto não encontrado");
		return produtoEncontrado.get();
	}
	
	public Produto encontrarPorTitulo(String produtoTitulo) {
		Optional<Produto> produtoEncontrado = repositorio.findByTitulo(produtoTitulo);
		if (produtoEncontrado.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Produto não encontrado");
		return produtoEncontrado.get();
	}
	
	public List<Produto> encontrarPorFiltros(String titulo, String descricao, BigDecimal preco) {
		return repositorio.findByFilters(titulo, descricao, preco);
	}
	
	public void delete(Long produtoId) {
		Produto produto = encontrarPorId(produtoId);
		storageServico.deleteFile(produto.getCaminhoImagem());
		repositorio.deleteById(produtoId);
		
	}
	
}
