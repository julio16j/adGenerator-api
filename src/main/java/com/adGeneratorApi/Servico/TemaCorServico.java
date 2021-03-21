package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.TemaCorDTO;
import com.adGeneratorApi.Dominio.Entidade.TemaCor;
import com.adGeneratorApi.Repositorio.TemaCorRepositorio;

@Component
public class TemaCorServico {
	
	@Autowired
	TemaCorRepositorio repositorio;
	
	public List<TemaCor> listarTodos () {
		return repositorio.findAll();
	}
	
	public TemaCor cadastrarTemaCor (TemaCorDTO dto) {
		if (repositorio.findByCorFundo(dto.getCorFundo()).isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "TemaCor já existente");
		
		TemaCor novoTemaCor = new TemaCor(dto);
		TemaCor temaCorSalvo = repositorio.save(novoTemaCor);
		return temaCorSalvo;
	}
	
	public TemaCor editarTemaCor (TemaCor dto) {
		TemaCor temaCorEncontrado = encontrarPorId(dto.getId()) ;
		temaCorEncontrado.setCorFonte(dto.getCorFonte());
		temaCorEncontrado.setCorFundo(dto.getCorFundo());
		TemaCor temaCorSalvo = repositorio.save(temaCorEncontrado);
		return temaCorSalvo;
	}

	public TemaCor encontrarPorId(Long id) {
		Optional<TemaCor> temaCorEncontrado = repositorio.findById(id);
		if (temaCorEncontrado.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND , "TemaCor não Encontrada");
		return temaCorEncontrado.get();
	}
	
	public List<TemaCor> encontrarPorFiltros(String corFundo, String corFonte) {
		return repositorio.findByFilters(corFundo, corFonte);
	}
	
	public void delete(Long id) {
		repositorio.deleteById(id);
		
	}
	
	
}
