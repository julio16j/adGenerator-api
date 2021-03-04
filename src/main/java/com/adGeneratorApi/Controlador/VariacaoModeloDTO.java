package com.adGeneratorApi.Controlador;

import java.io.Serializable;
import java.util.List;

import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Dominio.Entidade.Titulo;

public class VariacaoModeloDTO implements Serializable {
	
	private static final long serialVersionUID = -2787726937528515283L;
	private Modelo modelo;
	private Titulo titulo;
	private List<DescricaoValor> descricoes;
	private List<Cartao> cartoes;
	
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Titulo getTitulo() {
		return titulo;
	}
	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
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
}
