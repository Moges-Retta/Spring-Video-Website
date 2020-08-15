package be.vdab.retroVideo.controllers;

import be.vdab.retroVideo.forms.KlantNaamBeginMet;
import be.vdab.retroVideo.services.KlantService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("Klant")
public class KlantController {
    private final KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping
    public ModelAndView toonForm() {
        var modelAndView = new ModelAndView("klanten");
        modelAndView.addObject(new KlantNaamBeginMet(""));
        return modelAndView;
    }

    @GetMapping("klantenForm")
    public ModelAndView klanten(String klantenNaam) {
        var modelAndView = new ModelAndView("klanten");
        modelAndView.addObject("klantenList", klantService.findByBeginNaam(klantenNaam));
        return modelAndView;
    }

    @GetMapping("beginMetLetter")
    public ModelAndView klantNaamBeginMet(@Valid KlantNaamBeginMet klantNaamBeginMet, Errors errors) {
        var modelAndView = new ModelAndView("klanten");
        if (errors.hasErrors()) {
            return modelAndView;
        }
        return modelAndView.addObject("klantenLijst",
                klantService.findByBeginNaam(klantNaamBeginMet.getBeginLetterMet()));
    }

}
