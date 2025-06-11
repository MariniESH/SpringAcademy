package com.example.demo.service.connector;

import com.example.demo.dto.CorsoAlunniDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.CorsoWithoutAlunniDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CorsoConnector {


//    private String credentials = "user:pass1234";
//    private String encodedAuth = Base64.getEncoder().encodeToString(credentials.getBytes());
//

    @Autowired
    WebClient webClient;

    public List<CorsoDTO> getCorsiByDocenteId(Long docenteId) {
        return webClient.get()
                .uri("/corsi/docente/{docenteId}", docenteId)
//                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .retrieve()
                .bodyToFlux(CorsoDTO.class)
                .collectList()
                .block();
    }

    public void removeDocente(CorsoDTO corsoDTO) {
        webClient.put()
                .uri("/corsi/{id}", corsoDTO.getId())
                .bodyValue(corsoDTO)
//                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
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

        return webClient.post()
                .uri("/corsi/by-ids")
                .bodyValue(corsoIds)
//                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .retrieve()
                .bodyToFlux(CorsoWithoutAlunniDTO.class)
                .collectList()
                .block();
    }
}
