package com.adGeneratorApi.Dominio.Embutivel;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Imagem implements Serializable {
	
	private static final long serialVersionUID = -4443197339432215165L;

	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "estiloInicial", column = @Column(name = "ImagemEstiloInicial")),
		  @AttributeOverride( name = "value", column = @Column(name = "ImagemValue"))
		})
	private Elemento elementoImagem;
	
	@Embedded
	@AttributeOverrides({
	  @AttributeOverride( name = "rotate", column = @Column(name = "ImagemRotate")),
	  @AttributeOverride( name = "scale", column = @Column(name = "ImagemScale")),
	  @AttributeOverride( name = "translate", column = @Column(name = "ImagemTranslate"))
	})
	private Transform transformacao;

	@Override
	public int hashCode() {
		return Objects.hash(elementoImagem, transformacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imagem other = (Imagem) obj;
		return Objects.equals(elementoImagem, other.elementoImagem) && Objects.equals(transformacao, other.transformacao);
	}

	public Elemento getElementoImagem() {
		return elementoImagem;
	}

	public void setElementoImagem(Elemento elementoImagem) {
		this.elementoImagem = elementoImagem;
	}

	public Transform getTransformacao() {
		return transformacao;
	}

	public void setTransformacao(Transform transformacao) {
		this.transformacao = transformacao;
	}
}
