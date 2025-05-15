package com.example.demo.mapper;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.entity.Alunno;
import org.springframework.stereotype.Component;

@Component
public class AlunnoMapper {

    public AlunnoDTO convertEntityToDTO(Alunno alunno) {
        AlunnoDTO alunnoDTO = new AlunnoDTO();
        alunnoDTO.setId(alunno.getId());
        alunnoDTO.setNome(alunno.getNome());
        alunnoDTO.setCognome(alunno.getCognome());
        alunnoDTO.setData(alunno.getData());
        alunnoDTO.setCitta(alunno.getCitta());
        alunnoDTO.setVoto(alunno.getVoto());
        return alunnoDTO;
    }

    public Alunno convertDTOToEntity(AlunnoDTO alunnoDTO) {
        Alunno alunno = new Alunno();
        alunno.setId(alunnoDTO.getId());
        alunno.setNome(alunnoDTO.getNome());
        alunno.setCognome(alunnoDTO.getCognome());
        alunno.setData(alunnoDTO.getData());
        alunno.setCitta(alunnoDTO.getCitta());
        alunno.setVoto(alunnoDTO.getVoto());
        return alunno;
    }
}
