package com.example.demo.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CorsoDTO {
    private Long id;
    private String nome;
    private Integer ore;
    private Integer anno;
    private DocenteDTO docente;
    private Set<AlunnoWithoutCorsiDTO> alunni;
}
