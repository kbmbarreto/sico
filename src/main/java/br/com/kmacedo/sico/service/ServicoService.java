package br.com.kmacedo.sico.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.kmacedo.sico.model.Servico;

public interface ServicoService {

	List<Servico> getAllServicos();
	void saveServico(Servico servico);
	Servico getServicoById(long id);
	void deleteServicoById(long id);
	Page<Servico> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}