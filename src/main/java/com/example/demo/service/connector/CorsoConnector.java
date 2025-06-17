package com.example.demo.service.connector;

import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.CorsoWithoutAlunniDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CorsoConnector {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public CorsoConnector(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    private WebClient webClient() {
        // every time you build, the filter will inject the header
        return webClientBuilder.build();
    }

    public List<CorsoDTO> getCorsiByDocenteId(Long docenteId) {
        return webClient().get()
                .uri("/corsi/docente/{docenteId}", docenteId)
                .retrieve()
                .bodyToFlux(CorsoDTO.class)
                .collectList()
                .block();
    }

    public void removeDocente(CorsoDTO corsoDTO) {
        webClient().put()
                .uri("/corsi/{id}", corsoDTO.getId())
                .bodyValue(corsoDTO)
                .retrieve()
                .bodyToMono(CorsoDTO.class)
                .block();
    }

//    public CorsoWithoutAlunniDTO getCorso(Long corsoId) {
//        return webClient.get()
//                .uri("/corsi/{id}", corsoId)
//                .retrieve()
//                .bodyToMono(CorsoWithoutAlunniDTO.class)
//                .block();
//    }

    public List<CorsoWithoutAlunniDTO> getCorsiByAlunnoId(List<Long> corsoIds) {
        if (corsoIds.isEmpty()) { return List.of(); }

        return webClient().post()
                .uri("/corsi/by-ids")
                .bodyValue(corsoIds)
                .retrieve()
                .bodyToFlux(CorsoWithoutAlunniDTO.class)
                .collectList()
                .block();
    }
}
