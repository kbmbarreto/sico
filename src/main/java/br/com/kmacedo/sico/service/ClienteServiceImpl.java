package br.com.kmacedo.sico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.kmacedo.sico.model.Cliente;
import br.com.kmacedo.sico.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public void saveCliente(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}

	@Override
	public Cliente getClienteById(long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		Cliente cliente = null;
		if (optional.isPresent()) {
			cliente = optional.get();
		} else {
			throw new RuntimeException(" Cliente not found for id :: " + id);
		}
		return cliente;
	}

	@Override
	public void deleteClienteById(long id) {
		this.clienteRepository.deleteById(id);
	}

	@Override
	public Page<Cliente> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending(): 
			Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.clienteRepository.findAll(pageable);
	}
}