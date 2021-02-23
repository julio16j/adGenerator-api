package com.adGeneratorApi.Dominio.Enum;

public enum CategoriaProduto {
	
	Eletronicos ("Eletrônicos"), Mercado ("Mercado"), Utensilios ("Utensílios"), Tecidos ("Tecidos");
	
	private String descricao;
	
	CategoriaProduto (String string) {
		this.descricao = string;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
