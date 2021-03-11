package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.converters.models.Pageable;
import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Entidade.Titulo;

public class VariacaoModeloDTO extends Pageable implements Serializable {
	
	private static final long serialVersionUID = -2787726937528515283L;
	private Modelo modelo;
	private Titulo titulo;
	private Produto produto;
	private List<DescricaoValor> descricoes;
	private List<Cartao> cartoes;
	
	public VariacaoModeloDTO () {
		super(0, 5, new ArrayList<>());
	};
	public VariacaoModeloDTO(int page, int size, List<String> sort) {
		super(page, size, sort);
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
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
