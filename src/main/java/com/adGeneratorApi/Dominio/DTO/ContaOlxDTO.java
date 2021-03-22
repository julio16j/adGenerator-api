package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;

public class ContaOlxDTO implements Serializable {
	
	private static final long serialVersionUID = -2174068800852547423L;
	private String email;
	private String senha;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
