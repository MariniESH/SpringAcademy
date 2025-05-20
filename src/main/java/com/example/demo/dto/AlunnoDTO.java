package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
public class AlunnoDTO {
    private Long id;
    private String nome;
    private String cognome;
    private Date data;
    private String citta;
    private Double voto;
    private Set<CorsoDTO> corsi;
}
