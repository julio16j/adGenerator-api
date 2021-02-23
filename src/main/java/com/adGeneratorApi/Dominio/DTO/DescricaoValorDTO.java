package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;
import java.util.Objects;

import com.adGeneratorApi.Dominio.Enum.CategoriaDescricao;
import com.adGeneratorApi.Dominio.Enum.Tamanho;

public class DescricaoValorDTO implements Serializable {
	
	private static final long serialVersionUID = 5479812992182441322L;
	private String descricao;
	private CategoriaDescricao categoria;
	private Tamanho tamanho;
	
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
	@Override
	public int hashCode() {
		return Objects.hash(categoria, descricao, tamanho);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DescricaoValorDTO other = (DescricaoValorDTO) obj;
		return categoria == other.categoria && Objects.equals(descricao, other.descricao) && tamanho == other.tamanho;
	}
	
}
