package com.adGeneratorApi.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Titulo;

@Repository
public interface TituloRepositorio extends JpaRepository<Titulo, String> {

}
