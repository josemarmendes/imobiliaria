
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

import com.ifma.imobiliaria.model.Profissional;
import com.ifma.imobiliaria.repository.Profissionais;
import com.ifma.imobiliaria.repository.filter.ProfissionalFilter;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
	
	@Autowired
	private Profissionais profissionais;
	
	@GetMapping("/novo")
	public ModelAndView novo(Profissional profissional) {
		ModelAndView mv = new ModelAndView("profissionais/cadastro-profissionais");
		mv.addObject(profissional);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Profissional profissional, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(profissional);
		}
		profissionais.save(profissional);
		attributes.addFlashAttribute("mensagem", "Profissional salvo com sucesso!");
		return new ModelAndView("redirect:/profissionais/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ProfissionalFilter profissionalFilter) {
		ModelAndView mv = new ModelAndView("profissionais/pesquisa-profissionais");
		mv.addObject("profissionais", profissionais.findByNomeProfissionalContainingIgnoreCase(
				Optional.ofNullable(profissionalFilter.getNomeProfissional()).orElse("%")));
		return mv;
	}
	
	@GetMapping("/{idProfissional}")
	public ModelAndView editar(@PathVariable Long idProfissional) {
		Profissional profissional = profissionais.findOne(idProfissional);
		return novo(profissional);
	}
	
	@DeleteMapping("/{idProfissional}")
	public String apagar(@PathVariable Long idProfissional, RedirectAttributes attributes) {
		try {
			profissionais.delete(idProfissional);
			attributes.addFlashAttribute("mensagem", "Profissional removido com sucesso");
			return "redirect:/profissionais";
		} catch (DataIntegrityViolationException e) {
			attributes.addFlashAttribute("erro", "Profissional não pode ser removido");
			return "redirect:/profissionais";
		}
	}
	
}






