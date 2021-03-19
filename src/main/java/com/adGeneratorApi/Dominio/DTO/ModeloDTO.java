package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;
import java.util.List;

import com.adGeneratorApi.Dominio.Embutivel.Elemento;

public class ModeloDTO implements Serializable {
	
	private static final long serialVersionUID = -2526212402637664429L;
	private String nome;
	private Elemento imagem;
	private Elemento titulo;
	private List<Elemento> descricoes;
	private List<Elemento> cartoes;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Elemento getImagem() {
		return imagem;
	}
	public void setImagem(Elemento imagem) {
		this.imagem = imagem;
	}
	public Elemento getTitulo() {
		return titulo;
	}
	public void setTitulo(Elemento titulo) {
		this.titulo = titulo;
	}
	public List<Elemento> getDescricoes() {
		return descricoes;
	}
	public void setDescricoes(List<Elemento> descricoes) {
		this.descricoes = descricoes;
	}
	public List<Elemento> getCartoes() {
		return cartoes;
	}
	public void setCartoes(List<Elemento> cartoes) {
		this.cartoes = cartoes;
	}
	
}
