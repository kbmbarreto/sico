package br.com.kmacedo.sico.service;

import java.util.List;
import org.springframework.data.domain.Page;
import br.com.kmacedo.sico.model.Projeto;

public interface ProjetoService {

	List<Projeto> getAllProjetos();
	void saveProjeto(Projeto projeto);
	Projeto getProjetoById(long id);
	void deleteProjetoById(long id);
	Page<Projeto> findPaginated(int pageNo, int PageSize, String sortField, String sortDirection);
}