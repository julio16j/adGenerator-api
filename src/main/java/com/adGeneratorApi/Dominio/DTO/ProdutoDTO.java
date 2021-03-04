package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;

import com.adGeneratorApi.Dominio.Enum.CategoriaProduto;

public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = -4902779232709253309L;
	private String nome;
	private String descricao;
	private CategoriaProduto categoria;
	
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
	public CategoriaProduto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

}
