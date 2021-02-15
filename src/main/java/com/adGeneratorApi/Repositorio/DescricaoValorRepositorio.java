package com.adGeneratorApi.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;

@Repository
public interface DescricaoValorRepositorio extends JpaRepository<DescricaoValor, String> {
	
}
