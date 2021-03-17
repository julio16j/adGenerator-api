package com.adGeneratorApi.Dominio.DTO;

public class ContadorDTO {

	Integer contador;
	Boolean deveParar;
	
	public ContadorDTO(Integer contador, Boolean deveParar) {
		super();
		this.contador = contador;
		this.deveParar = deveParar;
	}
	
	public Integer getContador() {
		return contador;
	}
	public void setContador(Integer contador) {
		this.contador = contador;
	}
	public Boolean getDeveParar() {
		return deveParar;
	}
	public void setDeveParar(Boolean deveParar) {
		this.deveParar = deveParar;
	}
}
