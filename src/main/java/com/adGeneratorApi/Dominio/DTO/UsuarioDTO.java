package com.adGeneratorApi.Dominio.DTO;

import javax.validation.constraints.NotNull;

public class UsuarioDTO {
	
	private String nome;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	
	private String telefone;
	
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
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getTelefone() {
		return this.telefone;
	}
	public void setTelefone (String telefone) {
		this.telefone = telefone;
	}
}
