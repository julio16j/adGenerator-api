package com.adGeneratorApi.Repositorio;

import java.time.LocalDateTime;
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
			+ "WHERE (:variacaoModeloChave IS NULL OR d.variacaoModelo.chave LIKE :variacaoModeloChave || '%') "
			+ "AND (:contaOlxEmail IS NULL OR d.contaOlx.email LIKE :contaOlxEmail || '%') "
			+ "AND (d.dataPostado BETWEEN :dataInicial AND :dataFinal) "
			+ "AND :usuarioId = d.usuarioDivulgador.id")
	public List<Anuncio> findByFilters(@Param("variacaoModeloChave") String variacaoModeloChave, 
									   @Param("contaOlxEmail") String contaOlxEmail,
									   @Param("dataInicial") LocalDateTime dataInicial,
									   @Param("dataFinal") LocalDateTime dataFinal,
									   @Param("usuarioId") Long usuarioId);
	
	@Query("SELECT count(a.id) from Anuncio a "
			+ "where a.usuarioDivulgador.id = :usuarioId "
			+ "AND function('date_format',a.dataPostado, '%m') = function('date_format',a.dataPostado, '%m')")
	public Integer totalAnuncioPorMes (@Param("usuarioId") Long usuarioId );
	
	@Query("SELECT count(a.id) from Anuncio a "
			+ "where a.usuarioDivulgador.id = :usuarioId "
			+ "AND function('date_format',a.dataPostado, '%d-%m-%y') = function('date_format',CURRENT_DATE, '%d-%m-%y')")
	public Integer totalAnuncioPorDia (@Param("usuarioId") Long usuarioId );
	
	public List<Anuncio> findByContaOlx (ContaOlx contaOlx);
	
	public Optional<Anuncio> findByVariacaoModelo (VariacaoModelo variacaoModelo);
}