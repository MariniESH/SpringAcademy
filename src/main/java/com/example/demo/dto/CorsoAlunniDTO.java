package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorsoAlunniDTO {
    private Long id;
    private Long alunnoId;
    private Long corsoId;
}
