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
import org.springframework.web.bind.annotation.RestController;

import com.adGeneratorApi.Dominio.DTO.ModeloDTO;
import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Servico.ModeloServico;

@CrossOrigin
@RestController
@RequestMapping("modelo")
public class ModeloControlador {
	
	@Autowired
	ModeloServico servico;
	
	@GetMapping
	public ResponseEntity<List<Modelo>> obterModelos () {
		return ResponseEntity.ok(servico.listarTodos());
	}
	
	
	@GetMapping("{modeloId}")
	public ResponseEntity<Modelo> obterModeloPorId (@PathVariable("modeloId") String modeloId) {
		return ResponseEntity.ok(servico.encontrarPorId(modeloId));
	}
	
	@PostMapping
	public ResponseEntity<Modelo> criarModelo (@RequestBody @Valid ModeloDTO novoModelo) {
		return ResponseEntity.ok(servico.cadastrarModelo(novoModelo));
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> deleteById (@RequestBody String modeloId) {
		try {
			servico.delete(modeloId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
