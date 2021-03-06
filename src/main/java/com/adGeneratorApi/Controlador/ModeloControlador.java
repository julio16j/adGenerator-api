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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.ModeloDTO;
import com.adGeneratorApi.Dominio.DTO.VariacaoModeloDTO;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Servico.ModeloServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("modelo")
public class ModeloControlador {
	
	@Autowired
	ModeloServico servico;
	
	@Operation(summary = "Encontre todos os modelos")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Modelo>> obterModelos () {
		return ResponseEntity.ok(servico.listarTodos());
	}
	
	@Operation(summary = "Encontre Modelos pelo nome")
	@GetMapping("filtrar")
	public ResponseEntity<List<Modelo>> obterDescricaoValorPorFiltros(
			@RequestParam(value="nome", required=false) String nome) {
		return ResponseEntity.ok(servico.encontrarPorNome(nome));
	}
	
	@Operation(summary = "Encontre um modelo pelo Id")
	@GetMapping("{modeloId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Modelo> obterModeloPorId (@PathVariable("modeloId") String modeloId) {
		return ResponseEntity.ok(servico.encontrarPorId(modeloId));
	}
	
	@Operation(summary = "Criar um novo modelo")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Modelo> criarModelo (@RequestBody @Valid ModeloDTO novoModelo) {
		return ResponseEntity.ok(servico.cadastrarModelo(novoModelo));
	}
	
	@Operation(summary = "Deletar um modelo pelo Id")
	@DeleteMapping("{modeloId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@PathVariable String modeloId) {
		try {
			servico.delete(modeloId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Operation(summary = "Detalhar Modelos")
	@GetMapping("detalhar")
	public ResponseEntity<List<VariacaoModeloDTO>> detalharModelos () {
		try {
			return ResponseEntity.ok(servico.detalharModelos());
		} catch (Exception erro) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, erro.getMessage());
		}
	}
}
