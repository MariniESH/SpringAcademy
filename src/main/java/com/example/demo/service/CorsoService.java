package com.example.demo.service;

import com.example.demo.dto.AlunnoWithoutCorsiDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.CorsoWithoutAlunniDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.mapper.AlunnoMapper;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.mapper.DocenteMapper;
import com.example.demo.repository.AlunnoRepository;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
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
    CorsoMapper corsoMapper;

    @Autowired
    AlunnoMapper alunnoMapper;

    @Autowired
    private AlunnoRepository alunnoRepository;

    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private DocenteMapper docenteMapper;


    public List<CorsoDTO> findAll() {
        return corsoMapper.toDTO(corsoRepository.findAll());
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

    public CorsoDTO save(CorsoDTO c) {
        Corso corso;
        if (c.getId() != null) {
            corso = corsoRepository.findById(c.getId()).orElseThrow();
            if (corso.getAlunni() != null) {
                removeAlunni(corso);
            }
            if (c.getDocente() != null && c.getDocente().getId() != null) {
                c.setDocente(docenteMapper.toDto(docenteRepository.findById(c.getDocente().getId()).orElseThrow()));
            } else {
                c.setDocente(null);
            }
            if (c.getAlunni() != null) {
                List<Long> alunniIds = new ArrayList<>();
                for (AlunnoWithoutCorsiDTO alunno : c.getAlunni()) {
                    alunniIds.add(alunno.getId());
                }
                c.setAlunni(addAlunni(corso, alunniIds));
            }
        } else {

            if (c.getAlunni() != null) {
                Set<AlunnoWithoutCorsiDTO> alunni = new HashSet<>();
                for (AlunnoWithoutCorsiDTO alunno : c.getAlunni()) {
                    alunni.add(alunnoMapper.toDtoWithoutCorsi(alunnoRepository.findById(alunno.getId()).orElseThrow()));
                }
                c.setAlunni(alunni);
            }
        }
        corso = corsoRepository.save(corsoMapper.toEntity(c));
        return corsoMapper.toDTO(corsoRepository.save(corso));
    }

    public void delete(Long id) {
        Corso corso = corsoRepository.findById(id).orElseThrow();
        removeAlunni(corso);
        corsoRepository.deleteById(corso.getId());
    }


    public Set<AlunnoWithoutCorsiDTO> addAlunni(Corso corso, List<Long> idAlunni) {
        Set<Alunno> nuoviAlunni = new HashSet<>(alunnoRepository.findAllById(idAlunni));
        corso.setAlunni(nuoviAlunni);
        return alunnoMapper.toDtoWithoutCorsi(corso.getAlunni());
    }

    public void removeAlunni(Corso corso) {
        Set<Alunno> alunni = new HashSet<>(corso.getAlunni());
        for (Alunno alunno : alunni) {
            alunno.getCorsi().removeIf(c -> c.getId().equals(corso.getId()));
        }
        corso.getAlunni().clear();
    }

}
