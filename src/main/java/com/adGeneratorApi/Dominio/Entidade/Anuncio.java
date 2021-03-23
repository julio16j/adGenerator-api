package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.adGeneratorApi.Dominio.DTO.AnuncioDTO;

@Entity
public class Anuncio implements Serializable {
	
	private static final long serialVersionUID = -8939219162105153756L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "variacaoModeloId", referencedColumnName = "chave")
	private VariacaoModelo variacaoModelo;

	@ManyToOne
	@JoinColumn(name = "usuarioId", referencedColumnName = "id")
	private Usuario usuarioDivulgador;
	
	@ManyToOne
	@JoinColumn(name = "contaOlxId", referencedColumnName = "id")
	private ContaOlx contaOlx;
	
	@Column(unique = true, nullable = true)
	private String link;
	
	@Column
	private LocalDateTime dataPostado;
	
	public Anuncio () {}
	
	public Anuncio (AnuncioDTO dto) {
		variacaoModelo = dto.getVariacaoModelo();
		contaOlx = dto.getContaOlx();
		dataPostado = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VariacaoModelo getVariacaoModelo() {
		return variacaoModelo;
	}

	public void setVariacaoModelo(VariacaoModelo variacaoModelo) {
		this.variacaoModelo = variacaoModelo;
	}

	public ContaOlx getContaOlx() {
		return contaOlx;
	}

	public void setContaOlx(ContaOlx contaOlx) {
		this.contaOlx = contaOlx;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public LocalDateTime getDataPostado() {
		return dataPostado;
	}

	public void setDataPostado(LocalDateTime dataPostado) {
		this.dataPostado = dataPostado;
	}

	public Usuario getUsuarioDivulgador() {
		return usuarioDivulgador;
	}

	public void setUsuarioDivulgador(Usuario usuarioDivulgador) {
		this.usuarioDivulgador = usuarioDivulgador;
	}
	
}
