package br.com.kmacedo.sico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.kmacedo.sico.service.ApontamentoService;
import br.com.kmacedo.sico.model.Apontamento;

@Controller
public class ApontamentoController {

	@Autowired
	private ApontamentoService apontamentoService;
	
	// display list of apontamentos
	@GetMapping("/apontamentos")
	public String viewApontamentoPage(Model model) {
		return findPaginated(1, "data", "desc", model);		
	}
	
	@GetMapping("/showNewApontamentoForm")
	public String showNewApontamentoForm(Model model) {
		// create model attribute to bind form data
		Apontamento apontamento = new Apontamento();
		model.addAttribute("apontamento", apontamento);
		return "new_apontamento";
	}
	
	@PostMapping("/saveApontamento")
	public String saveApontamento(@ModelAttribute("apontamento") Apontamento apontamento) {
		// save apontamento to database
		apontamentoService.saveApontamento(apontamento);
		return "redirect:/apontamentos";
	}
	
	@GetMapping("/showFormApontamentoForUpdate/{id}")
	public String showFormApontamentoForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get apontamento from the service
		Apontamento apontamento = apontamentoService.getApontamentoById(id);
		
		// set apontamento as a model attribute to pre-populate the form
		model.addAttribute("apontamento", apontamento);
		return "update_apontamento";
	}
	
	@GetMapping("/deleteApontamento/{id}")
	public String deleteApontamento(@PathVariable (value = "id") long id) {
		
		// call delete apontamento method 
		this.apontamentoService.deleteApontamentoById(id);
		return "redirect:/apontamentos";
	}
	
	@GetMapping("/pageApontamento/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Apontamento> page = apontamentoService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Apontamento> listApontamentos = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listApontamentos", listApontamentos);
		return "apontamentos";
	}
}