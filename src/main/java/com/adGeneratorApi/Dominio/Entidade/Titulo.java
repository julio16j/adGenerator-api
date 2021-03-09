package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.adGeneratorApi.Dominio.DTO.TituloDTO;
import com.adGeneratorApi.Dominio.Enum.Tamanho;

@Entity
public class Titulo implements Serializable {
	
	private static final long serialVersionUID = -8939219162105153756L;
	
	@Id
	private String descricao;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Tamanho tamanho;
	
	@ManyToOne
	@JoinColumn(name = "nome")
	private Produto produto;
	
	
	public Titulo () {}
	
	public Titulo(TituloDTO dto) {
		this.descricao = dto.getDescricao();
		this.tamanho = dto.getTamanho();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
