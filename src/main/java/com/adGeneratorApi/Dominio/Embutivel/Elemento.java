package com.adGeneratorApi.Dominio.Embutivel;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Elemento implements Serializable {
	
	private static final long serialVersionUID = -703004887653561206L;

	@Column(columnDefinition = "varchar(10)")
	private String value;
	
	@Column(columnDefinition = "varchar(50)")
	private String estiloInicial;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getEstiloInicial() {
		return estiloInicial;
	}
	public void setEstiloInicial(String estiloInicial) {
		this.estiloInicial = estiloInicial;
	}
	@Override
	public int hashCode() {
		return Objects.hash(estiloInicial, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elemento other = (Elemento) obj;
		return Objects.equals(estiloInicial, other.estiloInicial) && Objects.equals(value, other.value);
	}
	
}
