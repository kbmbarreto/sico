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

import br.com.kmacedo.sico.service.ClienteService;
import br.com.kmacedo.sico.model.Cliente;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	// display list of clientes
	@GetMapping("/clientes")
	public String viewClientePage(Model model) {
		return findPaginated(1, "cnpj", "asc", model);		
	}
	
	@GetMapping("/showNewClienteForm")
	public String showNewClienteForm(Model model) {
		// create model attribute to bind form data
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "new_cliente";
	}
	
	@PostMapping("/saveCliente")
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {
		// save cliente to database
		clienteService.saveCliente(cliente);
		return "redirect:/clientes";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get cliente from the service
		Cliente cliente = clienteService.getClienteById(id);
		
		// set cliente as a model attribute to pre-populate the form
		model.addAttribute("cliente", cliente);
		return "update_cliente";
	}
	
	@GetMapping("/deleteCliente/{id}")
	public String deleteCliente(@PathVariable (value = "id") long id) {
		
		// call delete cliente method 
		this.clienteService.deleteClienteById(id);
		return "redirect:/clientes";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Cliente> page = clienteService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Cliente> listClientes = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listClientes", listClientes);
		return "clientes";
	}
}
