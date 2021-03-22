package com.adGeneratorApi.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.ContaOlx;
import com.adGeneratorApi.Dominio.Enum.StatusUso;

@Repository
public interface ContaOlxRepositorio extends JpaRepository<ContaOlx, Long> {
	@Query("SELECT d FROM ContaOlx d "
			+ "WHERE (:email IS NULL OR d.email LIKE :email || '%') "
			+ "AND (:status  IS NULL OR d.status LIKE :status || '%') ")
	public List<ContaOlx> findByFilters(@Param("email") String email, @Param("status") StatusUso status);
	
	public Optional<ContaOlx> findByEmail (String email);
}