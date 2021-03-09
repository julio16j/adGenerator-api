package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.adGeneratorApi.Dominio.Enum.StatusEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class VariacaoModelo implements Serializable {
	
	private static final long serialVersionUID = -8939219162105153756L;
	
	@Id
	private String chave;
	
	@ManyToOne
	@JoinColumn(name = "modeloId", referencedColumnName = "nome")
	private Modelo modelo;
	
	@ManyToOne
	@JoinColumn(name = "produtoId", referencedColumnName = "nome")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "titulo")
	private Titulo titulo;
	
	@ManyToMany
	@JsonManagedReference
	@JoinTable(
			  name = "DescricaoVariacaoModelo", 
			  joinColumns = @JoinColumn(name = "chaveVariacao"), 
			  inverseJoinColumns = @JoinColumn(name = "descricaoId"))
	private Set<DescricaoValor> descricoes;
	
	@ManyToMany
	@JsonManagedReference
	@JoinTable(
			  name = "CartaoVariacaoModelo", 
			  joinColumns = @JoinColumn(name = "chaveVariacao"), 
			  inverseJoinColumns = @JoinColumn(name = "cartaoId"))
	private Set<Cartao> cartoes;
	
	@Column
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	public VariacaoModelo () {}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public Set<DescricaoValor> getDescricoes() {
		return descricoes;
	}

	public void setDescricoes(Set<DescricaoValor> descricoes) {
		this.descricoes = descricoes;
	}

	public Set<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(Set<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
}
