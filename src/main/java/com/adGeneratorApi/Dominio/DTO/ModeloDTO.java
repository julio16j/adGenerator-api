package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;
import java.util.List;

import com.adGeneratorApi.Dominio.Embutivel.Cartao;
import com.adGeneratorApi.Dominio.Embutivel.Descricao;
import com.adGeneratorApi.Dominio.Embutivel.Imagem;
import com.adGeneratorApi.Dominio.Embutivel.Titulo;

public class ModeloDTO implements Serializable {
	
	private static final long serialVersionUID = -2526212402637664429L;
	private String nome;
	private Imagem imagem;
	private Titulo titulo;
	private List<Descricao> descricoes;
	private List<Cartao> cartoes;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Imagem getImagem() {
		return imagem;
	}
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	public Titulo getTitulo() {
		return titulo;
	}
	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
	public List<Descricao> getDescricoes() {
		return descricoes;
	}
	public void setDescricoes(List<Descricao> descricoes) {
		this.descricoes = descricoes;
	}
	public List<Cartao> getCartoes() {
		return cartoes;
	}
	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}
	
}
