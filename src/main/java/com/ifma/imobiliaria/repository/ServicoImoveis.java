package com.ifma.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifma.imobiliaria.model.ServicoImovel;

public interface ServicoImoveis extends JpaRepository<ServicoImovel, Long> {
	
	public List<ServicoImovel> findAll();
}
