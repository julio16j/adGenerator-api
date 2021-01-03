package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.adGeneratorApi.Dominio.Enum.Status;

@Entity
public class ContaOlx implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -5477372506592201637L;

	@Id
	private String email;
	
	@Column(name = "Senha")
	private String senha; 
	
	@Column(name = "AlteracaoData")
	private LocalDateTime alteracaodata;
	
	@Column(name = "Status")
	private Status status;
	
	public ContaOlx () {}
	

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
