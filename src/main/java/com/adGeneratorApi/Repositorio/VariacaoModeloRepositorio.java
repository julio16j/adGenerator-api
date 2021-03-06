package com.adGeneratorApi.Repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;

@Repository
public interface VariacaoModeloRepositorio extends JpaRepository<VariacaoModelo, String> {
	
	@Query("Select variacao from VariacaoModelo variacao JOIN variacao.cartoes JOIN variacao.descricoes")
	public Page<VariacaoModelo> listarTodosPaginado (Pageable page);
}
