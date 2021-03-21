package com.adGeneratorApi.Controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adGeneratorApi.Dominio.DTO.TemaCorDTO;
import com.adGeneratorApi.Dominio.Entidade.TemaCor;
import com.adGeneratorApi.Servico.TemaCorServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("temaCor")
public class TemaCorControlador {
	
	@Autowired
	TemaCorServico servico;
	
	@Operation(summary = "Encontre um temaCor pelo Id")
	@GetMapping("{temaCorId}")
	public ResponseEntity<TemaCor> obterTemaCorPorId (@PathVariable("temaCorId") Long temaCorId) {
		return ResponseEntity.ok(servico.encontrarPorId(temaCorId));
	}
	
	@Operation(summary = "Obter todos os temaCors cadastrados")
	@GetMapping
	public ResponseEntity<List<TemaCor>> obterTemaCors () {
		return ResponseEntity.ok(servico.listarTodos());
	}

	@Operation(summary = "Encontre um temaCor com filtro")
	@GetMapping("filtrar")
	public ResponseEntity<List<TemaCor>> obterTemaCorPorFiltros(
			@RequestParam(value="corFundo", required=false) String corFundo, 
			@RequestParam(value="corFonte", required=false) String corFonte) {
		return ResponseEntity.ok(servico.encontrarPorFiltros(corFundo, corFonte));
	}
	
	@Operation(summary = "Criar um novo temaCor")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TemaCor> criarTemaCor (@RequestBody @Valid TemaCorDTO novoTemaCor) {
		return ResponseEntity.ok(servico.cadastrarTemaCor(novoTemaCor));
	}
	
	@Operation(summary = "Editar um temaCor existente")
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TemaCor> editarTemaCor (@RequestBody @Valid TemaCor novoTemaCor) {
		return ResponseEntity.ok(servico.editarTemaCor(novoTemaCor));
	}
	
	@Operation(summary = "Deletar um temaCor pelo Id")
	@DeleteMapping("{temaCorId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@PathVariable Long temaCorId) {
		try {
			servico.delete(temaCorId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
