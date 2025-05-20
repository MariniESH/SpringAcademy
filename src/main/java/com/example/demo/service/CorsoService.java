package com.example.demo.service;

import com.example.demo.dto.AlunnoWithoutCorsiDTO;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CorsoService {

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    DocenteService docenteService;

    @Autowired
    CorsoMapper corsoMapper;

    @Autowired
    AlunnoMapper alunnoMapper;

    @Autowired
    private AlunnoRepository alunnoRepository;


    public List<CorsoDTO> findAll() {
        List<CorsoDTO> corsi = new ArrayList<>();
        List<Corso> corsiToConvert = corsoRepository.findAll(Sort.by("id"));
        for(Corso corso : corsiToConvert) {
            CorsoDTO corsoDTO = corsoMapper.toDTO(corso);

            if (corso.getAlunni() != null) {
                Set<AlunnoWithoutCorsiDTO> alunniDTO = alunnoMapper.toDtoWithoutCorsi(corso.getAlunni());
                corsoDTO.setAlunni(alunniDTO);
            } else {
                corsoDTO.setAlunni(null);
            }
            corsi.add(corsoDTO);
        }
        return corsi;
    }

    public CorsoDTO get(Long id) {
        Corso corso = corsoRepository.findById(id).orElseThrow();
        CorsoDTO corsoDTO = corsoMapper.toDTO(corso);
        if (corso.getAlunni() != null) {
            corsoDTO.setAlunni(alunnoMapper.toDtoWithoutCorsi(corso.getAlunni()));
        } else {
            corsoDTO.setAlunni(null);
        }
        return corsoDTO;
    }

    public Corso save(CorsoDTO c) {

        if (c.getDocente().getId() != null) {
            c.setDocente(docenteService.get(c.getDocente().getId()));
        } else {
            c.setDocente(null);
        }
        Corso corso = corsoMapper.toEntity(c);

        if (c.getAlunni() == null && c.getId() != null) {
            Corso corsoPresente = corsoRepository.findById(c.getId()).orElseThrow();
            c.setAlunni(alunnoMapper.toDtoWithoutCorsi(corsoPresente.getAlunni()));
            corso.setAlunni(alunnoMapper.toEntityWithoutCorsi(c.getAlunni()));
        }

        return corsoRepository.save(corso);
    }

    public void delete(Long id) {
        Corso corso = corsoRepository.findById(id).orElseThrow();

        Set<Alunno> alunni = new HashSet<>(corso.getAlunni());
        for (Alunno alunno : alunni) {
            alunno.getCorsi().removeIf(c -> c.getId().equals(corso.getId()));
        }
        corso.getAlunni().clear();

        corsoRepository.deleteById(corso.getId());
    }


    public void updateAlunni(Long idCorso, List<Long> idAlunni) {

        Corso corso = corsoRepository.findById(idCorso).orElseThrow();
        Set<Alunno> nuoviAlunni = new HashSet<>(alunnoRepository.findAllById(idAlunni));
        corso.setAlunni(nuoviAlunni);

        corsoRepository.save(corso);
    }

}
