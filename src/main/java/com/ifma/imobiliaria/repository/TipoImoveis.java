package com.ifma.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ifma.imobiliaria.model.TipoImovel;

public interface TipoImoveis extends JpaRepository<TipoImovel, Long>{
	
	public List<TipoImovel> findByDescricaoContainingIgnoreCase(String descricao);
}
