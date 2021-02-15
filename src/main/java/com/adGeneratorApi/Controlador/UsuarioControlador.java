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
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.UsuarioDTO;
import com.adGeneratorApi.Dominio.Entidade.Usuario;
import com.adGeneratorApi.Servico.UsuarioServico;

@CrossOrigin
@RestController
@RequestMapping("usuario")
public class UsuarioControlador {
	
	@Autowired
	UsuarioServico servico;
	
	@PostMapping("login")
	public ResponseEntity<Usuario> login (@RequestBody UsuarioDTO emailSenha) {
		try {
			return ResponseEntity.ok(servico.login(emailSenha));
		} catch (ResponseStatusException e) {
			throw e;
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> obterUsuarios () {
		return ResponseEntity.ok(servico.listarTodos());
	}
	
	
	@GetMapping("{usuarioId}")
	public ResponseEntity<Usuario> obterUsuarioPorId (@PathVariable("usuarioId") Long usuarioId) {
		return ResponseEntity.ok(servico.encontrarPorId(usuarioId));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario (@RequestBody @Valid UsuarioDTO novoUsuario) {
		return ResponseEntity.ok(servico.criarUsuario(novoUsuario));
	}
	
	
	@DeleteMapping
	public ResponseEntity<?> deleteById (@RequestBody Long usuarioId) {
		try {
			servico.delete(usuarioId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
