package com.adGeneratorApi.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.DTO.ContaOlxDTO;
import com.adGeneratorApi.Dominio.Entidade.ContaOlx;
import com.adGeneratorApi.Dominio.Enum.StatusUso;
import com.adGeneratorApi.Repositorio.ContaOlxRepositorio;

@Component
public class ContaOlxServico {
	
	@Autowired
	ContaOlxRepositorio repositorio;
	
	public List<ContaOlx> listarTodos () {
		return repositorio.findAll();
	}
	
	public ContaOlx cadastrarContaOlx (ContaOlxDTO dto) {
		if (repositorio.findByEmail(dto.getEmail()).isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "ContaOlx já existente");
		
		ContaOlx novoContaOlx = new ContaOlx(dto);
		ContaOlx contaOlxSalvo = repositorio.save(novoContaOlx);
		return contaOlxSalvo;
	}
	
	public ContaOlx editarContaOlx (ContaOlx dto) {
		ContaOlx contaOlxEncontrado = encontrarPorId(dto.getId()) ;
		contaOlxEncontrado.setSenha(dto.getSenha());
		contaOlxEncontrado.setEmail(dto.getEmail());
		contaOlxEncontrado.setUltimaVezUtilizada(dto.getUltimaVezUtilizada());
		ContaOlx contaOlxSalvo = repositorio.save(contaOlxEncontrado);
		return contaOlxSalvo;
	}

	public ContaOlx encontrarPorId(Long id) {
		Optional<ContaOlx> contaOlxEncontrado = repositorio.findById(id);
		if (contaOlxEncontrado.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND , "ContaOlx não Encontrada");
		return contaOlxEncontrado.get();
	}
	
	public List<ContaOlx> encontrarPorFiltros(String email, StatusUso status) {
		return repositorio.findByFilters(email, status);
	}
	
	public void delete(Long id) {
		repositorio.deleteById(id);
		
	}

	public ContaOlx obterContaOlxDisponivel() {
		return repositorio.obterContaOlxDisponivel().get(0);
	}
	
	
}
