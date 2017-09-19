
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

import com.ifma.imobiliaria.model.Locacao;
import com.ifma.imobiliaria.repository.Clientes;
import com.ifma.imobiliaria.repository.Imoveis;
import com.ifma.imobiliaria.repository.Locacoes;
import com.ifma.imobiliaria.repository.Profissionais;
import com.ifma.imobiliaria.repository.filter.LocacaoFilter;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {
	
	@Autowired
	private Locacoes locacoes;
	
	@Autowired
	private Imoveis imoveis;
	
	@Autowired
	private Clientes clientes;
	
	@Autowired
	private Profissionais profissionais;
	
	@GetMapping("/novo")
	public ModelAndView novo(Locacao locacao) {
		ModelAndView mv = new ModelAndView("locacoes/cadastro-locacoes");
		mv.addObject(locacao);
		mv.addObject("clientes", clientes.findAll());
		mv.addObject("imoveis", imoveis.findAll());
		mv.addObject("profissionais", profissionais.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Locacao locacao, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(locacao);
		}
		locacoes.save(locacao);
		attributes.addFlashAttribute("mensagem", "Locação salva com sucesso!");
		return new ModelAndView("redirect:/locacoes/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(LocacaoFilter locacaoFilter) {
		ModelAndView mv = new ModelAndView("locacoes/pesquisa-locacoes");
		mv.addObject("locacoes", locacoes.findByResponsavelPagamentoContainingIgnoreCase(
				Optional.ofNullable(locacaoFilter.getResponsavelPagamento()).orElse("%")));
		return mv;
	}
	
	@GetMapping("/{idLocacao}")
	public ModelAndView editar(@PathVariable Long idLocacao) {
		Locacao locacao = locacoes.findOne(idLocacao);
		return novo(locacao);
	}
	
	@DeleteMapping("/{idLocacao}")
	public String apagar(@PathVariable Long idLocacao, RedirectAttributes attributes) {
		locacoes.delete(idLocacao);
		attributes.addFlashAttribute("mensagem", "Locação removida com sucesso");
		return "redirect:/locacoes";
	}
	
}






