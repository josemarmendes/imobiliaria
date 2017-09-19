package com.ifma.imobiliaria.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifma.imobiliaria.model.Cliente;
import com.ifma.imobiliaria.repository.Clientes;
import com.ifma.imobiliaria.repository.filter.ClienteFilter;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private Clientes clientes;


	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/cadastro-cliente");
		mv.addObject(cliente);
		return mv;
	}
		
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
			
		clientes.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		return new ModelAndView("redirect:/clientes/novo");
	}
		
	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter) {
		ModelAndView mv = new ModelAndView("cliente/pesquisa-clientes");
		mv.addObject("clientes", clientes.findByNomeContainingIgnoreCase(
			Optional.ofNullable(clienteFilter.getNome()).orElse("%")));
		return mv;
	}
		
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Cliente cliente = clientes.findOne(codigo);
		return novo(cliente);
	}
		
	@DeleteMapping("/{codigo}")
	public String apagar(@PathVariable Long codigo, RedirectAttributes attributes) {
		
			try {
				clientes.delete(codigo);
				attributes.addFlashAttribute("mensagem", "Cliente removido com sucesso");
				return "redirect:/clientes";
			} catch (DataIntegrityViolationException e) {
				
				attributes.addFlashAttribute("erro", "Cliente não pode ser removido");
				return "redirect:/clientes";
			}
		
	}
		
}

