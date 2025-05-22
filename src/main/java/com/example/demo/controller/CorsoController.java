package com.example.demo.controller;


import com.example.demo.dto.CorsoDTO;

import com.example.demo.service.AlunnoService;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    CorsoService corsoService;

    @Autowired
    DocenteService docenteService;

    @Autowired
    private AlunnoService alunnoService;

    // LISTA
    @GetMapping("/lista")
    public List<CorsoDTO> list() {
        return corsoService.findAll();
    }

    @GetMapping("/{id}")
    public CorsoDTO findById(@PathVariable Long id) {
        return corsoService.get(id);
    }

//    // FORM Corso
//    @GetMapping({"/nuovo","{id}/edit"})
//    public String form(@PathVariable(required = false) Long id, Model model) {
//        CorsoDTO corso;
//        if(id != null) {
//            corso = corsoService.get(id);
//            model.addAttribute("title", "Modifica Corso");
//        } else {
//            corso = new CorsoDTO();
//            model.addAttribute("title", "Nuovo Corso");
//        }
//        List<DocenteDTO> docenti = docenteService.findAll();
//        model.addAttribute("docenti", docenti);
//        model.addAttribute("corso", corso);
//        return "form-corso";
//    }

    // CREATE
    @PostMapping("/save")
    public CorsoDTO save(@RequestBody CorsoDTO corso) {
        return corsoService.save(corso);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CorsoDTO update(@PathVariable Long id, @RequestBody CorsoDTO corso) {
        corso.setId(id);
        return corsoService.save(corso);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        corsoService.delete(id);
    }


//    @GetMapping("alunni/{id}")
//    public String showAlunni(@PathVariable Long id, Model model) {
//        model.addAttribute("corso", corsoService.get(id));
//        model.addAttribute("alunni", alunnoService.findAll());
//        List<Long> alunniId = new ArrayList<>();
//        for (AlunnoWithoutCorsiDTO alunno : corsoService.get(id).getAlunni()) {
//            alunniId.add(alunno.getId());
//        }
//        model.addAttribute("iscritti", alunniId);
//        return "alunni-iscritti";
//    }



//    @PostMapping("/alunni/{idCorso}")
//    public String addAlunni(
//            @PathVariable Long idCorso,
//            @RequestParam(required = false, name = "alunniIds") List<Long> idAlunni) {
//
//        corsoService.updateAlunni(idCorso, idAlunni != null ? idAlunni : new ArrayList<>());
//        return "redirect:/corsi/lista";
//    }
}
