package com.example.demo.service;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.mapper.AlunnoMapper;
import com.example.demo.repository.AlunnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunnoService {

    @Autowired
    AlunnoRepository alunnoRepository;

    @Autowired
    AlunnoMapper alunnoMapper;

    public List<AlunnoDTO> findAll() {
        List<AlunnoDTO> alunni = new ArrayList<>();
        for(Alunno alunno : alunnoRepository.findAll(Sort.by("id"))) {
            alunni.add(alunnoMapper.convertEntityToDTO(alunno));
        }
        return alunni;
    }

    public AlunnoDTO get(Long id) {
        return alunnoMapper.convertEntityToDTO(alunnoRepository.findById(id).orElseThrow());
    }

    public Alunno save(AlunnoDTO a) {
        return alunnoRepository.save(alunnoMapper.convertDTOToEntity(a));
    }

    public void delete(Long id) {
        Alunno alunno = alunnoMapper.convertDTOToEntity(get(id));
        alunnoRepository.deleteById(alunno.getId());
    }

    public List<AlunnoDTO> getPromossi() {
        List<AlunnoDTO> alunni = new ArrayList<>();
        for(Alunno alunno : alunnoRepository.findAllPromossi()) {
            alunni.add(alunnoMapper.convertEntityToDTO(alunno));
        }
        return alunni;
    }

    public List<AlunnoDTO> getByCitta(String citta) {
        List<AlunnoDTO> alunni = new ArrayList<>();
        for(Alunno alunno : alunnoRepository.findByCitta(citta)) {
            alunni.add(alunnoMapper.convertEntityToDTO(alunno));
        }
        return alunni;
    }

}
