package com.ifma.imobiliaria.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifma.imobiliaria.model.TipoImovel;
import com.ifma.imobiliaria.repository.TipoImoveis;
import com.ifma.imobiliaria.repository.filter.TipoImovelFilter;

@Controller
@RequestMapping("/tipoImoveis")
public class TipoImovelController {
	
	@Autowired
	private TipoImoveis tipoImoveis;

	@GetMapping("/novo")
	public ModelAndView novo(TipoImovel tipoImovel) {
		ModelAndView mv = new ModelAndView("tipoImoveis/cadastro-tipoImoveis");
		mv.addObject(tipoImovel);
		return mv;
	}
		
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid TipoImovel tipoImovel, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(tipoImovel);
		}
			
		tipoImoveis.save(tipoImovel);
		attributes.addFlashAttribute("mensagem", "Tipo Imóvel salvo com sucesso!");
		return new ModelAndView("redirect:/tipoImoveis/novo");
	}
		
	@GetMapping
	public ModelAndView pesquisar(TipoImovelFilter tipoImovelFilter) {
		ModelAndView mv = new ModelAndView("tipoImoveis/pesquisa-tipoImoveis");
		mv.addObject("tipoImoveis", tipoImoveis.findByDescricaoContainingIgnoreCase(
			Optional.ofNullable(tipoImovelFilter.getDescricao()).orElse("%")));
		return mv;
	}
		
	@GetMapping("/{idTipoImovel}")
	public ModelAndView editar(@PathVariable Long idTipoImovel) {
		TipoImovel tipoImovel = tipoImoveis.findOne(idTipoImovel);
		return novo(tipoImovel);
	}
		
	@DeleteMapping("/{idTipoImovel}")
	public String apagar(@PathVariable Long idTipoImovel, RedirectAttributes attributes) {
		tipoImoveis.delete(idTipoImovel);
		attributes.addFlashAttribute("mensagem", "Imovél removido com sucesso");
		return "redirect:/tipoImoveis";
	}
		
}

