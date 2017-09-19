
package com.ifma.imobiliaria.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifma.imobiliaria.model.Imovel;
import com.ifma.imobiliaria.repository.Clientes;
import com.ifma.imobiliaria.repository.Imoveis;
import com.ifma.imobiliaria.repository.TipoImoveis;
import com.ifma.imobiliaria.repository.filter.ImovelFilter;
import com.ifma.imobiliaria.service.ImovelService;

@Controller
@RequestMapping("/imoveis")
public class ImoveisController {

	@Autowired
	private Imoveis imoveis;
	
	@Autowired
	private Clientes clientes;
	
	@Autowired
	ImovelService imovelService;
	
	@Autowired
	TipoImoveis tipoImoveis;
	
	
	 @Autowired
	 private ApplicationContext appContext;
	
	

	@GetMapping("/novo")
	public ModelAndView novo(Imovel imovel) {
		ModelAndView mv = new ModelAndView("imoveis/cadastro-imoveis");
		mv.addObject(imovel);
		mv.addObject("clientes", clientes.findAll());
		mv.addObject("tipoImoveis", tipoImoveis.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Imovel imovel, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(imovel);
		}
		imovelService.salva(imovel);
		attributes.addFlashAttribute("mensagem", "Imovél salvo com sucesso!");
		return new ModelAndView("redirect:/imoveis/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ImovelFilter imovelFilter) {
		ModelAndView mv = new ModelAndView("imoveis/pesquisa-imoveis");
		mv.addObject("imoveis", imoveis.findByNomeImovelContainingIgnoreCase(
				Optional.ofNullable(imovelFilter.getNomeImovel()).orElse("%")));
		return mv;
	}
	
	@GetMapping("/{idImovel}")
	public ModelAndView editar(@PathVariable Long idImovel) {
		Imovel imovel = imoveis.findOne(idImovel);
		return novo(imovel);
	}
	
	@DeleteMapping("/{idImovel}")
	public String apagar(@PathVariable Long idImovel, RedirectAttributes attributes) {
		imoveis.delete(idImovel);
		attributes.addFlashAttribute("mensagem", "Imovél removido com sucesso");
		return "redirect:/imoveis";
	}
	
	/*@GetMapping("/relatorio")
	public ModelAndView getGerarRelatorioImoveis() throws JRException{
		
		JasperReportsPdfView view = new JasperReportsPdfView();
	    view.setUrl("/reports/imoveis_cadastrados.jasper");
	    view.setApplicationContext(appContext);
	    
	    System.out.println("passei aqui");
	       
	    Map<String, Object> params = new HashMap<>();
	    //params.put("datasource", imoveis.findAll());

	    return new ModelAndView(view, params);
	}*/
	
	/*public void imprimirRelatorio(){
		String jrxml = "imoveis_cadastrados.jrxml";
		Map<String, Object> parametros = new HashMap<>();
		Connection conexao = new ConnectionFactory().getConnection();
		OutputStream saida = new FileOutputStream("alunos.pdf");

		// compila jrxml em um arquivo .jasper
		String jasper = JasperCompileManager.compileReportToFile(jrxml);

		// preenche relatorio
		JasperPrint print = JasperFillManager.fillReport(jasper, parametros, conexao);

		// exporta para pdf
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);
		exporter.exportReport();
	}*/
}






