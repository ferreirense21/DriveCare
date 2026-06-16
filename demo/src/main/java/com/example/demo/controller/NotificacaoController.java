package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    // Uma lista segura que guarda os "túneis" de todos os usuários logados no site
    private final CopyOnWriteArrayList<SseEmitter> emissores = new CopyOnWriteArrayList<>();

    // 1. O Front-end se conecta aqui silenciosamente para começar a ouvir
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter conectar() {
        SseEmitter emissor = new SseEmitter(Long.MAX_VALUE); // Conexão infinita
        emissores.add(emissor);

        // Se o usuário fechar a aba, removemos ele da lista
        emissor.onCompletion(() -> emissores.remove(emissor));
        emissor.onTimeout(() -> emissores.remove(emissor));
        emissor.onError((e) -> emissores.remove(emissor));

        return emissor;
    }

    // 2. Rota para disparar o alerta. Pode ser acionada por um sensor do carro ou pelo painel admin
    @GetMapping("/enviar")
    public String enviarNotificacao(@RequestParam String titulo, @RequestParam String mensagem) {
        // Monta o alerta em formato JSON simples
        String payload = String.format("{\"titulo\": \"%s\", \"mensagem\": \"%s\"}", titulo, mensagem);

        // Dispara para todo mundo que estiver com a página aberta
        for (SseEmitter emissor : emissores) {
            try {
                emissor.send(SseEmitter.event().name("alerta-drivecare").data(payload));
            } catch (IOException e) {
                emissores.remove(emissor); // Remove se houver erro de conexão
            }
        }
        return "Notificação enviada com sucesso em tempo real!";
    }
}