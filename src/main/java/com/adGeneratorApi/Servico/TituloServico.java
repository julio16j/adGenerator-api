package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.TituloDTO;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Entidade.Titulo;
import com.adGeneratorApi.Dominio.Enum.Tamanho;
import com.adGeneratorApi.Repositorio.TituloRepositorio;

@Component
public class TituloServico {

	@Autowired
	TituloRepositorio repositorio;
	
	@Autowired
	ProdutoServico produtoServico;

	public List<Titulo> listarTodos() {
		return repositorio.findAll();
	}

	public Titulo cadastrartitulo(TituloDTO dto) {
		if (repositorio.findById(dto.getDescricao()).isPresent())
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED ,"titulo já existente");
		Produto produtoEncontrado = produtoServico.encontrarPorTitulo(dto.getProdutoTitulo());
		Titulo novoTitulo = new Titulo(dto);
		novoTitulo.setProduto(produtoEncontrado);
		Titulo tituloSalvo = repositorio.save(novoTitulo);
		return tituloSalvo;
	}

	public Titulo editartitulo(TituloDTO dto) {
		encontrarPorId(dto.getDescricao());
		Titulo novoTitulo = new Titulo(dto);
		Produto produtoEncontrado = produtoServico.encontrarPorTitulo(dto.getProdutoTitulo());
		novoTitulo.setProduto(produtoEncontrado);
		Titulo tituloSalvo = repositorio.save(novoTitulo);
		return tituloSalvo;
	}

	public List<Titulo> encontrarPorFiltros(String descricao, Tamanho tamanho, String produtoTitulo) {
		return repositorio.findByFilters(descricao, tamanho, produtoTitulo);
	}

	public Titulo encontrarPorId(String tituloId) {
		Optional<Titulo> tituloEncontrado = repositorio.findById(tituloId);
		if (tituloEncontrado.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"titulo não encontrado");
		return tituloEncontrado.get();
	}

	public void delete(String tituloId) {
		repositorio.deleteById(tituloId);
	}
}
