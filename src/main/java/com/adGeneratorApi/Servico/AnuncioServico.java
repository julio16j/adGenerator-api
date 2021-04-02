package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.AnuncioDTO;
import com.adGeneratorApi.Dominio.Entidade.Anuncio;
import com.adGeneratorApi.Repositorio.AnuncioRepositorio;

@Component
public class AnuncioServico {
	
	@Autowired
	AnuncioRepositorio repositorio;
	
	public List<Anuncio> listarTodos () {
		return repositorio.findAll();
	}
	
	public Anuncio cadastrarAnuncio (AnuncioDTO dto) {
		if (repositorio.findByVariacaoModelo(dto.getVariacaoModelo()).isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Anuncio já existente");
		
		Anuncio novoAnuncio = new Anuncio(dto);
		Anuncio anuncioSalvo = repositorio.save(novoAnuncio);
		return anuncioSalvo;
	}

	public Anuncio encontrarPorId(Long id) {
		Optional<Anuncio> anuncioEncontrado = repositorio.findById(id);
		if (anuncioEncontrado.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Anuncio não Encontrada");
		return anuncioEncontrado.get();
	}
	
	public List<Anuncio> encontrarPorFiltros(String variacaoModeloChave, Long anuncioId) {
		return repositorio.findByFilters(variacaoModeloChave, anuncioId);
	}
	
	public void delete(Long id) {
		repositorio.deleteById(id);
		
	}

	public Integer obterTotalAnunciosMes(Long divulgadorId) {
		return repositorio.totalAnuncioPorMes(divulgadorId);
	}

	public Integer obterTotalAnunciosDia(Long divulgadorId) {
		return repositorio.totalAnuncioPorDia(divulgadorId);
	}
	
	public Integer obterTotalAnunciosContaOlx(String emailOlx) {
		return repositorio.totalAnuncioPorContaOlx(emailOlx);
	}
	
	
}
