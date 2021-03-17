package com.adGeneratorApi.Dominio.DTO;

import java.util.List;

import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Entidade.Titulo;
import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;

public class SetupVariacaoDTO {

	List<VariacaoModelo> variacoes;
	List<DescricaoValor> descricoes;
	List<Cartao> cartoes;
	Modelo modelo;
	Integer numeroDescricoes;
	Integer numeroCartoes;
	Produto produto;
	Titulo titulo;
	
	public SetupVariacaoDTO() {}
	
	public SetupVariacaoDTO(List<VariacaoModelo> variacoes, List<DescricaoValor> descricoes, List<Cartao> cartoes,
			Modelo modelo, Integer numeroDescricoes, Integer numeroCartoes, Produto produto, Titulo titulo) {
		this.variacoes = variacoes;
		this.descricoes = descricoes;
		this.cartoes = cartoes;
		this.modelo = modelo;
		this.numeroDescricoes = numeroDescricoes;
		this.numeroCartoes = numeroCartoes;
		this.produto = produto;
		this.titulo = titulo;
	}
	
	public List<VariacaoModelo> getVariacoes() {
		return variacoes;
	}
	public void setVariacoes(List<VariacaoModelo> variacoes) {
		this.variacoes = variacoes;
	}
	public List<DescricaoValor> getDescricoes() {
		return descricoes;
	}
	public void setDescricoes(List<DescricaoValor> descricoes) {
		this.descricoes = descricoes;
	}
	public List<Cartao> getCartoes() {
		return cartoes;
	}
	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Integer getNumeroDescricoes() {
		return numeroDescricoes;
	}
	public void setNumeroDescricoes(Integer numeroDescricoes) {
		this.numeroDescricoes = numeroDescricoes;
	}
	public Integer getNumeroCartoes() {
		return numeroCartoes;
	}
	public void setNumeroCartoes(Integer numeroCartoes) {
		this.numeroCartoes = numeroCartoes;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Titulo getTitulo() {
		return titulo;
	}
	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
}
