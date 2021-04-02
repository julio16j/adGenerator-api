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

import com.adGeneratorApi.Dominio.DTO.AnuncioDTO;
import com.adGeneratorApi.Dominio.DTO.AtivarAnuncioDTO;
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
			@RequestParam(value="contaOlxEmail", required=false) String contaOlxId,
			@RequestParam(value="dataInicial", required=false) String dataInicial,
			@RequestParam(value="dataFinal", required=false) String dataFinal,
			@RequestParam(value="usuarioId", required=true) Long usuarioId) {
		return ResponseEntity.ok(servico.encontrarPorFiltros(variacaoModeloChave, contaOlxId, dataInicial, dataFinal, usuarioId));
	}
	
	@Operation(summary = "Criar um novo anuncio")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Anuncio> criarAnuncio (@RequestBody @Valid AnuncioDTO novoAnuncio) {
		return ResponseEntity.ok(servico.cadastrarAnuncio(novoAnuncio));
	}
	
	@Operation(summary = "Editar um anuncio")
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Anuncio> ativarAnuncio (@RequestBody @Valid AtivarAnuncioDTO novoAnuncio) {
		return ResponseEntity.ok(servico.ativarAnuncio(novoAnuncio));
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
	
	@Operation(summary = "Obter total anuncios no dia por divulgador")
	@GetMapping("/dia/{divulgadorId}")
	public ResponseEntity<Integer> obterTotalAnunciosDia (@PathVariable Long divulgadorId) {
		return ResponseEntity.ok(servico.obterTotalAnunciosDia(divulgadorId));
	}
	
	@Operation(summary = "Obter total anuncios no mês por divulgador")
	@GetMapping("/mes/{divulgadorId}")
	public ResponseEntity<Integer> obterTotalAnunciosMes (@PathVariable Long divulgadorId) {
		return ResponseEntity.ok(servico.obterTotalAnunciosMes(divulgadorId));
	}
	
	@Operation(summary = "Obter total anuncios pelo Email Olx")
	@GetMapping("/contaOlx/{emailOlx}")
	public ResponseEntity<Integer> obterAnunciosContaOlx (@PathVariable String emailOlx) {
		return ResponseEntity.ok(servico.obterTotalAnunciosContaOlx(emailOlx));
	}
}
