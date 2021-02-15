package com.adGeneratorApi.Dominio.Enum;

public enum TipoElemento {
	
	Imagem ("imagem"), Titulo ("titulo"), Descricao ("descricao"), Cartao ("cartao");
	
	private String descricao;
	
	TipoElemento(String string) {
		this.descricao = string;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
