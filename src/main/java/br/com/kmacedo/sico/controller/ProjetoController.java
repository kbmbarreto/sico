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

import br.com.kmacedo.sico.service.ProjetoService;
import br.com.kmacedo.sico.model.Projeto;

@Controller
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;
	
	// display list of projetos
	@GetMapping("/projetos")
	public String viewProjetoPage(Model model) {
		return findPaginated(1, "descricao", "asc", model);		
	}
	
	@GetMapping("/showNewProjetoForm")
	public String showNewProjetoForm(Model model) {
		// create model attribute to bind form data
		Projeto projeto = new Projeto();
		model.addAttribute("projeto", projeto);
		return "new_projeto";
	}
	
	@PostMapping("/saveProjeto")
	public String saveProjeto(@ModelAttribute("projeto") Projeto projeto) {
		// save projeto to database
		projetoService.saveProjeto(projeto);
		return "redirect:/projetos";
	}
	
	@GetMapping("/showFormProjetoForUpdate/{id}")
	public String showFormProjetoForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get projeto from the service
		Projeto projeto = projetoService.getProjetoById(id);
		
		// set projeto as a model attribute to pre-populate the form
		model.addAttribute("projeto", projeto);
		return "update_projeto";
	}
	
	@GetMapping("/deleteProjeto/{id}")
	public String deleteProjeto(@PathVariable (value = "id") long id) {
		
		// call delete projeto method 
		this.projetoService.deleteProjetoById(id);
		return "redirect:/projetos";
	}
	
	@GetMapping("/pageProjeto/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Projeto> page = projetoService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Projeto> listProjetos = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listProjetos", listProjetos);
		return "projetos";
	}
}