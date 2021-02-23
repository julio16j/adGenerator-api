package com.adGeneratorApi.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.adGeneratorApi.Dominio.Entidade.Titulo;
import com.adGeneratorApi.Servico.TituloServico;

import io.swagger.v3.oas.annotations.Operation;

public class TituloControlador {
	@Autowired
	TituloServico servico;
	
	@Operation(summary = "Obter todos os títulos cadastrados")
	@GetMapping
	public ResponseEntity<List<Titulo>> obterDescricaoValors () {
		return ResponseEntity.ok(servico.listarTodos());
	}
}
