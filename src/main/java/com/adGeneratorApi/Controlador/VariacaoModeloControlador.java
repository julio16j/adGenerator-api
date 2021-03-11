package com.adGeneratorApi.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.VariacaoModeloDTO;
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
	public ResponseEntity<Page<VariacaoModelo>> listarPaginado (@RequestParam(value="pagina", required=true) Integer pagina,
			   @RequestParam(value="tamanho", required=false) Integer tamanho,
			   @RequestParam(value="ordenarPor",required=false) Sort ordenarPor) {
		return ResponseEntity.ok(servico.listarTodos(pagina, tamanho, ordenarPor));
	}
	
	@Operation(summary = "Filtrar Todos")
	@GetMapping("filtrar")
	public ResponseEntity<Page<VariacaoModelo>> filtrarPaginado (@RequestParam(value="variacaoModelo", required=true) VariacaoModeloDTO filtroDTO) {
		return ResponseEntity.ok(servico.filtrar(filtroDTO));
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
	
}
