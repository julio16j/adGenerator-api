package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;

import com.adGeneratorApi.Dominio.Entidade.ContaOlx;
import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;

public class AnuncioDTO implements Serializable {
	
	private static final long serialVersionUID = 1864505299361418488L;
	private VariacaoModelo variacaoModelo;
	private ContaOlx contaOlx;
	
	public VariacaoModelo getVariacaoModelo() {
		return variacaoModelo;
	}
	public void setVariacaoModelo(VariacaoModelo variacaoModelo) {
		this.variacaoModelo = variacaoModelo;
	}
	public ContaOlx getContaOlx() {
		return contaOlx;
	}
	public void setContaOlx(ContaOlx contaOlx) {
		this.contaOlx = contaOlx;
	}
}
