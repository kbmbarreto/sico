package br.com.kmacedo.sico.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.kmacedo.sico.model.Cliente;

public interface ClienteService {

	List<Cliente> getAllClientes();
	void saveCliente(Cliente cliente);
	Cliente getClienteById(long id);
	void deleteClienteById(long id);
	Page<Cliente> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
