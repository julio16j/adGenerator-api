package com.adGeneratorApi.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, String> {
	

}
