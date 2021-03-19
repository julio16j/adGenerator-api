package com.adGeneratorApi.Dominio.Embutivel;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Elemento implements Serializable {
	
	private static final long serialVersionUID = -703004887653561206L;
	
	@Column
	private String estiloInicial;
	
	@Column
	private String transformacao;

	public String getEstiloInicial() {
		return estiloInicial;
	}

	public void setEstiloInicial(String estiloInicial) {
		this.estiloInicial = estiloInicial;
	}

	public String getTransformacao() {
		return transformacao;
	}

	public void setTransformacao(String transformacao) {
		this.transformacao = transformacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estiloInicial, transformacao);
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
		return Objects.equals(estiloInicial, other.estiloInicial) && Objects.equals(transformacao, other.transformacao);
	}
	
	
}
