package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.adGeneratorApi.Dominio.DTO.CodigoProdutoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CodigoProduto implements Serializable {

	private static final long serialVersionUID = -8939219162105153756L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 5)
	private String codigo;
	
	@OneToOne
	@JsonIgnore
	private Produto produto;

	public CodigoProduto() {}
	
	public CodigoProduto(CodigoProdutoDTO dto) {
		this.codigo = dto.getCodigo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
