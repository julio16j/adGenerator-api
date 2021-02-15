package com.adGeneratorApi.Dominio.Embutivel;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Titulo implements Serializable {
	
	private static final long serialVersionUID = -7845187359549996098L;

	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "estiloInicial", column = @Column(name = "TituloEstiloInicial")),
		  @AttributeOverride( name = "value", column = @Column(name = "TituloValue"))
		})
	private Elemento elementoTitulo;
	
	@Embedded
	@AttributeOverrides({
	  @AttributeOverride( name = "rotate", column = @Column(name = "TituloRotate")),
	  @AttributeOverride( name = "scale", column = @Column(name = "TituloScale")),
	  @AttributeOverride( name = "translate", column = @Column(name = "TituloTranslate"))
	})
	private Transform transformacao;

	@Override
	public int hashCode() {
		return Objects.hash(elementoTitulo, transformacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		return Objects.equals(elementoTitulo, other.elementoTitulo) && Objects.equals(transformacao, other.transformacao);
	}

	public Elemento getElementoTitulo() {
		return elementoTitulo;
	}

	public void setElementoTitulo(Elemento titulo) {
		this.elementoTitulo = titulo;
	}

	public Transform getTransformacao() {
		return transformacao;
	}

	public void setTransformacao(Transform transformacao) {
		this.transformacao = transformacao;
	}
}
