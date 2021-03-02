package com.adGeneratorApi.Controlador;

import java.io.IOException;
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
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.CartaoDTO;
import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Servico.CartaoServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("cartao")
public class CartaoControlador {
	@Autowired
	CartaoServico servico;
	
	@Operation(summary = "Encontre todos os Cartaos")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Cartao>> obterCartoes () {
		return ResponseEntity.ok(servico.listarTodos());
	}
	
	@Operation(summary = "Encontre um Cartão pelo Id")
	@GetMapping("{cartaoId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Cartao> obterCartaoPorId (@PathVariable("cartaoId") String cartaoId) {
		return ResponseEntity.ok(servico.encontrarPorId(cartaoId));
	}
	
	@Operation(summary = "Encontre um descricaoValor com filtro")
	@GetMapping("filtrar")
	public ResponseEntity<List<Cartao>> obterCartaoPorFiltros(@RequestParam(value="nome", required=false) String nome) {
		return ResponseEntity.ok(servico.encontrarPorFiltros(nome));
	}
	
	@Operation(summary = "Criar um novo Cartão")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cartao> criarCartao (@RequestBody @Valid CartaoDTO novoCartao) {
		try {
			return ResponseEntity.ok(servico.cadastrarCartao(novoCartao));
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao Salvar a imagem" + e.getMessage());
		}
	}
	
	@Operation(summary = "Editar um Cartão")
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Cartao> editarCartao (@RequestBody @Valid CartaoDTO novoCartao) {
		try {
			return ResponseEntity.ok(servico.editarCartao(novoCartao));
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao Salvar a imagem" + e.getMessage());
		}
	}
	
	@Operation(summary = "Deletar um Cartão pelo Id")
	@DeleteMapping("{cartaoId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@PathVariable String cartaoId) {
		try {
			servico.delete(cartaoId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
