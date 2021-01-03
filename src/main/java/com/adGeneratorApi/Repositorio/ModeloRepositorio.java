package com.adGeneratorApi.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adGeneratorApi.Dominio.Entidade.Modelo;

public interface ModeloRepositorio extends JpaRepository<Modelo, Long> {

}
