package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.adGeneratorApi.Dominio.DTO.CartaoDTO;

@Entity
public class Cartao implements Serializable{
	private static final long serialVersionUID = -8939219162105153756L;

	@Id
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private String caminhoImagem;
	
	public Cartao() {}

	public Cartao(CartaoDTO dto) {
		this.nome = dto.getNome();
		this.descricao = dto.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}
}
