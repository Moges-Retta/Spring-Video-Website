package be.vdab.retroVideo.controllers;

import be.vdab.retroVideo.domain.Film;
import be.vdab.retroVideo.domain.Klant;
import be.vdab.retroVideo.domain.Reservatie;
import be.vdab.retroVideo.services.FilmService;
import be.vdab.retroVideo.services.KlantService;
import be.vdab.retroVideo.services.ReservatieService;
import be.vdab.retroVideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Controller
@RequestMapping("bevestigen")
public class ReservatieController {
    private final Mandje mandje;
    private final KlantService klantService;
    private final FilmService filmService;
    private final ReservatieService reservatieService;

    public ReservatieController(Mandje mandje, KlantService klantService, FilmService filmService, ReservatieService reservatieService) {
        this.mandje = mandje;
        this.klantService = klantService;
        this.filmService = filmService;
        this.reservatieService = reservatieService;
    }

    @GetMapping("{id}")
    public ModelAndView bevestigen(@PathVariable long id){
        var modelAndView = new ModelAndView("bevestigen");
        modelAndView.addObject("aantalfilms",filmService.findByIds(mandje.getIds()).size());
        modelAndView.addObject("klant",klantService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("raport/{id}")
    public ModelAndView raport(@PathVariable long id){
        var films = filmService.findByIds(mandje.getIds());
        var klant = klantService.findById(id).get();
        var reservaties = new LinkedList<Reservatie>();
        var nietGereserveered = new LinkedList<Film>();

        films.forEach(film -> {
            if(film.getVoorraad()>film.getGereserveerd()){
                filmService.update(film);
                reservaties.add(new Reservatie(klant.getId(),film.getId(), LocalDateTime.now()));
            }else{
                nietGereserveered.add(film);
            }
        });
        reservaties.forEach(reservatieService::create);

        var modelAndView = new ModelAndView("raport");
        modelAndView.addObject("nietGereserveerd",nietGereserveered);
        return modelAndView;
    }
}
