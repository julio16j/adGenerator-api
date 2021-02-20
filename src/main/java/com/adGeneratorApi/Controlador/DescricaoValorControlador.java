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

import com.adGeneratorApi.Dominio.DTO.DescricaoValorDTO;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Enum.Categoria;
import com.adGeneratorApi.Dominio.Enum.Tamanho;
import com.adGeneratorApi.Servico.DescricaoValorServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("descricaoValor")
public class DescricaoValorControlador {
	
	@Autowired
	DescricaoValorServico servico;
	
	@Operation(summary = "Encontre um descricaoValor pelo Id")
	@GetMapping("{descricaoValorId}")
	public ResponseEntity<DescricaoValor> obterDescricaoValorPorId (@PathVariable("descricaoValorId") String descricaoValorId) {
		return ResponseEntity.ok(servico.encontrarPorId(descricaoValorId));
	}
	
	@Operation(summary = "Obter todos os descricaoValors cadastrados")
	@GetMapping
	public ResponseEntity<List<DescricaoValor>> obterDescricaoValors () {
		return ResponseEntity.ok(servico.listarTodos());
	}

	@Operation(summary = "Encontre um descricaoValor com filtro")
	@GetMapping("filtrar")
	public ResponseEntity<List<DescricaoValor>> obterDescricaoValorPorFiltros(
			@RequestParam(value="descricao", required=false) String descricao, 
			@RequestParam(value="categoria", required=false) Categoria categoria, 
			@RequestParam(value="tamanho", required=false) Tamanho tamanho) {
		return ResponseEntity.ok(servico.encontrarPorFiltros(descricao, categoria, tamanho));
	}
	
	@Operation(summary = "Criar um novo descricaoValor")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DescricaoValor> criarDescricaoValor (@RequestBody @Valid DescricaoValorDTO novoDescricaoValor) {
		return ResponseEntity.ok(servico.cadastrarDescricaoValor(novoDescricaoValor));
	}
	
	@Operation(summary = "Editar um descricaoValor existente")
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DescricaoValor> editarDescricaoValor (@RequestBody @Valid DescricaoValorDTO novoDescricaoValor) {
		return ResponseEntity.ok(servico.editarDescricaoValor(novoDescricaoValor));
	}
	
	@Operation(summary = "Deletar um descricaoValor pelo Id")
	@DeleteMapping("{descricaoValorId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@PathVariable String descricaoValorId) {
		try {
			servico.delete(descricaoValorId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
