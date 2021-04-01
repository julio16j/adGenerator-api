package com.adGeneratorApi.Dominio.DTO;

import java.io.Serializable;

public class AtivarAnuncioDTO implements Serializable {

	private static final long serialVersionUID = 5479812992182441322L;
	private Long id;
	private String link;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

}
