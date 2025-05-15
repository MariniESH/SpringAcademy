package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class AlunnoDTO {
    private Long id;
    private String nome;
    private String cognome;
    private Date data;
    private String citta;
    private Double voto;
}
