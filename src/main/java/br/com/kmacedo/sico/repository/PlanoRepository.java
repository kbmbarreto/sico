package br.com.kmacedo.sico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kmacedo.sico.model.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long>{

}