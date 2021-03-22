package com.adGeneratorApi.Dominio.Enum;

public enum StatusUso {
	
	EM_USO ("Em uso"), DISPONIVEL ("Disponível"), BLOQUEADA ("Bloqueada");
	
	private String descricao;
	
	StatusUso (String string) {
		this.descricao = string;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
