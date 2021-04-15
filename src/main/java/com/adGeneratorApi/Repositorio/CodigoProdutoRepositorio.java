package com.adGeneratorApi.Repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.CodigoProduto;

@Repository
public interface CodigoProdutoRepositorio extends JpaRepository<CodigoProduto, Long> {

	@Query("SELECT c FROM CodigoProduto c WHERE c.produto IS NULL")
	public List<CodigoProduto> findByNullProduto();
	
	public Optional<CodigoProduto> findByCodigo(String codigo);
	
}
