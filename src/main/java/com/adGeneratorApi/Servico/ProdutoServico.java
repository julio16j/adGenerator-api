package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.adGeneratorApi.Dominio.DTO.ProdutoDTO;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Enum.CategoriaProduto;
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
		if (repositorio.findById(dto.getNome()).isPresent()) throw new RuntimeException("Produto já existente");

		Produto novoProduto = new Produto(dto);
		String caminhoImagem = salvarImagem(imagemProduto, dto.getNome());
		novoProduto.setCaminhoImagem(caminhoImagem);
		Produto produtoSalvo = repositorio.save(novoProduto);
		return produtoSalvo;
	}
	
	public Produto editarProduto (String produto, MultipartFile imagemProduto) throws Exception {
		ProdutoDTO dto = MapeadorObjeto.converterStringJson(produto, ProdutoDTO.class);
		Produto produtoAtual = encontrarPorId(dto.getNome());
		
		if (produtoAtual == null) throw new RuntimeException("Produto não encontrado");
		
		storageServico.deleteFile(produtoAtual.getCaminhoImagem());
		
		Produto novoProduto = new Produto(dto);
		String caminhoImagem = salvarImagem(imagemProduto, dto.getNome());
		novoProduto.setCaminhoImagem(caminhoImagem);
		Produto produtoSalvo = repositorio.save(novoProduto);
		return produtoSalvo;
	}

	private String salvarImagem(MultipartFile imagemProduto, String nome) throws Exception {
	  String caminho = storageServico.uploadFile(imagemProduto);
      return caminho;
	}

	public Produto encontrarPorId(String produtoId) {
		Optional<Produto> produtoEncontrado = repositorio.findById(produtoId);
		if (produtoEncontrado.isEmpty()) throw new RuntimeException("Produto não encontrado");
		return produtoEncontrado.get();
	}
	
	public List<Produto> encontrarPorFiltros(String nome, String descricao, CategoriaProduto categoria) {
		return repositorio.findByFilters(nome, descricao, categoria);
	}
	
	public void delete(String produtoId) {
		Produto produto = encontrarPorId(produtoId);
		storageServico.deleteFile(produto.getCaminhoImagem());
		repositorio.deleteById(produtoId);
		
	}
	
}
