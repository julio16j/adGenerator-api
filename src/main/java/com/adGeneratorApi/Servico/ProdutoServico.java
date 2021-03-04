package com.adGeneratorApi.Servico;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Dominio.DTO.ProdutoDTO;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Enum.CategoriaProduto;
import com.adGeneratorApi.Repositorio.ProdutoRepositorio;

@Component
public class ProdutoServico {
	
	@Autowired
	ProdutoRepositorio repositorio;
	
	public List<Produto> listarTodos () {
		return repositorio.findAll();
	}
	
	public Produto cadastrarProduto (ProdutoDTO dto) throws IOException {
		if (repositorio.findById(dto.getNome()).isPresent()) throw new RuntimeException("Produto já existente");

		Produto novoProduto = new Produto(dto);
		String caminhoImagem = salvarImagem(dto.getImagemBase64(), dto.getNome());
		novoProduto.setCaminhoImagem(caminhoImagem);
		Produto produtoSalvo = repositorio.save(novoProduto);
		return produtoSalvo;
	}
	
	public Produto editarProduto (ProdutoDTO dto) throws IOException {
		if (encontrarPorId(dto.getNome()) == null) throw new RuntimeException("Produto não encontrado");
		
		Produto novoProduto = new Produto(dto);
		String caminhoImagem = salvarImagem(dto.getImagemBase64(), dto.getNome());
		novoProduto.setCaminhoImagem(caminhoImagem);
		Produto produtoSalvo = repositorio.save(novoProduto);
		return produtoSalvo;
	}

	private String salvarImagem(String imagemBase64, String nome) throws IOException {
	  byte[] byteDecodificados = Base64.getDecoder().decode(imagemBase64);
      ByteArrayInputStream byteStream = new ByteArrayInputStream(byteDecodificados);
      BufferedImage buffImagem = ImageIO.read(byteStream);
      String caminho = "imagens/" + nome + ".png";
      ImageIO.write(buffImagem, "png", new File(caminho) );
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
		repositorio.deleteById(produtoId);
		
	}
	
	
}
