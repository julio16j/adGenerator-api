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
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.UsuarioDTO;
import com.adGeneratorApi.Dominio.Entidade.Usuario;
import com.adGeneratorApi.Servico.UsuarioServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("usuario")
public class UsuarioControlador {
	
	@Autowired
	UsuarioServico servico;
	
	@Operation(summary = "Login de usuário")
	@PostMapping("login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Usuario> login (@RequestBody UsuarioDTO emailSenha) {
		try {
			return ResponseEntity.ok(servico.login(emailSenha));
		} catch (ResponseStatusException e) {
			throw e;
		}
	}
	
	@Operation(summary = "Obter todos os usuários")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Usuario>> obterUsuarios () {
		return ResponseEntity.ok(servico.listarTodos());
	}
	
	@Operation(summary = "Encontre um usuário pelo Id")
	@GetMapping("{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Usuario> obterUsuarioPorId (@PathVariable("usuarioId") Long usuarioId) {
		return ResponseEntity.ok(servico.encontrarPorId(usuarioId));
	}
	
	@Operation(summary = "Filtrar divulgadores")
	@GetMapping("/divulgador")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Usuario>> filtrarDivulgadores (
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "nome", required = false) String nome) {
		return ResponseEntity.ok(servico.filtrarDivulgadores(email, nome));
	}
	
	@Operation(summary = "Criar um novo usuário")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> criarUsuario (@RequestBody @Valid UsuarioDTO novoUsuario) {
		return ResponseEntity.ok(servico.criarUsuario(novoUsuario));
	}
	
	@Operation(summary = "Deletar um usuário pelo Id")
	@DeleteMapping("{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@PathVariable Long usuarioId) {
		try {
			servico.delete(usuarioId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
