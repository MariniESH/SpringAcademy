package com.example.demo.controller;

import com.example.demo.entity.Corso;
import com.example.demo.service.CorsoService;
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

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {
        List<Corso> corsi = corsoService.findAll();
        model.addAttribute("corsi", corsi);
        return "list-corsi";
    }

    // FORM Corso
    @GetMapping({"/nuovo","{id}/edit"})
    public String form(@PathVariable(required = false) Long id, Model model) {

        Corso corso;
        if(id != null) {
            corso = corsoService.get(id);
        } else {
            corso = new Corso();
        }
        model.addAttribute("corso", corso);
        return "form-corso";
    }

    // CREATE or UPDATE
    @PostMapping("/save")
    public String save(@ModelAttribute("corso")Corso corso, BindingResult br) {
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

}
