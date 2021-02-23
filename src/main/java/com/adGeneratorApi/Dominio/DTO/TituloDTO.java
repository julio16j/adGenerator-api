package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;
import java.util.Objects;

import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Enum.Tamanho;

public class TituloDTO implements Serializable {
	private static final long serialVersionUID = 5479812992182441322L;
	private String descricao;
	private Tamanho tamanho;
	private Produto produto;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Tamanho getTamanho() {
		return tamanho;
	}
	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(descricao, tamanho, produto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TituloDTO other = (TituloDTO) obj;
		return Objects.equals(descricao, other.descricao) && tamanho == other.tamanho && produto == other.produto;
	}
}
