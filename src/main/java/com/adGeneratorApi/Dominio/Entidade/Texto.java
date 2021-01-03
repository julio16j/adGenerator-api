package com.adGeneratorApi.Dominio.Entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Texto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "FontFamily")
	private String fontfamily;
	
	@Column(name = "FontSize")
	private String fontsize;
	
	@Column(name = "FontColor")
	private String fontcolor;
	
	@Column(name = "positionX")
	private String positionx;
	
	@Column(name = "positionY")
	private String positiony;
}
