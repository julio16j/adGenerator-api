package com.adGeneratorApi.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail (String email);

	@Query("SELECT t FROM Usuario t "
            + "WHERE (:email IS NULL OR t.email LIKE :email || '%') "
            + "AND (:nome IS NULL OR t.nome LIKE :nome || '%') "
            + "AND t.isAdmin = false or t.isAdmin is null")
	public List<Usuario> filtrarDivulgadores(@Param("email") String email, @Param("nome") String nome);
}
