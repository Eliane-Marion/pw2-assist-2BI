package br.com.etechoracio.pw2assist.controller;

import br.com.etechoracio.pw2assist.entity.Equipamento;
import br.com.etechoracio.pw2assist.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
    @Autowired
    private EquipamentoRepository repository;

    @GetMapping
    public List<Equipamento> listarTodosEquipamentos(){
        return repository.findAll();
    }
    @PostMapping
    public Equipamento inserirEquipamento(@RequestBody Equipamento body){
        return repository.save(body);
    }

    @GetMapping("/{id}")
    public Equipamento buscarPorId(@PathVariable Long id){
        var existe = repository.findById(id);

        if(existe.isPresent())
            return existe.get();
        return null;
    }

    //Fazer o método alterar



}
