package com.adGeneratorApi.Repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.DTO.VariacaoModeloDTO;
import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;

@Repository
public interface VariacaoModeloRepositorio extends JpaRepository<VariacaoModelo, String> {
	
	@Query("Select variacao from VariacaoModelo variacao where "
			+ "(:#{#filtroDTO.modelo} is null or variacao.modelo.nome like '%' || :#{#filtroDTO.modelo.nome} || '%') and "
			+ "(:#{#filtroDTO.titulo} is null or variacao.titulo.descricao like '%' || :#{#filtroDTO.titulo.descricao} || '%') and "
			+ "(:#{#filtroDTO.produto} is null or variacao.produto.nome like '%' || :#{#filtroDTO.produto.nome} || '%') ")
	public Page<VariacaoModelo> filtrarPaginado ( @Param("filtroDTO") VariacaoModeloDTO filtroDTO, Pageable page);
}
