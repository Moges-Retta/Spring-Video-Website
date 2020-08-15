package be.vdab.retroVideo.controllers;

import be.vdab.retroVideo.domain.Film;
import be.vdab.retroVideo.services.FilmService;
import be.vdab.retroVideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("Mandje")
public class MandjeController {
    private final Mandje mandje;
    private final FilmService filmService;

    public MandjeController(Mandje mandje, FilmService filmService) {
        this.mandje = mandje;
        this.filmService = filmService;
    }

    @PostMapping("{id}")
    public String voegToe(@PathVariable long id) {
    mandje.voegToe(id);
    return "redirect:/Mandje";
    }
    public BigDecimal totaalPrijs(List<Film> films){
        var totalPrijs = BigDecimal.ZERO;
        if(!films.isEmpty())
        {
            var prijsList = new LinkedList<BigDecimal>();
            films.forEach(film -> prijsList.add(film.getPrijs()));
            for(var i=0;i!=prijsList.size();i++) {
                totalPrijs=totalPrijs.add(prijsList.get(i));
            }
        }
        return totalPrijs;
    }
    @GetMapping
    public ModelAndView toonMandje() {
        var films = filmService.findByIds(mandje.getIds());
        var modelAndView = new ModelAndView("mandje","films", films);
        modelAndView.addObject("totaalPrijs",totaalPrijs(films));
        return modelAndView;
    }
    @PostMapping(value="verwijderen")
    public String updateMandje(long[] id) {
        if (id != null) {
        Arrays.stream(id).forEach(mandje::verwijder);
        }
        return "redirect:/Mandje";
    }
}
