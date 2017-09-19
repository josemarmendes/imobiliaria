package com.ifma.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifma.imobiliaria.model.Profissional;

public interface Profissionais extends JpaRepository<Profissional, Long> {
	
	public List<Profissional> findByNomeProfissionalContainingIgnoreCase(String nomeProfissional);
}
