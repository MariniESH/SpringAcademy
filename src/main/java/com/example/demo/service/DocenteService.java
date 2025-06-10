package com.example.demo.service;

import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.DocenteMapper;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.service.connector.CorsoConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocenteService {


    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    DocenteMapper docenteMapper;

    @Autowired
    private CorsoConnector corsoConnector;

    public List<DocenteDTO> findAll() {
        List<DocenteDTO> docenti = new ArrayList<>();
        for(Docente docente : docenteRepository.findAll(Sort.by("id"))) {
            docenti.add(docenteMapper.toDto(docente));
        }
        return docenti;
    }

    public DocenteDTO get(Long id) {
        return docenteMapper.toDto(docenteRepository.findById(id).orElseThrow());
    }

    public DocenteDTO save(DocenteDTO d) {
        Docente docente = docenteRepository.save(docenteMapper.toEntity(d));
        return docenteMapper.toDto(docente);
    }

    // Non mi setta a null l'id docente a Corso nell'altro backend
    public void delete(Long id) {
        Docente docente = docenteMapper.toEntity(get(id));

        // Chiamare il CorsoService per trovare i corsi insegnati dal docente
        List<CorsoDTO> corsi = corsoConnector.getCorsiByDocenteId(id);
        corsi.forEach(corsoDTO -> {
            corsoDTO.setDocente(null);
            corsoConnector.removeDocente(corsoDTO);
        });

        docenteRepository.deleteById(docente.getId());
    }

    public List<DocenteDTO> searchByNomeOrCognome(String search) {
        List<DocenteDTO> docenti = new ArrayList<>();
        for(Docente docente : docenteRepository.findByNomeContainingOrCognomeContaining(search, search)) {
            docenti.add(docenteMapper.toDto(docente));
        }
        return docenti;
    }
}
