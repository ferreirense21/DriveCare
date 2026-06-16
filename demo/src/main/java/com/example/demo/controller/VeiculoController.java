package com.example.demo.controller;

import com.example.demo.model.Veiculo;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository repository;

    // Rota de Cadastro
    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        try {
            Veiculo salvo = repository.save(veiculo);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Rota de Listagem (Filtra apenas os carros do usuário logado)
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Veiculo>> buscarPorUsuario(@PathVariable Long idUsuario) {
        // Puxa todos e filtra no Java (método à prova de falhas para evitar erro no Repository)
        List<Veiculo> todos = repository.findAll();
        List<Veiculo> veiculosDoUsuario = todos.stream()
                .filter(v -> v.getUsuario() != null && v.getUsuario().getId_usuario().equals(idUsuario))
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(veiculosDoUsuario);
    }

    // --- NOVA ROTA: EXCLUIR VEÍCULO ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return ResponseEntity.noContent().build(); // 204 - Sucesso ao deletar
            } else {
                return ResponseEntity.notFound().build(); // 404 - Não encontrou
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500 - Erro no banco
        }
    }
}