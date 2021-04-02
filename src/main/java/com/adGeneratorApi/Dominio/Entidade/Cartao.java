package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.adGeneratorApi.Dominio.DTO.CartaoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cartao implements Serializable{
	private static final long serialVersionUID = -8939219162105153756L;

	@Id
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private String caminhoImagem;
	
	@ManyToMany(mappedBy = "cartoes")
	@JsonIgnore
	Set<VariacaoModelo> variacoes;
	
	public Cartao() {}

	public Cartao(CartaoDTO dto) {
		this.nome = dto.getNome();
		this.descricao = dto.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public Set<VariacaoModelo> getVariacoes() {
		return variacoes;
	}

	public void setVariacoes(Set<VariacaoModelo> variacoes) {
		this.variacoes = variacoes;
	}
}
