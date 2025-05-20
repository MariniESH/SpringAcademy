package com.example.demo.service;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.entity.Alunno;
import com.example.demo.entity.Corso;
import com.example.demo.mapper.AlunnoMapper;
import com.example.demo.mapper.CorsoMapper;
import com.example.demo.repository.AlunnoRepository;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AlunnoService {

    @Autowired
    AlunnoRepository alunnoRepository;

    @Autowired
    AlunnoMapper alunnoMapper;
    @Autowired
    private CorsoRepository corsoRepository;


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
        Alunno alunno = alunnoRepository.findById(id).orElseThrow();

        Set<Corso> oldCorsi = new HashSet<>(alunno.getCorsi());

        for (Corso corso : oldCorsi) {
            corso.getAlunni().removeIf(a -> a.getId().equals(alunno.getId()));
        }
        alunno.getCorsi().clear();

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


    public void updateCorsi(Long idAlunno, List<Long> idCorsi) {

        Alunno alunno = alunnoRepository.findById(idAlunno).orElseThrow();

        Set<Corso> oldCorsi = new HashSet<>(alunno.getCorsi());

        for (Corso corso : oldCorsi) {
            corso.getAlunni().removeIf(a -> a.getId().equals(alunno.getId()));
        }
        alunno.getCorsi().clear();

        Set<Corso> nuoviCorsi = new HashSet<>(corsoRepository.findAllById(idCorsi));
        for (Corso corso: nuoviCorsi) {
            corso.getAlunni().add(alunno);
        }
        alunno.setCorsi(nuoviCorsi);
        alunnoRepository.save(alunno);

    }

}
