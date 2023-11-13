package br.com.etechoracio.pw2assist.repository;

import br.com.etechoracio.pw2assist.entity.OrdemServico;
import br.com.etechoracio.pw2assist.entity.Tecnico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    Page<Tecnico> findByEmail(String email, Pageable pageable);

    Optional<Tecnico> findByEmail(String email);

    List<Tecnico> findByEmailIgnoreCaseAndIdNot(String email, Long id);



}
