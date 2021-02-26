package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;

public class CartaoDTO implements Serializable {
	
	private static final long serialVersionUID = -4902779232709253309L;
	private String nome;
	private String descricao;
	private String imagemBase64;
	
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
	
	public String getImagemBase64() {
		return imagemBase64;
	}
	
	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}
}
