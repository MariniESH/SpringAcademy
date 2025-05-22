package com.example.demo.service;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.AlunnoWithoutCorsiDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.CorsoWithoutAlunniDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.mapper.AlunnoMapper;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.repository.AlunnoRepository;
import com.example.demo.repository.CorsoRepository;
import org.apache.taglibs.standard.tag.common.fmt.SetTimeZoneSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AlunnoService {

    @Autowired
    private AlunnoRepository alunnoRepository;

    @Autowired
    private AlunnoMapper alunnoMapper;

    @Autowired
    private CorsoRepository corsoRepository;
    @Autowired
    private CorsoMapper corsoMapper;


    public List<AlunnoDTO> findAll() {
        return alunnoMapper.toDto(alunnoRepository.findAll(Sort.by("id").ascending()));
    }

    public AlunnoDTO get(Long id) {
        return alunnoMapper.toDto(alunnoRepository.findById(id).orElseThrow());
    }

    public AlunnoWithoutCorsiDTO getWithoutCorsi(Long id) {
        return alunnoMapper.toDtoWithoutCorsi(alunnoRepository.findById(id).orElseThrow());
    }

    public AlunnoDTO save(AlunnoDTO a) {
        Alunno alunno;
        if (a.getId() != null) {
            alunno = alunnoRepository.findById(a.getId()).orElseThrow();
            if(alunno.getCorsi() != null) {
                removeCorsi(alunno);
            }
            if (a.getCorsi() != null) {
                List<Long> nuoviCorsi = new ArrayList<>();
                for (CorsoDTO corsoDTO : a.getCorsi()) {
                    nuoviCorsi.add(corsoDTO.getId());
                }
                a.setCorsi(addCorsi(alunno, nuoviCorsi));
            }
        } else {
            if (a.getCorsi() != null) {
                Set<CorsoDTO> corsi = new HashSet<>();
                for (CorsoDTO corsoDTO : a.getCorsi()) {
                    corsi.add(corsoMapper.toDTO(corsoRepository.findById(corsoDTO.getId()).orElseThrow()));
                }
                a.setCorsi(corsi);
            }
        }

        alunno = alunnoRepository.save(alunnoMapper.toEntity(a));
        return alunnoMapper.toDto(alunno);
    }

    public void delete(Long id) {
        Alunno alunno = alunnoRepository.findById(id).orElseThrow();
        removeCorsi(alunno);
        alunnoRepository.deleteById(alunno.getId());
    }

    public List<AlunnoDTO> getPromossi() {
        List<AlunnoDTO> alunni = new ArrayList<>();
        for(Alunno alunno : alunnoRepository.findAllPromossi()) {
            alunni.add(alunnoMapper.toDto(alunno));
        }
        return alunni;
    }

    public List<AlunnoDTO> getByCitta(String citta) {
        List<AlunnoDTO> alunni = new ArrayList<>();
        for(Alunno alunno : alunnoRepository.findByCitta(citta)) {
            alunni.add(alunnoMapper.toDto(alunno));
        }
        return alunni;
    }

    public Set<CorsoDTO> addCorsi(Alunno alunno, List<Long> idCorsi) {

        Set<Corso> nuoviCorsi = new HashSet<>(corsoRepository.findAllById(idCorsi));
        for (Corso corso: nuoviCorsi) {
            corso.getAlunni().add(alunno);
        }
        alunno.setCorsi(nuoviCorsi);
        return corsoMapper.toDTO(alunno.getCorsi());
    }

    public void removeCorsi(Alunno alunno) {
        Set<Corso> oldCorsi = new HashSet<>(alunno.getCorsi());

        for (Corso corso : oldCorsi) {
            corso.getAlunni().removeIf(a -> a.getId().equals(alunno.getId()));
        }
        alunno.getCorsi().clear();
    }
}
