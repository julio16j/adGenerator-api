package com.adGeneratorApi.Repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;

@Repository
public interface VariacaoModeloRepositorio extends JpaRepository<VariacaoModelo, String> {
	
	@Query("Select variacao from VariacaoModelo variacao where "
			+ "(:modeloId is null or variacao.modelo.nome like '%' || :modeloId || '%') and "
			+ "(:tituloId is null or variacao.titulo.descricao like '%' || :tituloId || '%') and "
			+ "(:produtoId is null or variacao.produto.titulo like :produtoId || '%' ) ")
	public Page<VariacaoModelo> filtrarPaginado (@Param("modeloId") String modeloId,
												 @Param("tituloId") String tituloId, 
												 @Param("produtoId") String produtoId,
												 Pageable page);
	
	@Query("Select variacao from VariacaoModelo variacao "
			+ "left join Anuncio a "
			+ "on a.variacaoModelo.chave = variacao.chave "
			+ "where a.variacaoModelo.chave is null and "
			+ "(:produtoId is null or variacao.produto.titulo like :produtoId || '%')")
	public Page<VariacaoModelo> findVariacaoSemAnuncio(@Param("produtoId") String produtoId, 
													   Pageable page);
}
