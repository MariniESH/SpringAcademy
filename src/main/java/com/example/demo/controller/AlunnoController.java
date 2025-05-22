package com.example.demo.controller;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.service.AlunnoService;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunni")
public class AlunnoController {

    @Autowired
    AlunnoService alunnoService;
    @Autowired
    private CorsoService corsoService;

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
    @DeleteMapping("/{id}/")
    public void delete(@PathVariable Long id) {
        alunnoService.delete(id);
    }

    @GetMapping("/promossi")
    public List<AlunnoDTO> getPromossi() {
        return alunnoService.getPromossi();
    }

    // Implementare metodo per ricevere tutti i corsi frequentati di un determinato alunno
    @GetMapping("/corsi/{id}")
    public String showCorsi(@PathVariable Long id, Model model) {
        model.addAttribute("alunno", alunnoService.get(id));
        model.addAttribute("corsi", corsoService.findAll());
        List<Long> corsiId = new ArrayList<>();
        for (CorsoDTO corso : alunnoService.get(id).getCorsi()) {
            corsiId.add(corso.getId());
        }
        model.addAttribute("iscritti", corsiId);
        return "corsi-frequentati";
    }

    @PostMapping("/corsi/{id}")
    public String addCorsi(@PathVariable Long id,
                           @RequestParam(required = false, name="corsiIds")List<Long> corsiId) {

        alunnoService.updateCorsi(id, corsiId != null ? corsiId : new ArrayList<>());
        return "redirect:/alunni/lista";
    }

}
