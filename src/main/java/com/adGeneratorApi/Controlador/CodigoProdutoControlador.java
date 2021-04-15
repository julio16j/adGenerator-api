package com.adGeneratorApi.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.Entidade.CodigoProduto;
import com.adGeneratorApi.Servico.CodigoProdutoServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("codigoProduto")
public class CodigoProdutoControlador {

	@Autowired
	CodigoProdutoServico servico;
	
	@Operation(summary = "Encontre um codigoProduto pelo Id")
	@GetMapping("{codigoProdutoId}")
	public ResponseEntity<CodigoProduto> obterCodigoProdutoPorId(@PathVariable("codigoProdutoId") Long codigoProdutoId) {
		return ResponseEntity.ok(servico.encontrarPorId(codigoProdutoId));
	}
	
	@Operation(summary = "Obter todos os codigoProdutos com produto null")
	@GetMapping
	public ResponseEntity<List<CodigoProduto>> obterCodigoComProdutoNull () {
		return ResponseEntity.ok(servico.listarTodosComProdutoNull());
	}
	
	@Operation(summary = "Criar um novo codigoProduto")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CodigoProduto> criarCodigoProduto () {
		try {
			servico.gerarCodigoProdutos();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (ResponseStatusException error) {
			throw error;
		}
	}
	
	@Operation(summary = "Deletar um codigoProduto pelo Id")
	@DeleteMapping("{codigoProdutoId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@PathVariable Long codigoProdutoId) {
		try {
			servico.delete(codigoProdutoId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
