package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.Entidade.CodigoProduto;
import com.adGeneratorApi.Repositorio.CodigoProdutoRepositorio;

@Component
public class CodigoProdutoServico {

	@Autowired
	CodigoProdutoRepositorio repositorio;
	
	public List<CodigoProduto> listarTodosComProdutoNull() {
		return repositorio.findByNullProduto();
	}
	
	public CodigoProduto encontrarPorId(Long codigoProdutoId) {
		Optional<CodigoProduto> codigoProdutoEncontrado = repositorio.findById(codigoProdutoId);
		if (codigoProdutoEncontrado.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CodigoProduto não encontrado");
		return codigoProdutoEncontrado.get();
	}
	
	public void gerarCodigoProdutos() {
		for(int i = 1; i < 100; i++) {
			CodigoProduto codigoProduto = new CodigoProduto();
			codigoProduto.setCodigo(String.valueOf(i));
			repositorio.save(codigoProduto);				
		}
	}
	
	public void delete(Long id) {
		repositorio.deleteAll();
	}
	
}
