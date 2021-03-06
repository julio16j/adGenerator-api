package com.adGeneratorApi.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;
import com.adGeneratorApi.Servico.VariacaoModeloServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("variacaoModelo")
public class VariacaoModeloControlador {
	
	@Autowired
	VariacaoModeloServico servico;
	
	@Operation(summary = "Listar Com Paginação")
	@GetMapping
	public ResponseEntity<Page<VariacaoModelo>> listarPaginado(@RequestParam(value="pagina", required=false) Integer pagina,
															   @RequestParam(value="tamanho", required=false) Integer tamanho,
															   @RequestParam(value="ordenarPor",required=false) Sort ordenarPor) {
		return ResponseEntity.ok(servico.listarTodos(pagina, tamanho, ordenarPor));
	}
}
