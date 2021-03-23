package com.adGeneratorApi.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Anuncio;
import com.adGeneratorApi.Dominio.Entidade.ContaOlx;
import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;

@Repository
public interface AnuncioRepositorio extends JpaRepository<Anuncio, Long> {
	@Query("SELECT d FROM Anuncio d "
			+ "WHERE (:variacaoModeloChave IS NULL OR d.variacaoModelo.chave = :chave) "
			+ "AND (:contaOlxId  IS NULL OR d.contaOlx.id = :contaOlxId) ")
	public List<Anuncio> findByFilters(@Param("variacaoModeloChave") String variacaoModeloChave, @Param("contaOlxId") Long contaOlxId);
	
	public List<Anuncio> findByContaOlx (ContaOlx contaOlx);
	
	public Optional<Anuncio> findByVariacaoModelo (VariacaoModelo variacaoModelo);
}