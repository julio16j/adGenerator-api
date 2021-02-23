package com.adGeneratorApi.Servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Dominio.Entidade.Titulo;
import com.adGeneratorApi.Repositorio.TituloRepositorio;

@Component
public class TituloServico {
	@Autowired
	TituloRepositorio repositorio;
	
	public List<Titulo> listarTodos () {
		return repositorio.findAll();
	}
}
