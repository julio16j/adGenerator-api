package com.adGeneratorApi.Controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adGeneratorApi.Dominio.Entidade.Modelo;
import com.adGeneratorApi.Servico.ModeloServico;

@CrossOrigin
@RestController
@RequestMapping("usuario")
public class ModeloControlador {
	
	@Autowired
	ModeloServico servico;
	
	@PostMapping
	public ResponseEntity<Modelo> criarModelo (@RequestBody @Valid Modelo novoModelo) {
		return ResponseEntity.ok(servico.criarModelo(novoModelo));
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteById (@RequestBody Long modeloId) {
		try {
			servico.delete(modeloId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
