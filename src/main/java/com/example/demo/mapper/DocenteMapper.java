package com.example.demo.mapper;

import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Docente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    Docente toEntity(DocenteDTO dto);

    DocenteDTO toDto(Docente entity);


}
