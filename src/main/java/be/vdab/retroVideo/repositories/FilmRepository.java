package be.vdab.retroVideo.repositories;

import be.vdab.retroVideo.domain.Film;
import be.vdab.retroVideo.domain.Klant;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmRepository {
    Optional<Film> findById(long id);
    List<Film> findByNaam(String genreNaam);
    List<Film> findByIds(List<Long> ids);
    void update(Film film);

}
