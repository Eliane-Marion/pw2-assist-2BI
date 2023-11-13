package br.com.etechoracio.pw2assist.repository;

import br.com.etechoracio.pw2assist.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
