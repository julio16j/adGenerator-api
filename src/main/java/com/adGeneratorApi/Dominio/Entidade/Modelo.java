package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.adGeneratorApi.Dominio.DTO.ModeloDTO;
import com.adGeneratorApi.Dominio.Embutivel.Elemento;

@Entity
public class Modelo implements Serializable{
	
	private static final long serialVersionUID = -8939219162105153756L;

	@Id
	private String nome;
	
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "estiloInicial", column = @Column(name = "ImagemEstiloInicial")),
		  @AttributeOverride( name = "transformacao", column = @Column(name = "ImagemTransformacao"))
	})
	private Elemento imagem;
	
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "estiloInicial", column = @Column(name = "TituloEstiloInicial")),
		  @AttributeOverride( name = "transformacao", column = @Column(name = "TituloTransformacao"))
	})
	private Elemento titulo;
	
	@ElementCollection
	private List<Elemento> descricoes;
	
	@ElementCollection
	private List<Elemento> cartoes;
	
	public Modelo () {}

	public Modelo(ModeloDTO dto) {
		nome = dto.getNome();
		imagem = dto.getImagem();
		titulo = dto.getTitulo();
		descricoes = dto.getDescricoes();
		cartoes = dto.getCartoes();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Elemento getImagem() {
		return imagem;
	}

	public void setImagem(Elemento imagem) {
		this.imagem = imagem;
	}

	public Elemento getTitulo() {
		return titulo;
	}

	public void setTitulo(Elemento titulo) {
		this.titulo = titulo;
	}

	public List<Elemento> getDescricoes() {
		return descricoes;
	}

	public void setDescricoes(List<Elemento> descricoes) {
		this.descricoes = descricoes;
	}

	public List<Elemento> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Elemento> cartoes) {
		this.cartoes = cartoes;
	}
	

}
