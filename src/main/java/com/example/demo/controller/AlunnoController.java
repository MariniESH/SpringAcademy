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

@Controller
@RequestMapping("/alunni")
public class AlunnoController {

    @Autowired
    AlunnoService alunnoService;
    @Autowired
    private CorsoService corsoService;

    // LISTA
    @GetMapping("/lista")
    public ModelAndView list(@RequestParam(required = false, value = "citta")String citta) {
        ModelAndView model = new ModelAndView("list-alunni");
        List<AlunnoDTO> alunni;
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
        AlunnoDTO alunno;

        if (id != null) {
            alunno = alunnoService.get(id);
            model.addObject("title", "Modifica Alunno");
        } else {
            alunno = new AlunnoDTO();
            model.addObject("title", "Nuovo Alunno");
        };
        model.addObject("alunno", alunno);

        return model;
    }

    // CREATE Or UPDATE
    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("alunno") AlunnoDTO alunno, BindingResult br) {
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
