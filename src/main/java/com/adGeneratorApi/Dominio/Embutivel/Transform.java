package com.adGeneratorApi.Dominio.Embutivel;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Transform implements Serializable {
	
	private static final long serialVersionUID = -2626836039171666351L;

	@Column(columnDefinition = "varchar(80)")
	private String rotate;
	
	@Column(columnDefinition = "varchar(80)")
	private String scale;
	
	@Column(columnDefinition = "varchar(80)")
	private String translate;
	
	public String getRotate() {
		return rotate;
	}
	public void setRotate(String rotate) {
		this.rotate = rotate;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getTranslate() {
		return translate;
	}
	public void setTranslate(String translate) {
		this.translate = translate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(rotate, scale, translate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transform other = (Transform) obj;
		return Objects.equals(rotate, other.rotate) && Objects.equals(scale, other.scale)
				&& Objects.equals(translate, other.translate);
	}
	
}
