package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Dominio.DTO.UsuarioDTO;
import com.adGeneratorApi.Dominio.Entidade.Usuario;
import com.adGeneratorApi.Repositorio.UsuarioRepositorio;

@Component(value = "usuarioServicoConcreto")
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

	public Usuario login(@Valid UsuarioDTO emailSenha) throws Exception {
		Optional<Usuario> usuarioEncontrado = repositorio.findByEmail(emailSenha.getEmail());
		if (usuarioEncontrado.isEmpty()) throw new Exception("Usuario não Encontrado");
		if (usuarioEncontrado.get().getSenha().equals(emailSenha.getSenha())) return usuarioEncontrado.get();
		throw new Exception("Email ou Senha Incorretos");
	}

	public void delete(Long usuarioId) {
		this.repositorio.deleteById(usuarioId);
		
	}
	
	
}
