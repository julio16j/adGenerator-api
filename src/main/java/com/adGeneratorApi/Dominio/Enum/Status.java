package com.adGeneratorApi.Dominio.Enum;

public enum Status {
	emUso("EM USO");
	
	private String valor;
	
	Status (String valor) {
		this.valor = valor;
	}
	
	public String getValor () {
		return valor;
	}
}
