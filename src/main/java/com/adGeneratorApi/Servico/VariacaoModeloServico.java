package com.adGeneratorApi.Servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.adGeneratorApi.Dominio.Entidade.Cartao;
import com.adGeneratorApi.Dominio.Entidade.DescricaoValor;
import com.adGeneratorApi.Dominio.Entidade.VariacaoModelo;
import com.adGeneratorApi.Repositorio.VariacaoModeloRepositorio;

@Component
public class VariacaoModeloServico {
	
	@Autowired
	VariacaoModeloRepositorio repositorio;
	
	public Page<VariacaoModelo> listarTodos (Integer pagina, Integer tamanho, Sort sort) {
		sort = sort == null ? Sort.by("produto") : sort;
		tamanho = tamanho == null ? 5 : tamanho;
		return repositorio.listarTodosPaginado(PageRequest.of(pagina, tamanho, sort));
	}

	public String gerarChave(VariacaoModelo novaVariacao) {
		String chave = "";
		chave += novaVariacao.getModelo().getNome();
		chave += novaVariacao.getProduto().getNome();
		chave += novaVariacao.getTitulo().getDescricao();
		for (DescricaoValor descricao: novaVariacao.getDescricoes()) {
			chave += descricao.getDescricao();
		}
		for (Cartao cartao: novaVariacao.getCartoes()) {
			chave += cartao.getNome();
		}
		Optional<VariacaoModelo> variacaoEncontrada = repositorio.findById(chave);
		if (variacaoEncontrada.isPresent()) throw new RuntimeException("Chave já cadastrada");
		return chave;
	}
	
	
}
