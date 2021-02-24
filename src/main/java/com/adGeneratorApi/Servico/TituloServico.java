package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Dominio.DTO.TituloDTO;
import com.adGeneratorApi.Dominio.Entidade.Titulo;
import com.adGeneratorApi.Repositorio.TituloRepositorio;

@Component
public class TituloServico {
	
	@Autowired
	TituloRepositorio repositorio;
	
	public List<Titulo> listarTodos () {
		return repositorio.findAll();
	}
	
	public Titulo cadastrartitulo (TituloDTO dto) {
		if (repositorio.findById(dto.getDescricao()).isPresent()) throw new RuntimeException("titulo já existente");
		
		Titulo novoTitulo = new Titulo(dto);
		Titulo tituloSalvo = repositorio.save(novoTitulo);
		return tituloSalvo;
	}
	
	public Titulo editartitulo (TituloDTO dto) {
		if (encontrarPorId(dto.getDescricao()) == null) throw new RuntimeException("titulo não encontrado");
		
		Titulo novoTitulo = new Titulo(dto);
		Titulo tituloSalvo = repositorio.save(novoTitulo);
		return tituloSalvo;
	}

	public Titulo encontrarPorId(String tituloId) {
		Optional<Titulo> tituloEncontrado = repositorio.findById(tituloId);
		if (tituloEncontrado.isEmpty()) throw new RuntimeException("titulo não encontrado");
		return tituloEncontrado.get();
	}
	
	public void delete(String tituloId) {
		repositorio.deleteById(tituloId);	
	}
}
