package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.CodigoProdutoDTO;
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
	
	public CodigoProduto cadastrarCodigoProduto(CodigoProdutoDTO dto) {
		if (repositorio.findByCodigo(dto.getCodigo()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CodigoProduto já existente");
		
		CodigoProduto novoCodigoProduto = new CodigoProduto(dto);
		CodigoProduto codigoProdutoSalvo = repositorio.save(novoCodigoProduto);
		return codigoProdutoSalvo;
	}
	
	public void delete(Long id) {
		repositorio.deleteById(id);
	}
	
}
