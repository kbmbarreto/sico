package br.com.kmacedo.sico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.kmacedo.sico.model.Apontamento;
import br.com.kmacedo.sico.repository.ApontamentoRepository;

@Service
public class ApontamentoServiceImpl implements ApontamentoService {

	@Autowired
	private ApontamentoRepository apontamentoRepository;

	@Override
	public List<Apontamento> getAllApontamentos() {
		return apontamentoRepository.findAll();
	}

	@Override
	public void saveApontamento(Apontamento apontamento) {
		this.apontamentoRepository.save(apontamento);
	}

	@Override
	public Apontamento getApontamentoById(long id) {
		Optional<Apontamento> optional = apontamentoRepository.findById(id);
		Apontamento apontamento = null;
		if (optional.isPresent()) {
			apontamento = optional.get();
		} else {
			throw new RuntimeException(" Apontamento not found for id :: " + id);
		}
		return apontamento;
	}

	@Override
	public void deleteApontamentoById(long id) {
		this.apontamentoRepository.deleteById(id);
	}

	@Override
	public Page<Apontamento> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending(): 
			Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.apontamentoRepository.findAll(pageable);
	}
}