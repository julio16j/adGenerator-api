package com.adGeneratorApi.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.adGeneratorApi.Dominio.Entidade.Produto;
import com.adGeneratorApi.Dominio.Enum.CategoriaProduto;
import com.adGeneratorApi.Servico.ProdutoServico;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("produto")
public class ProdutoControlador {
	
	@Autowired
	ProdutoServico servico;
	
	@Operation(summary = "Encontre todos os produtos")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Produto>> obterProdutos () {
		return ResponseEntity.ok(servico.listarTodos());
	}
	
	@Operation(summary = "Encontre um produto pelo Id")
	@GetMapping("{produtoId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Produto> obterProdutoPorId (@PathVariable("produtoId") String produtoId) {
		return ResponseEntity.ok(servico.encontrarPorId(produtoId));
	}
	
	@Operation(summary = "Encontre produtos por filtros")
	@GetMapping("filtrar")
	public ResponseEntity<List<Produto>> obterDescricaoValorPorFiltros(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam(value = "categoria", required = false) CategoriaProduto categoria) {
		return ResponseEntity.ok(servico.encontrarPorFiltros(nome, descricao, categoria));
	}
	
	@Operation(summary = "Criar um novo produto")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> criarProduto (@RequestPart("novoProduto") String novoProduto,
			@RequestPart("imagemProduto") MultipartFile imagemProduto) {
		try {
			return ResponseEntity.ok(servico.cadastrarProduto(novoProduto, imagemProduto));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao Salvar a imagem" + e.getMessage());
		}
	}
	
	@Operation(summary = "Criar um novo produto")
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Produto> editarProduto (@RequestPart("produto") String novoProduto,
			@RequestPart("imagemProduto") MultipartFile imagemProduto) {
		try {
			return ResponseEntity.ok(servico.editarProduto(novoProduto, imagemProduto));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao Salvar a imagem" + e.getMessage());
		}
	}
	
	@Operation(summary = "Deletar um produto pelo Id")
	@DeleteMapping("{produtoId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteById (@PathVariable String produtoId) {
		try {
			servico.delete(produtoId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
