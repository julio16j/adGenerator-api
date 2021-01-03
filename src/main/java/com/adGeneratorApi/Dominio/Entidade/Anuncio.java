package com.adGeneratorApi.Dominio.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="codigoProduto", referencedColumnName = "idalfa")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name="usuarioEmail", referencedColumnName = "email")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="olxEmail", referencedColumnName = "email")
	private ContaOlx contaolx;
	
	@OneToOne
	@JoinColumn(name="idImagem", referencedColumnName = "id")
	private Imagem imagem;
	
	public Anuncio () {}
	

	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public ContaOlx getContaolx() {
		return contaolx;
	}


	public void setContaolx(ContaOlx contaolx) {
		this.contaolx = contaolx;
	}


	public Imagem getImagem() {
		return imagem;
	}


	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
