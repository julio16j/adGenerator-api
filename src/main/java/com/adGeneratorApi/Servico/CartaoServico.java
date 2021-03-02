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

import com.adGeneratorApi.Dominio.DTO.CartaoDTO;
import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Repositorio.CartaoRepositorio;

@Component
public class CartaoServico {
	
	@Autowired
	CartaoRepositorio repositorio;
	
	public List<Cartao> listarTodos () {
		return repositorio.findAll();
	}
	
	public Cartao cadastrarCartao (CartaoDTO dto) throws IOException {
		if (repositorio.findById(dto.getNome()).isPresent()) throw new RuntimeException("Cartão já existente");

		Cartao novoCartao = new Cartao(dto);
		String caminhoImagem = salvarImagem(dto.getImagemBase64(), dto.getNome());
		novoCartao.setCaminhoImagem(caminhoImagem);
		Cartao CartaoSalvo = repositorio.save(novoCartao);
		return CartaoSalvo;
	}
	
	public Cartao editarCartao(CartaoDTO dto) throws IOException {
		if (encontrarPorId(dto.getNome()) == null) throw new RuntimeException("Cartão não encontrado");
		
		Cartao novoCartao = new Cartao(dto);
		String caminhoImagem = salvarImagem(dto.getImagemBase64(), dto.getNome());
		novoCartao.setCaminhoImagem(caminhoImagem);
		Cartao CartaoSalvo = repositorio.save(novoCartao);
		return CartaoSalvo;
	}

	private String salvarImagem(String imagemBase64, String nome) throws IOException {
	  byte[] byteDecodificados = Base64.getDecoder().decode(imagemBase64);
      ByteArrayInputStream byteStream = new ByteArrayInputStream(byteDecodificados);
      BufferedImage buffImagem = ImageIO.read(byteStream);
      String caminho = "imagens/" + nome + ".png";
      ImageIO.write(buffImagem, "png", new File(caminho) );
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
		repositorio.deleteById(cartaoId);
		
	}
}
