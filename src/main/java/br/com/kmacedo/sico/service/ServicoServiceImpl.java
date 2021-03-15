package br.com.kmacedo.sico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.kmacedo.sico.model.Servico;
import br.com.kmacedo.sico.repository.ServicoRepository;

@Service
public class ServicoServiceImpl implements ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;

	@Override
	public List<Servico> getAllServicos() {
		return servicoRepository.findAll();
	}

	@Override
	public void saveServico(Servico servico) {
		this.servicoRepository.save(servico);
	}

	@Override
	public Servico getServicoById(long id) {
		Optional<Servico> optional = servicoRepository.findById(id);
		Servico servico = null;
		if (optional.isPresent()) {
			servico = optional.get();
		} else {
			throw new RuntimeException(" Servico not found for id :: " + id);
		}
		return servico;
	}

	@Override
	public void deleteServicoById(long id) {
		this.servicoRepository.deleteById(id);
	}

	@Override
	public Page<Servico> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending(): 
			Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.servicoRepository.findAll(pageable);
	}
}