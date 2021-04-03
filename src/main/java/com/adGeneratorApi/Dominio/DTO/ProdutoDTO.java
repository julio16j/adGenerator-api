package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

import com.adGeneratorApi.Dominio.Enum.CondicaoProduto;


public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = -4902779232709253309L;
	private Long id;
	private String titulo;
	private String descricao;
	private String categoria;
	private String subCategoria;
	private String tipo;
	private String cep;
	private BigDecimal preco;
	private CondicaoProduto condicao;
	private String caminhoImagem;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public CondicaoProduto getCondicao() {
		return condicao;
	}
	public void setCondicao(CondicaoProduto condicao) {
		this.condicao = condicao;
	}
	public String getCaminhoImagem() {
		return caminhoImagem;
	}
	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

}
