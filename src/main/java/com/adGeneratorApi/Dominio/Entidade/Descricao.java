package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Descricao extends Texto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3327139497848169734L;
	@Column(name = "Nome")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
