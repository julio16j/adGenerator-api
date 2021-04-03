package com.adGeneratorApi.Repositorio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
	@Query("SELECT p FROM Produto p "
			+ "WHERE (:titulo IS NULL OR p.titulo LIKE :titulo || '%') "
			+ "AND (:descricao IS NULL OR p.descricao LIKE :descricao || '%') "
			+ "AND (:preco IS NULL OR p.preco <= :preco)")
	public List<Produto> findByFilters(@Param("titulo") String titulo, @Param("descricao") String descricao, @Param("preco") BigDecimal preco);
	
	public Optional<Produto> findByTitulo(String titulo);
}
