package com.adGeneratorApi.Dominio.Entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.adGeneratorApi.Dominio.Enum.Segmento;

@Entity
public class Modelo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "Segmento")
	private Segmento segmento;

	@OneToOne
	@JoinColumn(name="idText", referencedColumnName = "id")
	private Texto titulo;
	
	@ManyToOne
	@JoinColumn(name="produtoId", referencedColumnName = "idalfa")
	private Produto produto;
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@OneToMany
	@JoinColumn(name="idDescricao", referencedColumnName = "id")
	private List<Texto> descricao;
	
	@Column(name = "PositionX")
	private String positionx;
	
	@Column(name = "PositionY")
	private String positiony;
	
	@Column(name = "Height")
	private String height;
	
	@Column(name = "Width")
	private String witdth;
	
	@Column(name = "Rotation")
	private String rotation;
	
	
	
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

	public Modelo () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public Texto getTitulo() {
		return titulo;
	}

	public void setTitulo(Texto titulo) {
		this.titulo = titulo;
	}


	public List<Texto> getDescricao() {
		return descricao;
	}

	public void setDescricao(List<Texto> descricao) {
		this.descricao = descricao;
	}

	
}
