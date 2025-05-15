package com.example.demo.service;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.mapper.AlunnoMapper;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.repository.AlunnoRepository;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CorsoService {

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    AlunnoRepository alunnoRepository;

    @Autowired
    DocenteService docenteService;

    @Autowired
    CorsoMapper corsoMapper;

    @Autowired
    AlunnoMapper alunnoMapper;

    public List<CorsoDTO> findAll() {
        List<CorsoDTO> corsi = new ArrayList<>();
        for(Corso corso : corsoRepository.findAll(Sort.by("id"))) {
            corsi.add(corsoMapper.converEntityToDTO(corso));
        }
        return corsi;
    }

    public CorsoDTO get(Long id) {
        return corsoMapper.converEntityToDTO(corsoRepository.findById(id).orElseThrow());
    }

    public Corso save(CorsoDTO c) {
        if (c.getDocente().getId() != null) {
             c.setDocente(docenteService.get(c.getDocente().getId()));
        } else {
            c.setDocente(null);
        }

        return corsoRepository.save(corsoMapper.convertDTOToEntity(c));
    }

    public void delete(Long id) {
        Corso corso = corsoMapper.convertDTOToEntity(get(id));
        corsoRepository.deleteById(corso.getId());
    }

    public List<AlunnoDTO> getAlunniByCorsoId(Long id) {
        List<AlunnoDTO> alunni = new ArrayList<>();
        for (Alunno alunno : alunnoRepository.findByCorsiId(id)) {
            alunni.add(alunnoMapper.convertEntityToDTO(alunno));
        }
        return alunni;
    }

}
