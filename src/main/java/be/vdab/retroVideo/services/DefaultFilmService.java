package be.vdab.retroVideo.services;

import be.vdab.retroVideo.domain.Film;
import be.vdab.retroVideo.repositories.JdbcFilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultFilmService implements FilmService {
    private final JdbcFilmRepository repository;

    public DefaultFilmService(JdbcFilmRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Film> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Film> findByNaam(String genreNaam) {
        return repository.findByNaam(genreNaam);
    }

    @Override
    public List<Film> findByIds(List<Long> ids) {
        return repository.findByIds(ids);
    }

    @Override
    public void update(Film film) {
        repository.update(film);
    }
}
