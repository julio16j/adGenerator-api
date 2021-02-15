package com.adGeneratorApi.Dominio.Embutivel;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Descricao implements Serializable {
	
	private static final long serialVersionUID = 5352875942175956991L;

	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "estiloInicial", column = @Column(name = "DescricaoEstiloInicial")),
		  @AttributeOverride( name = "value", column = @Column(name = "DescricaoValue"))
		})
	private Elemento elementoDescricao;
	
	@Embedded
	@AttributeOverrides({
	  @AttributeOverride( name = "rotate", column = @Column(name = "DescricaoRotate")),
	  @AttributeOverride( name = "scale", column = @Column(name = "DescricaoScale")),
	  @AttributeOverride( name = "translate", column = @Column(name = "DescricaoTranslate"))
	})
	private Transform transformacao;

	@Override
	public int hashCode() {
		return Objects.hash(elementoDescricao, transformacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Descricao other = (Descricao) obj;
		return Objects.equals(elementoDescricao, other.elementoDescricao) && Objects.equals(transformacao, other.transformacao);
	}

	public Elemento getElementoDescricao() {
		return elementoDescricao;
	}

	public void setElementoDescricao(Elemento descricao) {
		this.elementoDescricao = descricao;
	}

	public Transform getTransformacao() {
		return transformacao;
	}

	public void setTransformacao(Transform transformacao) {
		this.transformacao = transformacao;
	}
}
