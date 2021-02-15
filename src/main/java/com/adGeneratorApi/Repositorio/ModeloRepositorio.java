package com.adGeneratorApi.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Modelo;

@Repository
public interface ModeloRepositorio extends JpaRepository<Modelo, String> {
	

}
