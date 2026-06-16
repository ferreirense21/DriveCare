package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/planos")
public class PlanoController {

    @GetMapping
    public String exibirPlanos() {
        return "planos"; 
    }

    @PostMapping("/criar-pagamento-real")
    @ResponseBody
    public ResponseEntity<Map<String, String>> criarPagamentoReal(@RequestBody Map<String, String> dados) {
        Map<String, String> resposta = new HashMap<>();

        try {
            
            String linkOficialMercadoPago = "https://mpago.la/1VPAtDM";

            
            resposta.put("checkoutUrl", linkOficialMercadoPago);
            return ResponseEntity.ok(resposta);

        } catch (Exception e) {
            resposta.put("erro", "Erro interno do servidor: " + e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
}
