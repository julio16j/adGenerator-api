package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.adGeneratorApi.Dominio.DTO.DescricaoValorDTO;
import com.adGeneratorApi.Dominio.Enum.CategoriaDescricao;
import com.adGeneratorApi.Dominio.Enum.Tamanho;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DescricaoValor implements Serializable{
	
	private static final long serialVersionUID = -8939219162105153756L;
	
	@Id
	private String descricao;
	
	@Column
	@Enumerated(EnumType.STRING)
	private CategoriaDescricao categoria;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Tamanho tamanho;
	
	@ManyToMany(mappedBy = "descricoes")
	@JsonIgnore
	Set<VariacaoModelo> variacoes;
	
	public DescricaoValor () {}
	
	public DescricaoValor(DescricaoValorDTO dto) {
		this.descricao = dto.getDescricao();
		this.categoria = dto.getCategoria();
		this.tamanho = dto.getTamanho();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaDescricao getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDescricao categoria) {
		this.categoria = categoria;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Set<VariacaoModelo> getVariacoes() {
		return variacoes;
	}

	public void setVariacoes(Set<VariacaoModelo> variacoes) {
		this.variacoes = variacoes;
	}
}
