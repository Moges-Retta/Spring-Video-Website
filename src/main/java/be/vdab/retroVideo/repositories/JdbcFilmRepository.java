package be.vdab.retroVideo.repositories;

import be.vdab.retroVideo.domain.Film;
import be.vdab.retroVideo.domain.Genre;
import be.vdab.retroVideo.domain.Klant;
import be.vdab.retroVideo.exceptions.FilmNietGevondenException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcFilmRepository implements FilmRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final RowMapper<Film> filmMapper =
            (result, rowNum) ->
                    new Film(result.getLong("id"),
                            result.getLong("genreid"),
                            result.getString("titel"),
                            result.getLong("voorraad"),
                            result.getLong("gereserveerd"),
                            result.getBigDecimal("prijs"));
    private final RowMapper<Genre> genreMapper =
            (result, rowNum) ->
                    new Genre(result.getLong("id"),
                            result.getString("naam"));

    JdbcFilmRepository(JdbcTemplate template) {
        this.template = template;
        this.insert  = new SimpleJdbcInsert(template)
                .withTableName("reservaties")
                .usingGeneratedKeyColumns("klantid");
    }

    @Override
    public Optional<Film> findById(long id) {
        try {
            var sql = "select * from films where id = ?";
            return Optional.of(template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Film> findByNaam(String genreNaam) {
            var sql = "select * from genres where naam = ?";
            var sqlFilm = "select * from films where genreid = ?";
            var genre = template.queryForObject(sql, genreMapper, genreNaam);
            return template.query(sqlFilm, filmMapper, genre.getId());
    }
    @Override
    public List<Film> findByIds(List<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        var sql = "select * from films where id in (" + "?,".repeat(ids.size() - 1) +
                "?) order by id";
        return template.query(sql, ids.toArray(), filmMapper);
    }

    @Override
    public void update(Film film) {
        var sql = "update films set gereserveerd=? where id = ?";
        if (template.update(sql, film.getGereserveerd()+1,film.getId()) == 0) {
            throw new FilmNietGevondenException();
        }
    }
}
