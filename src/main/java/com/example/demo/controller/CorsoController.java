package com.example.demo.controller;

import com.example.demo.dto.AlunnoDTO;
import com.example.demo.dto.CorsoDTO;
import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import jakarta.servlet.RequestDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    CorsoService corsoService;

    @Autowired
    DocenteService docenteService;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {
        List<CorsoDTO> corsi = corsoService.findAll();
        model.addAttribute("corsi", corsi);
        return "list-corsi";
    }

    // FORM Corso
    @GetMapping({"/nuovo","{id}/edit"})
    public String form(@PathVariable(required = false) Long id, Model model) {
        CorsoDTO corso;
        if(id != null) {
            corso = corsoService.get(id);
            model.addAttribute("title", "Modifica Corso");
        } else {
            corso = new CorsoDTO();
            model.addAttribute("title", "Nuovo Corso");
        }
        List<DocenteDTO> docenti = docenteService.findAll();
        model.addAttribute("docenti", docenti);
        model.addAttribute("corso", corso);
        return "form-corso";
    }

    // CREATE or UPDATE
    @PostMapping("/save")
    public String save(@ModelAttribute("corso") CorsoDTO corso, BindingResult br) {
        if(br.hasErrors()) {
            return "form-corso";
        }

        corsoService.save(corso);
        return "redirect:/corsi/lista";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        corsoService.delete(id);
        return "redirect:/corsi/lista";
    }

    @GetMapping("{id}/iscritti")
    public String showAlunni(@PathVariable Long id, Model model) {
        List<AlunnoDTO> alunni = corsoService.getAlunniByCorsoId(id);
        model.addAttribute("alunni", alunni);
        model.addAttribute("corsoId", id);
        return "corso-alunni";
    }
}
