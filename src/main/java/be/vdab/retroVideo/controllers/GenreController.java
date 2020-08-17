package be.vdab.retroVideo.controllers;

import be.vdab.retroVideo.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/{genre}", method = RequestMethod.GET)
public class GenreController {
    private final FilmService filmService;
    public GenreController(FilmService filmService) {
        this.filmService = filmService;
    }
    @GetMapping
    public ModelAndView Aktiefilm(@PathVariable("genre") String genre) {
        var modelAndView = new ModelAndView("filmList");
        modelAndView.addObject("films",filmService.findByNaam(genre));
        modelAndView.addObject("Genre",genre);
        return modelAndView;
    }
}
