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

import com.adGeneratorApi.Dominio.DTO.ContaOlxDTO;
import com.adGeneratorApi.Dominio.Entidade.ContaOlx;
import com.adGeneratorApi.Dominio.Enum.StatusUso;
import com.adGeneratorApi.Servico.ContaOlxServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("contaOlx")
public class ContaOlxControlador {
	
	@Autowired
	ContaOlxServico servico;
	
	@Operation(summary = "Encontre um contaOlx pelo Id")
	@GetMapping("{contaOlxId}")
	public ResponseEntity<ContaOlx> obterContaOlxPorId (@PathVariable("contaOlxId") Long contaOlxId) {
		return ResponseEntity.ok(servico.encontrarPorId(contaOlxId));
	}
	
	@Operation(summary = "Obter todos os contaOlxs cadastrados")
	@GetMapping
	public ResponseEntity<List<ContaOlx>> obterContaOlxs () {
		return ResponseEntity.ok(servico.listarTodos());
	}

	@Operation(summary = "Encontre um contaOlx com filtro")
	@GetMapping("filtrar")
	public ResponseEntity<List<ContaOlx>> obterContaOlxPorFiltros(
			@RequestParam(value="email", required=false) String email, 
			@RequestParam(value="status", required=false) StatusUso status) {
		return ResponseEntity.ok(servico.encontrarPorFiltros(email, status));
	}
	
	@Operation(summary = "Criar um novo contaOlx")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ContaOlx> criarContaOlx (@RequestBody @Valid ContaOlxDTO novoContaOlx) {
		return ResponseEntity.ok(servico.cadastrarContaOlx(novoContaOlx));
	}
	
	@Operation(summary = "Editar um contaOlx existente")
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ContaOlx> editarContaOlx (@RequestBody @Valid ContaOlx novoContaOlx) {
		return ResponseEntity.ok(servico.editarContaOlx(novoContaOlx));
	}
	
	@Operation(summary = "Deletar um contaOlx pelo Id")
	@DeleteMapping("{contaOlxId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@PathVariable Long contaOlxId) {
		try {
			servico.delete(contaOlxId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
