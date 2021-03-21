package com.adGeneratorApi.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.TemaCor;
import com.adGeneratorApi.Dominio.Entidade.Usuario;

@Repository
public interface TemaCorRepositorio extends JpaRepository<TemaCor, Long> {
	@Query("SELECT d FROM TemaCor d "
			+ "WHERE (:corFundo IS NULL OR d.corFundo LIKE :corFundo || '%') "
			+ "AND (:corFonte IS NULL OR d.corFonte LIKE :corFonte || '%') ")
	public List<TemaCor> findByFilters(@Param("corFundo") String corFundo, @Param("corFonte") String corFonte);
	
	public Optional<Usuario> findByCorFundo (String corFundo);
}