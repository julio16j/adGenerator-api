package com.adGeneratorApi.Dominio.Enum;

public enum Segmento {
	geral("GERAL");
	
	private String valor;
	
	Segmento (String valor) {
		this.valor = valor;
	}
	
	public String getValor () {
		return valor;
	}
}
