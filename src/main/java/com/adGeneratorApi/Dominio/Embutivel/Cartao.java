package com.adGeneratorApi.Dominio.Embutivel;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Cartao implements Serializable {
	
	private static final long serialVersionUID = 5605575035128795390L;

	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "estiloInicial", column = @Column(name = "CartaoEstiloInicial")),
		  @AttributeOverride( name = "value", column = @Column(name = "CartaoValue"))
		})
	private Elemento elementoCartao;
	
	@Embedded
	@AttributeOverrides({
	  @AttributeOverride( name = "rotate", column = @Column(name = "CartaoRotate")),
	  @AttributeOverride( name = "scale", column = @Column(name = "CartaoScale")),
	  @AttributeOverride( name = "translate", column = @Column(name = "CartaoTranslate"))
	})
	private Transform transformacao;

	@Override
	public int hashCode() {
		return Objects.hash(elementoCartao, transformacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		return Objects.equals(elementoCartao, other.elementoCartao) && Objects.equals(transformacao, other.transformacao);
	}

	public Elemento getElementoCartao() {
		return elementoCartao;
	}

	public void setElementoCartao(Elemento cartao) {
		this.elementoCartao = cartao;
	}

	public Transform getTransformacao() {
		return transformacao;
	}

	public void setTransformacao(Transform transformacao) {
		this.transformacao = transformacao;
	}
}
