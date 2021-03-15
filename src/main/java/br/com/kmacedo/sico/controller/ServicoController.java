package br.com.kmacedo.sico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.kmacedo.sico.service.ServicoService;
import br.com.kmacedo.sico.model.Servico;

@Controller
public class ServicoController {

	@Autowired
	private ServicoService servicoService;
	
	@GetMapping("/servicos")
	public String viewServicoPage(Model model) {
		return findPaginated(1, "descricao", "asc", model);
	}
	
	@GetMapping("/showNewServicoForm")
	public String showNewServicoForm(Model model) {
		// create model attribute to bind form data
		Servico servico = new Servico();
		model.addAttribute("servico", servico);
		return "new_servico";
	}
	
	@PostMapping("/saveServico")
	public String saveServico(Servico servico) {
		System.out.println(servico);
		//save serviço to database
		servicoService.saveServico(servico);
		return "redirect:/servicos";
	}
	
	@GetMapping("/showFormServicoForUpdate/{id}")
	public String showFormServicoForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get servico from the service
		Servico servico = servicoService.getServicoById(id);
		
		// set servico as a model attribute to pre-populate the form
		model.addAttribute("servico", servico);
		return "update_servico";
	}
	
	@GetMapping("/deleteServico/{id}")
	public String deleteServico(@PathVariable (value = "id") long id) {
		
		// call delete serviços method 
		this.servicoService.deleteServicoById(id);
		return "redirect:/servicos";
	}


	@GetMapping("/pageServico/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Servico> page = servicoService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Servico> listServicos = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listServicos", listServicos);
		return "servicos";
	}
}