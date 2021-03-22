package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.adGeneratorApi.Dominio.DTO.ContaOlxDTO;
import com.adGeneratorApi.Dominio.Enum.StatusUso;

@Entity
public class ContaOlx implements Serializable {
	
	private static final long serialVersionUID = -8939219162105153756L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;
	
	@Column
	private LocalDateTime ultimaVezUtilizada;
	
	@Column
	@Enumerated(EnumType.STRING)
	private StatusUso status;
	
	
	public ContaOlx () {}
	
	public ContaOlx (ContaOlxDTO dto) {
		email = dto.getEmail();
		senha = dto.getSenha();
		status = StatusUso.DISPONIVEL;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public LocalDateTime getUltimaVezUtilizada() {
		return ultimaVezUtilizada;
	}


	public void setUltimaVezUtilizada(LocalDateTime ultimaVezUtilizada) {
		this.ultimaVezUtilizada = ultimaVezUtilizada;
	}


	public StatusUso getStatus() {
		return status;
	}


	public void setStatus(StatusUso status) {
		this.status = status;
	}	
}
