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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adGeneratorApi.Dominio.DTO.ModeloDTO;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Servico.ModeloServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("modelo")
public class ModeloControlador {
	
	@Autowired
	ModeloServico servico;
	
	@Operation(summary = "Encontre todos os modelos")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Modelos encontrados",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Modelo.class)) }),
		@ApiResponse(responseCode = "404", description = "Nenhum modelo foi encontrado",
				content = @Content)
	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Modelo>> obterModelos () {
		return ResponseEntity.ok(servico.listarTodos());
	}
	
	@Operation(summary = "Encontre um modelo pelo Id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Modelo encontrado",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Modelo.class)) }),
		@ApiResponse(responseCode = "400", description = "Modelo inválido",
				content = @Content),
		@ApiResponse(responseCode = "404", description = "Modelo não encontrado",
				content = @Content),
	})
	@GetMapping("{modeloId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Modelo> obterModeloPorId (@PathVariable("modeloId") String modeloId) {
		return ResponseEntity.ok(servico.encontrarPorId(modeloId));
	}
	
	@Operation(summary = "Criar um novo modelo")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Modelo criado",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Modelo.class)) }),
		@ApiResponse(responseCode = "400", description = "DescricaoValor inválido",
				content = @Content)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Modelo> criarModelo (@RequestBody @Valid ModeloDTO novoModelo) {
		return ResponseEntity.ok(servico.cadastrarModelo(novoModelo));
	}
	
	@Operation(summary = "Deletar um modelo pelo Id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Modelo deletado",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Modelo.class)) }),
		@ApiResponse(responseCode = "400", description = "Modelo inválido",
				content = @Content),
		@ApiResponse(responseCode = "404", description = "Modelo não encontrado",
				content = @Content),
	})
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@RequestBody String modeloId) {
		try {
			servico.delete(modeloId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
