package be.vdab.retroVideo.controllers;

import be.vdab.retroVideo.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/genre/{id}")
public class FilmController {
    private final FilmService filmService;
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    @GetMapping
    public ModelAndView film(@PathVariable long id) {
        var modelAndView = new ModelAndView("filmDetail");
        filmService.findById(id).ifPresent(film->modelAndView.addObject("film",film));
        return modelAndView;
    }
}
