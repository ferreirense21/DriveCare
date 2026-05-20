package com.example.demo.controller;

import com.example.demo.model.Veiculo;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos") // É ESSA LINHA AQUI QUE O SEU SITE ESTÁ PROCURANDO!
public class VeiculoController {

    @Autowired
    private VeiculoRepository repository;

    @PostMapping
    public ResponseEntity<Veiculo> salvarVeiculo(@RequestBody Veiculo veiculo) {
        try {
            Veiculo veiculoSalvo = repository.save(veiculo);
            return ResponseEntity.ok(veiculoSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Veiculo>> listarCarrosDaGaragem(@PathVariable Long id) {
        List<Veiculo> veiculos = repository.buscarPorIdDono(id);
        return ResponseEntity.ok(veiculos);
    }
}
