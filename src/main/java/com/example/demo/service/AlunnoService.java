package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Alunno;
import com.example.demo.mapper.AlunnoMapper;
import com.example.demo.repository.AlunnoRepository;
import com.example.demo.service.connector.CorsoAlunniConnector;
import com.example.demo.service.connector.CorsoConnector;
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
    private CorsoConnector corsoConnector;

    @Autowired
    private CorsoAlunniConnector corsoAlunniConnector;


    public List<AlunnoDTO> findAll() {
        List<AlunnoDTO> alunni = alunnoMapper.toDto(alunnoRepository.findAll(Sort.by("id").ascending()));
        alunni.forEach(this::addCorsi);
        return alunni;
    }

    // Implementare metodo diverso per non creare un loop infinito
    public AlunnoDTO get(Long id) {
        AlunnoDTO alunno = alunnoMapper.toDto(alunnoRepository.findById(id).orElseThrow());
        addCorsi(alunno);
        return alunno;
    }
//     Possibile implementazione per evitare loop infinito
//    public AlunnoWithoutCorsiDTO getWithoutCorsi(Long id) {
//        return alunnoMapper.toDtoWithoutCorsi(alunnoRepository.findById(id).orElseThrow());
//    }

    public AlunnoDTO save(AlunnoDTO a) {
        Alunno alunno = alunnoRepository.save(alunnoMapper.toEntity(a));
        AlunnoDTO alunnoDTO = alunnoMapper.toDto(alunno);

        try {
           corsoAlunniConnector.deleteIscritti(alunno.getId());
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (a.getCorsi() != null && !a.getCorsi().isEmpty()) {
            List<CorsoAlunniDTO> iscrizioni = new ArrayList<>();
            a.getCorsi().forEach(c -> {
                CorsoAlunniDTO iscrizione = new CorsoAlunniDTO();
                iscrizione.setCorsoId(c.getId());
                iscrizione.setAlunnoId(alunnoDTO.getId());
                iscrizioni.add(iscrizione);
            });
            corsoAlunniConnector.postIscritti(iscrizioni);
            addCorsi(alunnoDTO);
        }
        return alunnoDTO;
    }

    public void delete(Long id) {
        Alunno alunno = alunnoRepository.findById(id).orElseThrow();
        corsoAlunniConnector.deleteIscritti(alunno.getId());
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

    private void addCorsi(AlunnoDTO alunnoDTO) {
        Set<CorsoWithoutAlunniDTO> corsi;
        try {
            List<CorsoAlunniDTO> iscrizioni = corsoAlunniConnector.getIscritti(alunnoDTO.getId());
            List<Long> corsiIds = iscrizioni.stream().map(CorsoAlunniDTO::getCorsoId).toList();
            corsi = new HashSet<>(corsoConnector.getCorsiByAlunnoId(corsiIds));
            alunnoDTO.setCorsi(corsi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
