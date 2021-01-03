package com.adGeneratorApi.Dominio.Entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.adGeneratorApi.Dominio.Enum.Segmento;

@Entity
public class Produto {

	@Id
	private String idalfa;
	
	@Column(name = "Segmento")
	@Enumerated(EnumType.STRING)
	private Segmento segmento;

	@Column(name = "Titulo")
	private String titulo;
	
	@Column(name = "Descricao")
	private String descricao;
	
	@Column(name = "Preco")
	private BigDecimal preco;
	
	@ManyToOne
	@JoinColumn(name="usuarioEmail", referencedColumnName = "email")
	private Usuario usuario;
	
	public String getIdalfa() {
		return idalfa;
	}

	public void setIdalfa(String idalfa) {
		this.idalfa = idalfa;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
	
}
