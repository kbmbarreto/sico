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

import br.com.kmacedo.sico.service.PlanoService;
import br.com.kmacedo.sico.model.Plano;

@Controller
public class PlanoController {

	@Autowired
	private PlanoService planoService;
	
	// display list of planos
	@GetMapping("/planos")
	public String viewPlanoPage(Model model) {
		return findPaginated(1, "descricao", "asc", model);		
	}
	
	@GetMapping("/showNewPlanoForm")
	public String showNewPlanoForm(Model model) {
		// create model attribute to bind form data
		Plano plano = new Plano();
		model.addAttribute("plano", plano);
		return "new_plano";
	}
	
	@PostMapping("/savePlano")
	public String savePlano(@ModelAttribute("plano") Plano plano) {
		// save plano to database
		planoService.savePlano(plano);
		return "redirect:/planos";
	}
	
	@GetMapping("/showFormPlanoForUpdate/{id}")
	public String showFormPlanoForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get plano from the service
		Plano plano = planoService.getPlanoById(id);
		
		// set plano as a model attribute to pre-populate the form
		model.addAttribute("plano", plano);
		return "update_plano";
	}
	
	@GetMapping("/deletePlano/{id}")
	public String deletePlano(@PathVariable (value = "id") long id) {
		
		// call delete plano method 
		this.planoService.deletePlanoById(id);
		return "redirect:/planos";
	}
	
	@GetMapping("/pagePlano/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Plano> page = planoService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Plano> listPlanos = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPlanos", listPlanos);
		return "planos";
	}
}