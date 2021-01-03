package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Dominio.DTO.UsuarioDTO;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Dominio.Entidade.Usuario;
import com.adGeneratorApi.Repositorio.ModeloRepositorio;

@Component(value = "modeloServicoConcreto")
public class ModeloServico {

	@Autowired
	ModeloRepositorio repositorio;
	
	
	public List<Modelo> listarTodos () {
		return repositorio.findAll();
	}
	
	public Modelo criarModelo (Modelo modelo) {
		Modelo modeloSalvo = repositorio.save(modelo);
		return modeloSalvo;
	}

	public Modelo encontrarPorId(Long id) {
		Optional<Modelo> modeloEncontrado = repositorio.findById(id);
		if (modeloEncontrado.isEmpty()) throw new RuntimeException("Modelo não encontrado");
		return modeloEncontrado.get();
	}

//	public Modelo encontrarPorSegmento(String segmento) throws Exception {
//		Optional<Modelo> modeloEncontrado = repositorio.findAll(segmento);
//		if (modeloEncontrado.isEmpty()) throw new Exception("Usuario não Encontrado");
//		return modeloEncontrado.get();
//	}

	public void delete(Long usuarioId) {
		this.repositorio.deleteById(usuarioId);
		
	}
}
