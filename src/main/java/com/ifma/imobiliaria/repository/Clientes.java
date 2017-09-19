package com.ifma.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifma.imobiliaria.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long>{
	
	public List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
