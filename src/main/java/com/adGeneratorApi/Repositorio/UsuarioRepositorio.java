package com.adGeneratorApi.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail (String email);
}
