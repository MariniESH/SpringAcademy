package com.example.demo.controller;

import com.example.demo.dto.DocenteDTO;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    // LISTA
    @GetMapping("/lista")
    public List<DocenteDTO> list(@RequestParam(required = false, value = "search") String search) {
        List<DocenteDTO> docenti;
        if(search != null && !search.isEmpty()) {
            docenti = docenteService.searchByNomeOrCognome(search);
        } else {
            docenti = docenteService.findAll();
        }
        return docenti;
    }

    @GetMapping("/{id}")
    public DocenteDTO get(@PathVariable Long id) {
        return docenteService.get(id);
    }

    // SALVA NUOVO
    @PostMapping("/new")
    public DocenteDTO create(@RequestBody DocenteDTO docente) {
        return docenteService.save(docente);

    }

    // AGGIORNA
    @PutMapping("/{id}")
    public DocenteDTO update(@PathVariable Long id, @RequestBody DocenteDTO docente) {
        docente.setId(id);
        return docenteService.save(docente);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        docenteService.delete(id);

    }


}

