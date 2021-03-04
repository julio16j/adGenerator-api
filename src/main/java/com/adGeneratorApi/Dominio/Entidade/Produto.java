package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.adGeneratorApi.Dominio.DTO.ProdutoDTO;
import com.adGeneratorApi.Dominio.Enum.CategoriaProduto;

@Entity
public class Produto implements Serializable{
	
	private static final long serialVersionUID = -8939219162105153756L;
	
	@Id
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	@Enumerated(EnumType.STRING)
	private CategoriaProduto categoria;
	
	@Column
	private String caminhoImagem;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Titulo> titulos;
	
	
	public Produto () {}


	public Produto(ProdutoDTO dto) {
		this.nome = dto.getNome();
		this.descricao = dto.getDescricao();
		this.categoria = dto.getCategoria();
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


	public CategoriaProduto getCategoria() {
		return categoria;
	}


	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}


	public String getCaminhoImagem() {
		return caminhoImagem;
	}


	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

}
