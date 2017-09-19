package com.ifma.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifma.imobiliaria.model.Imovel;

public interface Imoveis extends JpaRepository<Imovel, Long> {
	
	public List<Imovel> findByNomeImovelContainingIgnoreCase(String nomeImovel);
}
