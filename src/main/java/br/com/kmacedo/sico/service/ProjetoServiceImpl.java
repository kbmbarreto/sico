package br.com.kmacedo.sico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.kmacedo.sico.model.Projeto;
import br.com.kmacedo.sico.repository.ProjetoRepository;

@Service
public class ProjetoServiceImpl implements ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;

	@Override
	public List<Projeto> getAllProjetos() {
		return projetoRepository.findAll();
	}

	@Override
	public void saveProjeto(Projeto projeto) {
		this.projetoRepository.save(projeto);
	}

	@Override
	public Projeto getProjetoById(long id) {
		Optional<Projeto> optional = projetoRepository.findById(id);
		Projeto projeto = null;
		if (optional.isPresent()) {
			projeto = optional.get();
		} else {
			throw new RuntimeException(" Projeto not found for id :: " + id);
		}
		return projeto;
	}

	@Override
	public void deleteProjetoById(long id) {
		this.projetoRepository.deleteById(id);
	}

	@Override
	public Page<Projeto> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending(): 
			Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.projetoRepository.findAll(pageable);
	}
}