package com.ifma.imobiliaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifma.imobiliaria.model.Imovel;
import com.ifma.imobiliaria.repository.Imoveis;

@Service
public class ImovelService {
	
	@Autowired
	Imoveis imoveis;
	
	@Transactional
	public void salva(Imovel imovel){
		
		imoveis.save(imovel);
	
	}
}
