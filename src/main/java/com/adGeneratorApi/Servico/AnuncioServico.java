package com.adGeneratorApi.Servico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.AnuncioDTO;
import com.adGeneratorApi.Dominio.DTO.AtivarAnuncioDTO;
import com.adGeneratorApi.Dominio.Entidade.Anuncio;
import com.adGeneratorApi.Dominio.Enum.AnuncioStatus;
import com.adGeneratorApi.Repositorio.AnuncioRepositorio;

@Component
public class AnuncioServico {
	
	@Autowired
	AnuncioRepositorio repositorio;
	
	public List<Anuncio> listarTodos () {
		return repositorio.findAll();
	}
	
	public Anuncio cadastrarAnuncio (AnuncioDTO dto) {
		if (repositorio.findByVariacaoModelo(dto.getVariacaoModelo()).isPresent()) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Anuncio já existente");
		
		Anuncio novoAnuncio = new Anuncio(dto);
		Anuncio anuncioSalvo = repositorio.save(novoAnuncio);
		return anuncioSalvo;
	}
	
	public Anuncio ativarAnuncio (AtivarAnuncioDTO dto) {
		Optional<Anuncio> oldAnuncio = repositorio.findById(dto.getId());
		if (oldAnuncio.isPresent()) {
			Anuncio anuncio = oldAnuncio.get();
			anuncio.setId(dto.getId());
			anuncio.setLink(dto.getLink());
			anuncio.setStatus(AnuncioStatus.Ativo);
			repositorio.save(anuncio);
			return anuncio;
		}
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Anuncio inexistente");
	}

	public Anuncio encontrarPorId(Long id) {
		Optional<Anuncio> anuncioEncontrado = repositorio.findById(id);
		if (anuncioEncontrado.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Anuncio não Encontrada");
		return anuncioEncontrado.get();
	}
	
	public List<Anuncio> encontrarPorFiltros(String variacaoModeloChave, String contaOlxEmail, String dataInicial, String dataFinal, Long usuarioId) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime localDateInicial, localDateFinal;
		
		if (dataInicial == null || dataInicial.isEmpty())
			localDateInicial = LocalDateTime.MIN;
		else {
			LocalDate data = LocalDate.parse(dataInicial, dtf);
			localDateInicial = data.atTime(0, 0);			
		}
		
		if (dataFinal == null || dataFinal.isEmpty())
			localDateFinal = LocalDateTime.of(9999, 12, 31, 0, 0);
		else {
			LocalDate data = LocalDate.parse(dataFinal, dtf);
			localDateFinal = data.atTime(23, 59);			
		}

		return repositorio.findByFilters(variacaoModeloChave, contaOlxEmail, localDateInicial, localDateFinal, usuarioId);
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
