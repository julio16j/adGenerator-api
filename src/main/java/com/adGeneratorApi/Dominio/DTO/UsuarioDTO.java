package com.adGeneratorApi.Dominio.DTO;

import javax.validation.constraints.NotNull;

public class UsuarioDTO {
	
	private String nome;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	
	private String token;
	
	private Boolean isAdmin;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
