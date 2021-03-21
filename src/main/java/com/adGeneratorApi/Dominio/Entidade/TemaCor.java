package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.adGeneratorApi.Dominio.DTO.TemaCorDTO;

@Entity
public class TemaCor implements Serializable {
	
	private static final long serialVersionUID = -8939219162105153756L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String corFundo;
	
	@Column
	private String corFonte;
	
	public TemaCor() {}
	
	public TemaCor(TemaCorDTO dto) {
		corFundo = dto.getCorFundo();
		corFonte = dto.getCorFonte();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorFundo() {
		return corFundo;
	}

	public void setCorFundo(String corFundo) {
		this.corFundo = corFundo;
	}

	public String getCorFonte() {
		return corFonte;
	}

	public void setCorFonte(String corFonte) {
		this.corFonte = corFonte;
	}
	
}
