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

import com.adGeneratorApi.Dominio.DTO.AnuncioDTO;
import com.adGeneratorApi.Dominio.Entidade.Anuncio;
import com.adGeneratorApi.Servico.AnuncioServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("anuncio")
public class AnuncioControlador {
	
	@Autowired
	AnuncioServico servico;
	
	@Operation(summary = "Encontre um anuncio pelo Id")
	@GetMapping("{anuncioId}")
	public ResponseEntity<Anuncio> obterAnuncioPorId (@PathVariable("anuncioId") Long anuncioId) {
		return ResponseEntity.ok(servico.encontrarPorId(anuncioId));
	}
	
	@Operation(summary = "Obter todos os anuncios cadastrados")
	@GetMapping
	public ResponseEntity<List<Anuncio>> obterAnuncios () {
		return ResponseEntity.ok(servico.listarTodos());
	}

	@Operation(summary = "Encontre um anuncio com filtro")
	@GetMapping("filtrar")
	public ResponseEntity<List<Anuncio>> obterAnuncioPorFiltros(
			@RequestParam(value="variacaoModeloChave", required=false) String variacaoModeloChave, 
			@RequestParam(value="contaOlxId", required=false) Long contaOlxId) {
		return ResponseEntity.ok(servico.encontrarPorFiltros(variacaoModeloChave, contaOlxId));
	}
	
	@Operation(summary = "Criar um novo anuncio")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Anuncio> criarAnuncio (@RequestBody @Valid AnuncioDTO novoAnuncio) {
		return ResponseEntity.ok(servico.cadastrarAnuncio(novoAnuncio));
	}
	
	@Operation(summary = "Deletar um anuncio pelo Id")
	@DeleteMapping("{anuncioId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@PathVariable Long anuncioId) {
		try {
			servico.delete(anuncioId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}