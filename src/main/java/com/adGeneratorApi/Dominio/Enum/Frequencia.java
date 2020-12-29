package com.adGeneratorApi.Dominio.Enum;

public enum Frequencia {
	diaria("diaria"), semanal("semanal"), mensal("mensal"), semFrequencia("semFrequencia");
	
	private String valor;
	
	Frequencia(String valor) {
		this.valor = valor;
	}
	
	public String getValor () {
		return valor;
	}
}
