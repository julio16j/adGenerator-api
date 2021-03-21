package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.DescricaoValorDTO;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Enum.CategoriaDescricao;
import com.adGeneratorApi.Dominio.Enum.Tamanho;
import com.adGeneratorApi.Repositorio.DescricaoValorRepositorio;

@Component
public class DescricaoValorServico {
	
	@Autowired
	DescricaoValorRepositorio repositorio;
	
	public List<DescricaoValor> listarTodos () {
		return repositorio.findAll();
	}
	
	public DescricaoValor cadastrarDescricaoValor (DescricaoValorDTO dto) {
		if (repositorio.findById(dto.getDescricao()).isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "DescricaoValor já existente");
		
		DescricaoValor novoDescricaoValor = new DescricaoValor(dto);
		DescricaoValor descricaoValorSalvo = repositorio.save(novoDescricaoValor);
		return descricaoValorSalvo;
	}
	
	public DescricaoValor editarDescricaoValor (DescricaoValorDTO dto) {
		if (encontrarPorId(dto.getDescricao()) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND , "DescricaoValor não Encontrada");
		
		DescricaoValor novoDescricaoValor = new DescricaoValor(dto);
		DescricaoValor descricaoValorSalvo = repositorio.save(novoDescricaoValor);
		return descricaoValorSalvo;
	}

	public DescricaoValor encontrarPorId(String descricaoValorId) {
		Optional<DescricaoValor> descricaoValorEncontrado = repositorio.findById(descricaoValorId);
		if (descricaoValorEncontrado.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND , "DescricaoValor não Encontrada");
		return descricaoValorEncontrado.get();
	}
	
	public List<DescricaoValor> encontrarPorFiltros(String descricao, CategoriaDescricao categoria, Tamanho tamanho) {
		return repositorio.findByFilters(descricao, categoria, tamanho);
	}
	
	public void delete(String descricaoValorId) {
		repositorio.deleteById(descricaoValorId);
		
	}
	
	
}
