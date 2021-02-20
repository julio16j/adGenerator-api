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
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.UsuarioDTO;
import com.adGeneratorApi.Dominio.Entidade.Usuario;
import com.adGeneratorApi.Servico.UsuarioServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("usuario")
public class UsuarioControlador {
	
	@Autowired
	UsuarioServico servico;
	
	@Operation(summary = "Login de usuário")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Login bem sucedido",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Usuario.class)) }),
		@ApiResponse(responseCode = "400", description = "Usuário inválido",
				content = @Content),
		@ApiResponse(responseCode = "404", description = "Usuário não cadastrado",
				content = @Content),
	})
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
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuários encontrados",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Usuario.class)) }),
		@ApiResponse(responseCode = "404", description = "Nenhum usuário foi encontrado",
				content = @Content)
	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Usuario>> obterUsuarios () {
		return ResponseEntity.ok(servico.listarTodos());
	}
	
	@Operation(summary = "Encontre um usuário pelo Id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuário encontrado",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Usuario.class)) }),
		@ApiResponse(responseCode = "400", description = "Usuário inválido",
				content = @Content),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
				content = @Content),
	})
	@GetMapping("{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Usuario> obterUsuarioPorId (@PathVariable("usuarioId") Long usuarioId) {
		return ResponseEntity.ok(servico.encontrarPorId(usuarioId));
	}
	
	@Operation(summary = "Criar um novo usuário")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Usuário criado",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Usuario.class)) }),
		@ApiResponse(responseCode = "400", description = "Usuário inválido",
				content = @Content)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> criarUsuario (@RequestBody @Valid UsuarioDTO novoUsuario) {
		return ResponseEntity.ok(servico.criarUsuario(novoUsuario));
	}
	
	@Operation(summary = "Deletar um usuário pelo Id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuário deletado",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Usuario.class)) }),
		@ApiResponse(responseCode = "400", description = "Usuário inválido",
				content = @Content),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
				content = @Content),
	})
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@RequestBody Long usuarioId) {
		try {
			servico.delete(usuarioId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
