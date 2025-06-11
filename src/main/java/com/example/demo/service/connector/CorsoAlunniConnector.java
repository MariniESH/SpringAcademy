package com.example.demo.service.connector;

import com.example.demo.dto.CorsoAlunniDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.List;

@Component
public class CorsoAlunniConnector {

//    private String credentials = "user:pass1234";
//    private String encodedAuth = Base64.getEncoder().encodeToString(credentials.getBytes());
//

    @Autowired
    private WebClient webClient;

    public List<CorsoAlunniDTO> getIscritti(Long id) {
        return webClient.get()
                .uri("/iscrizioni/{id}/corsi", id)
//                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .retrieve()
                .bodyToFlux(CorsoAlunniDTO.class)
                .collectList()
                .block();
    }

    public void postIscritti(List<CorsoAlunniDTO> iscritti) {
        webClient.post()
                .uri("/iscrizioni/iscrivi")
                .bodyValue(iscritti)
//                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .retrieve()
                .bodyToFlux(CorsoAlunniDTO.class)
                .collectList()
                .block();
    }

    public void deleteIscritti(Long id) {
        webClient.delete()
                .uri("/iscrizioni/{id}/corsi", id)
//                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
