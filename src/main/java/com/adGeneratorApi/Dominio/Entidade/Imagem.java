package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Imagem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5783533330142313854L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "titulo", referencedColumnName = "nome")
	private Titulo titulo;

	@OneToMany
	private List<Descricao> descricoes;
	
	@Column(name = "PositionX")
	private String positionx;
	
	@Column(name = "PositionY")
	private String positiony;
	
	@Column(name = "Height")
	private String height;
	
	@Column(name = "Width")
	private String witdth;
	
	@Column(name = "Path")
	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPositionx() {
		return positionx;
	}

	public void setPositionx(String positionx) {
		this.positionx = positionx;
	}

	public String getPositiony() {
		return positiony;
	}

	public void setPositiony(String positiony) {
		this.positiony = positiony;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWitdth() {
		return witdth;
	}

	public void setWitdth(String witdth) {
		this.witdth = witdth;
	}

	public String getRotation() {
		return rotation;
	}

	public void setRotation(String rotation) {
		this.rotation = rotation;
	}

	@Column(name = "Rotation")
	private String rotation;

	public List<Descricao> getDescricoes() {
		return descricoes;
	}

	public void setDescricoes(List<Descricao> descricoes) {
		this.descricoes = descricoes;
	}


	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
	
}
