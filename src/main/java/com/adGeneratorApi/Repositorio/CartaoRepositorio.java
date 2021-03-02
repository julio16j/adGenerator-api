package com.adGeneratorApi.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Cartao;

@Repository
public interface CartaoRepositorio extends JpaRepository<Cartao, String> {
	@Query("SELECT c FROM Cartao c WHERE :nome IS NULL OR c.nome LIKE :nome || '%' ")
	public List<Cartao> findByFilters(@Param("nome") String nome);
}
