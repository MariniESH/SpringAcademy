package com.example.demo.mapper;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.entity.Alunno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AlunnoMapper {

    @Autowired
    CorsoMapper corsoMapper;

    public AlunnoDTO convertEntityToDTO(Alunno alunno) {
        AlunnoDTO alunnoDTO = new AlunnoDTO();
        alunnoDTO.setId(alunno.getId());
        alunnoDTO.setNome(alunno.getNome());
        alunnoDTO.setCognome(alunno.getCognome());
        alunnoDTO.setData(alunno.getData());
        alunnoDTO.setCitta(alunno.getCitta());
        alunnoDTO.setVoto(alunno.getVoto());
        if (alunno.getCorsi() != null) {
            alunnoDTO.setCorsi(corsoMapper.convertEntitiesNoAlunni(alunno.getCorsi()));
        } else {
            alunnoDTO.setCorsi(null);
        }
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
        if (alunnoDTO.getCorsi() != null) {
            alunno.setCorsi(corsoMapper.convertDTONoAlunni(alunnoDTO.getCorsi()));
        } else {
            alunno.setCorsi(null);
        }
        return alunno;
    }

    public Set<AlunnoDTO> convertAlunniEntityNoCorsi(Set<Alunno> alunni) {
        Set<AlunnoDTO> alunniDTO = new HashSet<>();
        AlunnoDTO alunnoDTO;
        for(Alunno alunno:alunni){
            alunnoDTO = new AlunnoDTO();
            alunnoDTO.setId(alunno.getId());
            alunnoDTO.setNome(alunno.getNome());
            alunnoDTO.setCognome(alunno.getCognome());
            alunnoDTO.setData(alunno.getData());
            alunnoDTO.setCitta(alunno.getCitta());
            alunnoDTO.setVoto(alunno.getVoto());
            alunniDTO.add(alunnoDTO);
        }
        return alunniDTO;
    }

    public Set<Alunno> convertAlunniDTONoCorsi(Set<AlunnoDTO> alunniDTO) {
        Set<Alunno> alunni = new HashSet<>();
        Alunno alunno;
        for(AlunnoDTO alunnoDTO:alunniDTO){
            alunno = new Alunno();
            alunno.setId(alunnoDTO.getId());
            alunno.setNome(alunnoDTO.getNome());
            alunno.setCognome(alunnoDTO.getCognome());
            alunno.setData(alunnoDTO.getData());
            alunno.setCitta(alunnoDTO.getCitta());
            alunno.setVoto(alunnoDTO.getVoto());
            alunni.add(alunno);
        }

        return alunni;
    }

}
