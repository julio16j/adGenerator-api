package com.adGeneratorApi.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;
import com.adGeneratorApi.Servico.VariacaoModeloServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("variacaoModelo")
public class VariacaoModeloControlador {
	
	@Autowired
	VariacaoModeloServico servico;
	
	@Operation(summary = "Listar Todos")
	@GetMapping("todos")
	public ResponseEntity<Page<VariacaoModelo>> listarPaginado (@RequestParam(value="pagina", required=false) Integer pagina,
			   @RequestParam(value="tamanho", required=false) Integer tamanho,
			   @RequestParam(value="ordenarPor",required=false) Sort ordenarPor) {
		return ResponseEntity.ok(servico.listarTodos(pagina, tamanho, ordenarPor));
	}
	
	@Operation(summary = "Filtrar Todos")
	@GetMapping("filtrar")
	public ResponseEntity<Page<VariacaoModelo>> filtrarPaginado (
			@RequestParam(value="modeloId", required=false) String modeloId,
			@RequestParam(value="produtoId", required=false) String produtoId,
			@RequestParam(value="tituloId", required=false) String tituloId,
			@RequestParam(value="page", required=true) Integer page,
			@RequestParam(value="size", required=true) Integer size) {
		return ResponseEntity.ok(servico.filtrar(modeloId, produtoId, tituloId, page, size));
	}
	
	@Operation(summary = "Filtrar Todos")
	@GetMapping("semAnuncio")
	public ResponseEntity<Page<VariacaoModelo>> listarVariacaoSemAnuncio (
			@RequestParam(value="produtoId", required=false) String produtoId,
			@RequestParam(value="page", required=true) Integer page,
			@RequestParam(value="size", required=true) Integer size) {
		return ResponseEntity.ok(servico.findVariacaoSemAnuncio(produtoId, page, size));
	}
	
	@Operation(summary = "Encontrar por Id")
	@GetMapping("{chave}")
	public ResponseEntity<VariacaoModelo> encontrarPorId(@PathVariable("chave") String chave) {
		return ResponseEntity.ok(servico.encontrarPorId(chave));
	}
	
	@Operation(summary = "Gerar Variacoes")
	@PostMapping
	public ResponseEntity<Object> gerarVariacoes() {
		try {
			servico.gerarVariacoes();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (ResponseStatusException error) {
			throw error;
		}
		
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteAll () {
		try {
			servico.deleteAll();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception error) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage());
		}
	}
	
	@DeleteMapping("{chave}")
	public ResponseEntity<?> deleteById (@PathVariable String chave) {
		try {
			servico.deleteByChave(chave);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception error) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error.getMessage());
		}
	}
	
}
