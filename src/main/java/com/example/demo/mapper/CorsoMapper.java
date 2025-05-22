package com.example.demo.mapper;

import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.CorsoWithoutAlunniDTO;
import com.example.demo.entity.Corso;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CorsoMapper {

    Corso toEntity(CorsoDTO corsoDTO);

    CorsoDTO toDTO(Corso corso);

    Set<Corso> toEntity(Set<CorsoDTO> corsoDTO);

    Set<CorsoDTO> toDTO(Set<Corso> corso);

    CorsoWithoutAlunniDTO toDTOWithoutAlunni(Corso corso);

    Set<CorsoWithoutAlunniDTO> toDTOWithoutAlunni(Set<Corso> corso);

    Corso toEntityWithoutAlunni (CorsoWithoutAlunniDTO corsoWithoutAlunniDTO);

    Set<Corso> toEntityWithoutAlunni (Set<CorsoWithoutAlunniDTO> corsoWithoutAlunniDTO);
}
