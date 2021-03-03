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

import com.adGeneratorApi.Dominio.DTO.TituloDTO;
import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Entidade.Titulo;
import com.adGeneratorApi.Dominio.Enum.Tamanho;
import com.adGeneratorApi.Servico.TituloServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("titulo")
public class TituloControlador {
	@Autowired
	TituloServico servico;

	@Operation(summary = "Obter todos os títulos cadastrados")
	@GetMapping
	public ResponseEntity<List<Titulo>> obtertitulos() {
		return ResponseEntity.ok(servico.listarTodos());
	}

	@Operation(summary = "Encontre um titulo pelo Id")
	@GetMapping("{tituloId}")
	public ResponseEntity<Titulo> obterTituloPorId(@PathVariable("tituloId") String tituloId) {
		return ResponseEntity.ok(servico.encontrarPorId(tituloId));
	}

	@Operation(summary = "Encontre titulos por filtros")
	@GetMapping("filtrar")
	public ResponseEntity<List<Titulo>> obterDescricaoValorPorFiltros(
			@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam(value = "tamanho", required = false) Tamanho tamanho,
			@RequestParam(value = "produto", required = false) Produto produto) {
		return ResponseEntity.ok(servico.encontrarPorFiltros(descricao, tamanho, produto));
	}

	@Operation(summary = "Criar um novo titulo")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Titulo> criartitulo(@RequestBody @Valid TituloDTO novotitulo) {
		return ResponseEntity.ok(servico.cadastrartitulo(novotitulo));
	}

	@Operation(summary = "Editar um titulo existente")
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Titulo> editartitulo(@RequestBody @Valid TituloDTO novotitulo) {
		return ResponseEntity.ok(servico.editartitulo(novotitulo));
	}

	@Operation(summary = "Deletar um titulo pelo Id")
	@DeleteMapping("{tituloId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById(@PathVariable String tituloId) {
		try {
			servico.delete(tituloId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
