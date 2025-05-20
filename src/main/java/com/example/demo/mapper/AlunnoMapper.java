package com.example.demo.mapper;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.AlunnoWithoutCorsiDTO;
import com.example.demo.entity.Alunno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AlunnoMapper {

    Alunno toEntity(AlunnoDTO alunnoDTO);

    AlunnoWithoutCorsiDTO toDtoWithoutCorsi(Alunno alunno);

    AlunnoDTO toDto(Alunno alunno);

    List<Alunno> toEntity(List<AlunnoDTO> alunnoDTOs);

    List<AlunnoDTO> toDto(List<Alunno> alunni);

    Set<AlunnoWithoutCorsiDTO> toDtoWithoutCorsi(Set<Alunno> alunni);

    Alunno toEntityWithoutCorsi(AlunnoWithoutCorsiDTO alunnoWithoutCorsiDTO);

    Set<Alunno> toEntityWithoutCorsi(Set<AlunnoWithoutCorsiDTO> alunnoWithoutCorsiDTOs);
}
