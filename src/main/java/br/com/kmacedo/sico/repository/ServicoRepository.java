package br.com.kmacedo.sico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kmacedo.sico.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}