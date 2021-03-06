package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.adGeneratorApi.Dominio.DTO.UsuarioDTO;

@Entity
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -8939219162105153756L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "Nome")
	private String nome;
	
	@Column(name = "Email", unique = true)
	private String email;
	
	@Column(name = "Senha")
	private String senha;
	
	@Column(name = "Telefone")
	private String telefone;
	
	@Column(name = "isAdmin")
	private Boolean isAdmin;
	
	public Usuario () {}
	
	public Usuario(UsuarioDTO dto) {
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.senha = dto.getSenha();
		this.telefone = dto.getTelefone();
		this.isAdmin = dto.getIsAdmin() == null ? false : dto.getIsAdmin();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
