package br.com.kmacedo.sico.service;

import java.util.List;
import org.springframework.data.domain.Page;
import br.com.kmacedo.sico.model.Plano;

public interface PlanoService {

	List<Plano> getAllPlanos();
	void savePlano(Plano plano);
	Plano getPlanoById(long id);
	void deletePlanoById(long id);
	Page<Plano> findPaginated(int pageNo, int PageSize, String sortField, String sortDirection);
}