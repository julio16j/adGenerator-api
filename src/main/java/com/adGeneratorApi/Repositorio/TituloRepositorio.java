package com.adGeneratorApi.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.adGeneratorApi.Dominio.Entidade.Titulo;
import com.adGeneratorApi.Dominio.Enum.Tamanho;

@Repository
public interface TituloRepositorio extends JpaRepository<Titulo, String> {
    @Query("SELECT t FROM Titulo t "
            + "WHERE (:descricao IS NULL OR t.descricao LIKE :descricao || '%')"
            + "AND (t.tamanho = :tamanho OR :tamanho IS NULL)"
            + "AND (t.produto.titulo = :produtoTitulo OR :produtoTitulo IS NULL)")
    public List<Titulo> findByFilters(@Param("descricao") String descricao, 
    								  @Param("tamanho") Tamanho tamanho, 
    								  @Param("produtoTitulo") String produtoTitulo);
}
