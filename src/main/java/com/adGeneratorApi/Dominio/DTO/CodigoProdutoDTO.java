package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;

import com.adGeneratorApi.Dominio.Entidade.Produto;

public class CodigoProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = -4902779232709253309L;
	private String codigo;
	private Produto produto;
	
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
