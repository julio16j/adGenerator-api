package com.adGeneratorApi.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adGeneratorApi.Dominio.Entidade.Cartao;

@Repository
public interface CartaoRepositorio extends JpaRepository<Cartao, String> {

}
