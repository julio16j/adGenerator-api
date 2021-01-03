package com.adGeneratorApi.Dominio.Entidade;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.adGeneratorApi.Dominio.Enum.Status;

@Entity
public class ContaOlx {
		
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
