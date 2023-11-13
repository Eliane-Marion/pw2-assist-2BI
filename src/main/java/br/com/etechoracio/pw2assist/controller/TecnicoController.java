package br.com.etechoracio.pw2assist.controller;


import br.com.etechoracio.pw2assist.entity.Tecnico;
import br.com.etechoracio.pw2assist.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @GetMapping
    public List<Tecnico> listarTodos() {
                return tecnicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> buscarPorId(@PathVariable Long id) {
        var resultado = tecnicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }

    @PostMapping
    public ResponseEntity<Tecnico> inserir(@RequestBody Tecnico tecnico) {
        var existe = tecnicoRepository.findByEmail(tecnico.getEmail());
        if (existe.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        var salvo = tecnicoRepository.save(tecnico);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecnico> atualizar(@PathVariable Long id,
                                             @RequestBody  Tecnico body) {
        var resultado = tecnicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var existente = tecnicoRepository.findByEmailIgnoreCaseAndIdNot(body.getEmail(), id);
        if (!existente.isEmpty()) {
            throw new RuntimeException("Já existe um e-mail cadastrado");
        }

        var salvo = tecnicoRepository.save(body);
        return ResponseEntity.ok(salvo);
    }






}
