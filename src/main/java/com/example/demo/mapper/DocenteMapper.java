package com.example.demo.mapper;

import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Docente;
import org.springframework.stereotype.Component;

@Component
public class DocenteMapper {

    public DocenteDTO convertEntityToDTO(Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setId(docente.getId());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        docenteDTO.setData(docente.getData());
        return docenteDTO;
    }

    public Docente convertDTOToEntity(DocenteDTO docenteDTO) {
        Docente docente = new Docente();
        docente.setId(docenteDTO.getId());
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        docente.setData(docenteDTO.getData());
        return docente;
    }
}
