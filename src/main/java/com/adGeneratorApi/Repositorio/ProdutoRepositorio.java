package com.adGeneratorApi.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Enum.CategoriaProduto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, String> {
	@Query("SELECT p FROM Produto p "
			+ "WHERE (:nome IS NULL OR p.nome LIKE :nome || '%') "
			+ "AND (:descricao IS NULL OR p.descricao LIKE :descricao || '%') "
			+ "AND (:categoria IS NULL OR p.categoria = :categoria)")
	public List<Produto> findByFilters(@Param("nome") String nome, @Param("descricao") String descricao, @Param("categoria") CategoriaProduto categoria);
}
