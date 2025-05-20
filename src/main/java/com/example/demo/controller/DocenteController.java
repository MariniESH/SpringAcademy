package com.example.demo.controller;

import com.example.demo.dto.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;
    @Autowired
    CorsoRepository corsoRepository;

    // LISTA
    @GetMapping("/lista")
    public String list(@RequestParam(required = false, value = "search") String search, Model model) {
        List<DocenteDTO> docenti;
        if(search != null && !search.isEmpty()) {
            docenti = docenteService.searchByNomeOrCognome(search);
        } else {
            docenti = docenteService.findAll();
        }
        model.addAttribute("docenti", docenti);
        return "list-docenti";
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new Docente());
        model.addAttribute("title", "Nuovo Docente");
        return "form-docente";
    }

    // SALVA NUOVO
    @PostMapping("/new")
    public String create(@ModelAttribute("docente") DocenteDTO docente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("docente", docenteService.get(id));
        model.addAttribute("title", "Modifica Docente");
        return "form-docente";
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("docente") DocenteDTO docente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docente.setId(id);
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {

        for (Corso corso : corsoRepository.findByDocenteId(id)) {
            corso.setDocente(null);
            corsoRepository.save(corso);
        }
        docenteService.delete(id);
        return "redirect:/docenti/lista";
    }


}

