package com.example.demo.service;

import com.example.demo.entity.Alunno;
import com.example.demo.repository.AlunnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunnoService {

    @Autowired
    AlunnoRepository alunnoRepository;

    public List<Alunno> findAll() {
        return alunnoRepository.findAll(Sort.by("id"));
    }

    public Alunno get(Long id) {
        return alunnoRepository.findById(id).orElseThrow();
    }

    public Alunno save(Alunno a) {
        return alunnoRepository.save(a);
    }

    public void delete(Long id) {
        alunnoRepository.deleteById(id);
    }

    public List<Alunno> getPromossi() {
        return alunnoRepository.findAllPromossi();
    }

    public List<Alunno> getByCitta(String citta) {
        return alunnoRepository.findByCitta(citta);
    }

}
