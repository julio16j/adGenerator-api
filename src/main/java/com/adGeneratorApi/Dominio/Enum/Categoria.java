package com.adGeneratorApi.Dominio.Enum;

public enum Categoria {
	
	Geral ("geral"), Tecnico ("tecnico"), Persuasivo ("persuasivo"), Financeiro ("financeiro");
	
	private String descricao;
	
	Categoria (String string) {
		this.descricao = string;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
