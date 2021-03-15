package br.com.kmacedo.sico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.kmacedo.sico.model.Plano;
import br.com.kmacedo.sico.repository.PlanoRepository;

@Service
public class PlanoServiceImpl implements PlanoService {

	@Autowired
	private PlanoRepository planoRepository;

	@Override
	public List<Plano> getAllPlanos() {
		return planoRepository.findAll();
	}

	@Override
	public void savePlano(Plano plano) {
		this.planoRepository.save(plano);
	}

	@Override
	public Plano getPlanoById(long id) {
		Optional<Plano> optional = planoRepository.findById(id);
		Plano plano = null;
		if (optional.isPresent()) {
			plano = optional.get();
		} else {
			throw new RuntimeException(" Plano not found for id :: " + id);
		}
		return plano;
	}

	@Override
	public void deletePlanoById(long id) {
		this.planoRepository.deleteById(id);
	}

	@Override
	public Page<Plano> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending(): 
			Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.planoRepository.findAll(pageable);
	}
}