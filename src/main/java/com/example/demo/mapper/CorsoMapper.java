package com.example.demo.mapper;

import com.example.demo.dto.CorsoDTO;
import com.example.demo.entity.Corso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorsoMapper {

    @Autowired
    DocenteMapper docenteMapper;

    public CorsoDTO converEntityToDTO(Corso corso) {
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setId(corso.getId());
        corsoDTO.setNome(corso.getNome());
        corsoDTO.setAnno(corso.getAnno());
        corsoDTO.setOre(corso.getOre());
        if(corso.getDocente() != null) {
            corsoDTO.setDocente(docenteMapper.convertEntityToDTO(corso.getDocente()));
        } else {
            corsoDTO.setDocente(null);
        }
        return corsoDTO;
    }

    public Corso convertDTOToEntity(CorsoDTO corsoDTO) {
        Corso corso = new Corso();
        corso.setId(corsoDTO.getId());
        corso.setNome(corsoDTO.getNome());
        corso.setAnno(corsoDTO.getAnno());
        corso.setOre(corsoDTO.getOre());
        if(corsoDTO.getDocente() != null) {
            corso.setDocente(docenteMapper.convertDTOToEntity(corsoDTO.getDocente()));
        } else {
            corso.setDocente(null);
        }
        return corso;
    }
}
