package be.vdab.retroVideo.services;

import be.vdab.retroVideo.domain.Film;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmService {
    Optional<Film> findById(long id);
    List<Film>findByNaam(String genreNaam);
    List<Film> findByIds(List<Long> ids);
    void update(Film film);

}
