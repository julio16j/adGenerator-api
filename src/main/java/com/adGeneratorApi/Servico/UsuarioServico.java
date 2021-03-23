package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.UsuarioDTO;
import com.adGeneratorApi.Dominio.Entidade.Usuario;
import com.adGeneratorApi.Repositorio.UsuarioRepositorio;

@Component
public class UsuarioServico {
	
	@Autowired
	UsuarioRepositorio repositorio;
	
	public List<Usuario> listarTodos () {
		return repositorio.findAll();
	}
	
	public Usuario criarUsuario (UsuarioDTO dto) {
		validarUsuarioUnico(dto.getEmail());
		Usuario usuario = new Usuario(dto);
		Usuario usuarioSalvo = repositorio.save(usuario);
		return usuarioSalvo;
		
	}

	private void validarUsuarioUnico(String email) {
		Optional<Usuario> usuarioEncontrado = repositorio.findByEmail(email);
		if (usuarioEncontrado.isPresent()) throw new RuntimeException("Usuario já cadastrado");
		
	}

	public Usuario encontrarPorId(Long usuarioId) {
		Optional<Usuario> usuarioEncontrado = repositorio.findById(usuarioId);
		if (usuarioEncontrado.isEmpty()) throw new RuntimeException("Usuario não encontrado");
		return usuarioEncontrado.get();
	}

	public Usuario login(@Valid UsuarioDTO emailSenha) throws ResponseStatusException {
		Optional<Usuario> usuarioEncontrado = repositorio.findByEmail(emailSenha.getEmail());
		if (usuarioEncontrado.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Usuario não Encontrado");
		if (usuarioEncontrado.get().getSenha().equals(emailSenha.getSenha())) return usuarioEncontrado.get();
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ou Senha Incorretos");
	}
	
	

	public void delete(Long usuarioId) {
		this.repositorio.deleteById(usuarioId);
		
	}

	public List<Usuario> filtrarDivulgadores(String email, String nome) {
		return repositorio.filtrarDivulgadores(email, nome);
	}
	
	
}
