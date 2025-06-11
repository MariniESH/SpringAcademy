package com.example.demo.controller;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.AlunnoWithoutCorsiDTO;
import com.example.demo.service.AlunnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/alunni")
public class AlunnoController {

    @Autowired
    AlunnoService alunnoService;

    // LISTA
    @GetMapping("/lista")
    public List<AlunnoDTO> list(@RequestParam(required = false, value = "citta")String citta) {
        List<AlunnoDTO> alunni;
        if (citta != null && !citta.isEmpty()) {
            alunni = alunnoService.getByCitta(citta);
        } else {
            alunni = alunnoService.findAll();
        }
        return alunni;
    }

    // READ
    @GetMapping("/{id}")
    public AlunnoDTO get(@PathVariable Long id) {
        return alunnoService.get(id);
    }

    @PostMapping("/by-ids")
    public Set<AlunnoWithoutCorsiDTO> getAlunnoWithoutCorsi(@RequestBody List<Long> ids) {
        return alunnoService.findAllByIds(ids);
    }

    // CREATE
    @PostMapping("/save")
    public AlunnoDTO save(@RequestBody AlunnoDTO alunno) {
        return alunnoService.save(alunno);
    }

    // UPDATE
    @PutMapping("{id}")
    public AlunnoDTO update(@PathVariable Long id, @RequestBody AlunnoDTO alunno) {
        alunno.setId(id);
        return alunnoService.save(alunno);
    }


    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        alunnoService.delete(id);
    }

    @GetMapping("/promossi")
    public List<AlunnoDTO> getPromossi() {
        return alunnoService.getPromossi();
    }

}
