package com.adGeneratorApi.Dominio.Enum;

public enum CategoriaDescricao {
	
	Geral ("geral"), Tecnico ("tecnico"), Persuasivo ("persuasivo"), Financeiro ("financeiro");
	
	private String descricao;
	
	CategoriaDescricao (String string) {
		this.descricao = string;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
