package com.example.demo.controller;

import com.example.demo.entity.Alunno;
import com.example.demo.service.AlunnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alunni")
public class AlunnoController {

    @Autowired
    AlunnoService alunnoService;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {
        List<Alunno> alunni = alunnoService.findAll();
        model.addAttribute("alunni", alunni);
        return "list-alunni";
    }

    //FORM ALUNNO
    @GetMapping({"/nuovo", "/{id}/edit"})
    public String showForm(@PathVariable(required = false) Long id, Model model) {
        Alunno alunno = (id != null) ? alunnoService.get(id) : new Alunno();
        model.addAttribute("alunno", alunno);
        return "form-alunno";
    }

    // CREATE Or UPDATE
    @PostMapping("/save")
    public String save(@ModelAttribute("alunno") Alunno alunno, BindingResult br) {
        if (br.hasErrors()) {
            return "form-alunno";
        }
        alunnoService.save(alunno);
        return "redirect:/alunni/lista";
    }


    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        alunnoService.delete(id);
        return "redirect:/alunni/lista";
    }

}
