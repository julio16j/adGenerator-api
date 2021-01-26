package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Titulo extends Texto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -331877058760972032L;
	@Column(name = "Nome")
	private String nome;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
