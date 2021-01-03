package com.adGeneratorApi.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adGeneratorApi.Dominio.Entidade.Anuncio;

public interface AnuncioRepositorio extends JpaRepository<Anuncio, Long>{

}
