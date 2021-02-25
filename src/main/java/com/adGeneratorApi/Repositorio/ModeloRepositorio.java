package com.adGeneratorApi.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Modelo;

@Repository
public interface ModeloRepositorio extends JpaRepository<Modelo, String> {
	
	@Query("SELECT m FROM Modelo m "
			+ "WHERE (:nome IS NULL OR m.nome LIKE  '%' || :nome || '%') ")
	List<Modelo> filtrarPorNome(@Param("nome") String nome);
	

}
