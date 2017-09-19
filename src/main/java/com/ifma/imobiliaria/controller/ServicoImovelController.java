
package com.ifma.imobiliaria.controller;

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

import com.ifma.imobiliaria.model.ServicoImovel;
import com.ifma.imobiliaria.repository.Imoveis;
import com.ifma.imobiliaria.repository.Profissionais;
import com.ifma.imobiliaria.repository.ServicoImoveis;
import com.ifma.imobiliaria.repository.filter.ServicoImovelFilter;

@Controller
@RequestMapping("/servicoimoveis")
public class ServicoImovelController {
	
	@Autowired
	private ServicoImoveis servicoImoveis;
	
	@Autowired
	private Imoveis imoveis;
	
	@Autowired
	private Profissionais profissionais;
	
	@GetMapping("/novo")
	public ModelAndView novo(ServicoImovel servicoImovel) {
		ModelAndView mv = new ModelAndView("servicoimoveis/cadastro-servicoimoveis");
		mv.addObject(servicoImovel);
		mv.addObject("imoveis", imoveis.findAll());
		mv.addObject("profissionais", profissionais.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid ServicoImovel servicoImovel, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(servicoImovel);
		}
		servicoImoveis.save(servicoImovel);
		attributes.addFlashAttribute("mensagem", "Serviço salvo com sucesso!");
		return new ModelAndView("redirect:/servicoimoveis/novo");
	}
	
	/*@GetMapping
	public ModelAndView pesquisar(ServicoImovelFilter servicoImovelFilter) {
		ModelAndView mv = new ModelAndView("servicoimoveis/pesquisa-servicoimoveis");
		mv.addObject("servicoimoveis", servicoImoveis.findAll());
		return mv;
	}*/
	
	@GetMapping("/{idServicoImovel}")
	public ModelAndView editar(@PathVariable Long idServicoImovel) {
		ServicoImovel servicoImovel = servicoImoveis.findOne(idServicoImovel);
		return novo(servicoImovel);
	}
	
	@DeleteMapping("/{idServicoImovel}")
	public String apagar(@PathVariable Long idServicoImovel, RedirectAttributes attributes) {
		servicoImoveis.delete(idServicoImovel);
		attributes.addFlashAttribute("mensagem", "Serviço removido com sucesso");
		return "redirect:/servicoimoveis";
	}
	
}






