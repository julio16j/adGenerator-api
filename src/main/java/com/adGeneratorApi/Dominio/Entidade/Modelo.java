package com.adGeneratorApi.Dominio.Entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.adGeneratorApi.Dominio.DTO.ModeloDTO;
import com.adGeneratorApi.Dominio.Embutivel.Cartao;
import com.adGeneratorApi.Dominio.Embutivel.Descricao;
import com.adGeneratorApi.Dominio.Embutivel.Imagem;
import com.adGeneratorApi.Dominio.Embutivel.Titulo;

@Entity
public class Modelo implements Serializable{
	
	private static final long serialVersionUID = -8939219162105153756L;

	@Id
	private String nome;
	
	@Embedded
	private Imagem imagem;
	
	@Embedded
	@Column
	private Titulo titulo;
	
	@Column
	@ElementCollection
	private List<Descricao> descricoes;
	
	@Column
	@ElementCollection
	private List<Cartao> cartoes;
	
	public Modelo () {}
	
	public Modelo(ModeloDTO dto) {
		nome = dto.getNome();
		imagem = dto.getImagem();
		titulo = dto.getTitulo();
		descricoes = dto.getDescricoes();
		cartoes = dto.getCartoes();
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public List<Descricao> getDescricoes() {
		return descricoes;
	}

	public void setDescricoes(List<Descricao> descricoes) {
		this.descricoes = descricoes;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartoes, descricoes, imagem, nome, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modelo other = (Modelo) obj;
		return Objects.equals(cartoes, other.cartoes) && Objects.equals(descricoes, other.descricoes)
				&& Objects.equals(imagem, other.imagem) && Objects.equals(nome, other.nome)
				&& Objects.equals(titulo, other.titulo);
	}

}
