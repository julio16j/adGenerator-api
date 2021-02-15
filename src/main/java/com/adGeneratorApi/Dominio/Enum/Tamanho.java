package com.adGeneratorApi.Dominio.Enum;

public enum Tamanho {
	
	Grande ("grande"), Medio ("medio"), Pequeno ("pequeno");
	
	private String descricao;
	
	Tamanho (String string) {
		this.descricao = string;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
