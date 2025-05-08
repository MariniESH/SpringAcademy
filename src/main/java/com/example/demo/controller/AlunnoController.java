package com.example.demo.controller;

import com.example.demo.entity.Alunno;
import com.example.demo.service.AlunnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/alunni")
public class AlunnoController {

    @Autowired
    AlunnoService alunnoService;

    // LISTA
    @GetMapping("/lista")
    public ModelAndView list(@RequestParam(required = false, value = "citta")String citta) {
        ModelAndView model = new ModelAndView("list-alunni");
        List<Alunno> alunni;
        if (citta != null && !citta.isEmpty()) {
            alunni = alunnoService.getByCitta(citta);
        } else {
            alunni = alunnoService.findAll();
        }
        model.addObject("alunni", alunni);
        model.addObject("viewType", "lista");
        return model;
    }

    //FORM ALUNNO
    @GetMapping({"/nuovo", "/{id}/edit"})
    public ModelAndView showForm(@PathVariable(required = false) Long id) {
        ModelAndView model = new ModelAndView("form-alunno");
        Alunno alunno = (id != null) ? alunnoService.get(id) : new Alunno();
        model.addObject("alunno", alunno);
        return model;
    }

    // CREATE Or UPDATE
    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("alunno") Alunno alunno, BindingResult br) {
        ModelAndView model = new ModelAndView();
        if (br.hasErrors()) {
            model.setViewName("form-alunno");
            return model;
        }
        alunnoService.save(alunno);
        model.setViewName("redirect:/alunni/lista");
        return model;
    }


    // DELETE
    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView model = new ModelAndView("redirect:/alunni/lista");
        alunnoService.delete(id);
        return model;
    }

    @GetMapping("/promossi")
    public ModelAndView getPromossi() {
        ModelAndView model = new ModelAndView("list-alunni");
        model.addObject("alunni", alunnoService.getPromossi());
        model.addObject("viewType", "promossi");
        return model;
    }



}
