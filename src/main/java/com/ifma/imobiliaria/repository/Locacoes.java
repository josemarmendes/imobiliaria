package com.ifma.imobiliaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifma.imobiliaria.model.Locacao;

public interface Locacoes extends JpaRepository<Locacao, Long> {
	
	public List<Locacao> findByResponsavelPagamentoContainingIgnoreCase(String responsavelPagamento);
}
