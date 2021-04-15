package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.adGeneratorApi.Dominio.DTO.ProdutoDTO;
import com.adGeneratorApi.Dominio.Enum.CondicaoProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable{
	
	private static final long serialVersionUID = -8939219162105153756L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull
	private String titulo;
	
	@Column
	private String descricao;
	
	@Column
	private String categoria;
	
	@Column
	private String subCategoria;
	
	@Column
	private String tipo;
	
	@Column
	private String cep;
	
	@Column
	private BigDecimal preco;
	
	@Column
	@Enumerated(EnumType.STRING)
	private CondicaoProduto condicao;
	
	@Column
	private String caminhoImagem;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Titulo> titulos;
	
	@OneToOne(mappedBy = "produto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private CodigoProduto codigoProduto;

	public Produto () {}
	
	public Produto (ProdutoDTO dto) {
		this.titulo = dto.getTitulo();
		this.descricao = dto.getDescricao();
		this.categoria = dto.getCategoria();
		this.subCategoria = dto.getSubCategoria();
		this.tipo = dto.getTipo();
		this.cep = dto.getCep();
		this.preco = dto.getPreco();
		this.condicao = dto.getCondicao();
		this.caminhoImagem = dto.getCaminhoImagem();
	}

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

	public List<Titulo> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<Titulo> titulos) {
		this.titulos = titulos;
	}
	
	public CodigoProduto getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(CodigoProduto codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
