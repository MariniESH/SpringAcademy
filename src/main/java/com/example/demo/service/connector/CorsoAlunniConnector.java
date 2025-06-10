package com.example.demo.service.connector;

import com.example.demo.dto.CorsoAlunniDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CorsoAlunniConnector {

    @Autowired
    private WebClient webClient;

    public List<CorsoAlunniDTO> getIscritti(Long id) {
        return webClient.get()
                .uri("/iscrizioni/{id}/corsi", id)
                .retrieve()
                .bodyToFlux(CorsoAlunniDTO.class)
                .collectList()
                .block();
    }

    public void postIscritti(List<CorsoAlunniDTO> iscritti) {
        webClient.post()
                .uri("/iscrizioni/iscrivi")
                .bodyValue(iscritti)
                .retrieve()
                .bodyToFlux(CorsoAlunniDTO.class)
                .collectList()
                .block();
    }

    public void deleteIscritti(Long id) {
        webClient.delete()
                .uri("/iscrizioni/{id}/corsi", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
