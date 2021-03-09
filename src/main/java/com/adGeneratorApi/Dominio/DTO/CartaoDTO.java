package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;

public class CartaoDTO implements Serializable {
	
	private static final long serialVersionUID = -4902779232709253309L;
	private String nome;
	private String descricao;
	
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
}
