package br.com.etechoracio.pw2assist.controller;

import br.com.etechoracio.pw2assist.entity.Servico;
import br.com.etechoracio.pw2assist.entity.Tecnico;
import br.com.etechoracio.pw2assist.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoRepository servicoRepository;


    @GetMapping
    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        var resultado = servicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado.get());
    }

    @PostMapping
    public ResponseEntity<Servico> inserir(@RequestBody Servico servico) {
        var existe = servicoRepository.findById(servico.getId());
        if (existe.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        var salvo = servicoRepository.save(servico);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id,
                                             @RequestBody  Servico body) {
        var resultado = servicoRepository.findById(id);
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var salvo = servicoRepository.save(body);
        return ResponseEntity.ok(salvo);
    }

}
