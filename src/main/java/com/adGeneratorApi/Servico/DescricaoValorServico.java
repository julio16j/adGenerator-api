package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Dominio.DTO.DescricaoValorDTO;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Repositorio.DescricaoValorRepositorio;

@Component
public class DescricaoValorServico {
	
	@Autowired
	DescricaoValorRepositorio repositorio;
	
	public List<DescricaoValor> listarTodos () {
		return repositorio.findAll();
	}
	
	public DescricaoValor cadastrarDescricaoValor (DescricaoValorDTO dto) {
		encontrarPorId(dto.getDescricao());
		DescricaoValor novoDescricaoValor = new DescricaoValor(dto);
		DescricaoValor descricaoValorSalvo = repositorio.save(novoDescricaoValor);
		return descricaoValorSalvo;
		
	}

	public DescricaoValor encontrarPorId(String descricaoValorId) {
		Optional<DescricaoValor> descricaoValorEncontrado = repositorio.findById(descricaoValorId);
		if (descricaoValorEncontrado.isEmpty()) throw new RuntimeException("DescricaoValor não encontrado");
		return descricaoValorEncontrado.get();
	}
	
	public void delete(String descricaoValorId) {
		repositorio.deleteById(descricaoValorId);
		
	}
	
	
}
