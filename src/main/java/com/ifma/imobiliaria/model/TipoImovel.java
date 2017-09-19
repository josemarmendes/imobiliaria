package com.ifma.imobiliaria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class TipoImovel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoImovel;
	
	@NotEmpty
	private String descricao;

	public Long getIdTipoImovel() {
		return idTipoImovel;
	}
	public void setIdTipoImovel(Long idTipoImovel) {
		this.idTipoImovel = idTipoImovel;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoImovel == null) ? 0 : idTipoImovel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoImovel other = (TipoImovel) obj;
		if (idTipoImovel == null) {
			if (other.idTipoImovel != null)
				return false;
		} else if (!idTipoImovel.equals(other.idTipoImovel))
			return false;
		return true;
	}
	
}
