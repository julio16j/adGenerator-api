package com.adGeneratorApi.Dominio.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Descricao extends Texto {
	
	@Column(name = "Nome")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
