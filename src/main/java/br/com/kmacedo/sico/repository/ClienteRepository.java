package br.com.kmacedo.sico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kmacedo.sico.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}