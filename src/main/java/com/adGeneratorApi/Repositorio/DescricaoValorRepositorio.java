package com.adGeneratorApi.Repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Enum.Categoria;
import com.adGeneratorApi.Dominio.Enum.Tamanho;

@Repository
public interface DescricaoValorRepositorio extends JpaRepository<DescricaoValor, String> {
	@Query("SELECT d FROM DescricaoValor d "
			+ "WHERE (:descricao IS NULL OR d.descricao LIKE :descricao || '%') "
			+ "AND (d.categoria = :categoria OR :categoria IS NULL) "
			+ "AND (d.tamanho = :tamanho OR :tamanho IS NULL)")
	public List<DescricaoValor> findByFilters(@Param("descricao") String descricao, @Param("categoria") Categoria categoria, @Param("tamanho") Tamanho tamanho);
}
