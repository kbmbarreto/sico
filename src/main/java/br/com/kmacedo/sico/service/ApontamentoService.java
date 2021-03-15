package br.com.kmacedo.sico.service;

import java.util.List;
import org.springframework.data.domain.Page;
import br.com.kmacedo.sico.model.Apontamento;

public interface ApontamentoService {

	List<Apontamento> getAllApontamentos();
	void saveApontamento(Apontamento apontamento);
	Apontamento getApontamentoById(long id);
	void deleteApontamentoById(long Id);
	Page<Apontamento> findPaginated(int pageNo, int PageSize, String sortField, String sortDirection);
}