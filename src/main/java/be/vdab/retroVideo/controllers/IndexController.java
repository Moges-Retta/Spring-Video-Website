package be.vdab.retroVideo.controllers;

import be.vdab.retroVideo.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    private final FilmService filmService;

    public IndexController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("index");
    }
    @GetMapping("Aktiefilm")
    public ModelAndView Aktiefilm() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Aktiefilm"));
        modelAndView.addObject("Genre","Aktiefilm");
        return modelAndView;
    }
    @GetMapping("Avontuur")
    public ModelAndView Avontuur() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Avontuur"));
        modelAndView.addObject("Genre","Avontuur");
        return modelAndView;
    }
    @GetMapping("Cowboyfilm")
    public ModelAndView Cowboyfilm() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Cowboyfilm"));
        return modelAndView;
    }
    @GetMapping("Erotiek")
    public ModelAndView Erotiek() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Erotiek"));
        modelAndView.addObject("Genre","Erotiek");
        return modelAndView;
    }
    @GetMapping("Griezel")
    public ModelAndView Griezel() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Griezel"));
        modelAndView.addObject("Genre","Griezel");
        return modelAndView;
    }
    @GetMapping("Humor")
    public ModelAndView Humor() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Humor"));
        modelAndView.addObject("Genre","Humor");
        return modelAndView;
    }
    @GetMapping("Kinderfilm")
    public ModelAndView Kinderfilm() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Kinderfilm"));
        modelAndView.addObject("Genre","Kinderfilm");
        return modelAndView;
    }
    @GetMapping("Oorlog")
    public ModelAndView Oorlog() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Oorlog"));
        modelAndView.addObject("Genre","Oorlog");

        return modelAndView;
    }
    @GetMapping("Piratenfilm")
    public ModelAndView Piratenfilm() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Piratenfilm"));
        modelAndView.addObject("Genre","Piratenfilm");

        return modelAndView;
    }
    @GetMapping("Sciencefiction")
    public ModelAndView Sciencefiction() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Science fiction"));       modelAndView.addObject("Genre","Aktiefilm");
        modelAndView.addObject("Genre","Science fiction");

        return modelAndView;
    }
    @GetMapping("Sentimenteel")
    public ModelAndView Sentimenteel() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Sentimenteel"));
        modelAndView.addObject("Genre","Sentimenteel");

        return modelAndView;
    }
    @GetMapping("Speelfilm")
    public ModelAndView Speelfilm() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Speelfilm"));
        modelAndView.addObject("Genre","Speelfilm");

        return modelAndView;
    }
    @GetMapping("Thriller")
    public ModelAndView Thriller() {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam("Thriller"));
        modelAndView.addObject("Genre","Thriller");

        return modelAndView;
    }
    @GetMapping("{id}")
    public ModelAndView film(@PathVariable long id) {
        var modelAndView = new ModelAndView("filmDetail");
        filmService.findById(id).ifPresent(film->modelAndView.addObject("film",film));
        return modelAndView;
    }
}
