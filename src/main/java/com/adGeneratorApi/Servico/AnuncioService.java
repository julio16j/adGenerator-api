package com.adGeneratorApi.Servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Dominio.Entidade.Anuncio;
import com.adGeneratorApi.Repositorio.AnuncioRepositorio;

@Component(value = "anuncioServicoConcreto")
public class AnuncioService {
	@Autowired
	AnuncioRepositorio repositorio;
	
	public List<Anuncio> listarTodos() {
		return repositorio.findAll();
	}
}
