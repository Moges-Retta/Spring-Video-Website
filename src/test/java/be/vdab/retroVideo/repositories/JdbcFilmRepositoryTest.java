package be.vdab.retroVideo.repositories;

import be.vdab.retroVideo.domain.Film;
import be.vdab.retroVideo.exceptions.FilmNietGevondenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(JdbcFilmRepository.class)
@ExtendWith(MockitoExtension.class)
public class JdbcFilmRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JdbcFilmRepository repository;

    public JdbcFilmRepositoryTest(JdbcFilmRepository repository) {
        this.repository = repository;
    }

    private long idVanTestFilm() {
        var sql = "insert into films(genreid,titel,voorraad,gereserveerd,prijs) " +
                "values (" + genreidVanTestFilm() + ",'test',0,0,0)";
        super.jdbcTemplate.execute(sql);
        return super.jdbcTemplate.queryForObject(
                "select id from films where titel ='test'", Long.class);
    }

    private long idVanTest2Film() {
        var sql = "insert into films(genreid,titel,voorraad,gereserveerd,prijs) " +
                "values (" + genreidVanTest2Film() + ",'test2',0,0,0)";
        super.jdbcTemplate.execute(sql);
        return super.jdbcTemplate.queryForObject(
                "select id from films where titel ='test2'", Long.class);
    }

    private long genreidVanTestFilm() {
        var sql = "insert into genres (naam) values ('test')";
        super.jdbcTemplate.execute(sql);
        return super.jdbcTemplate.queryForObject(
                "select id from genres where naam ='test'", Long.class);
    }

    private long genreidVanTest2Film() {
        var sql = "insert into genres (naam) values ('test2')";
        super.jdbcTemplate.execute(sql);
        return super.jdbcTemplate.queryForObject(
                "select id from genres where naam ='test2'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestFilm()).get().getTitle()).isEqualTo("test");
    }

    @Test
    void findByOnbestaandeIdVindtGeenFilm() {
        assertThat(repository.findById(-1)).isEmpty();
    }

    @Test
    void findByByNaam() {
        var id = idVanTestFilm();
        assertThat(super.countRowsInTableWhere("films", "id=" + id)).isOne();
    }

    @Test
    void findByIds() {
        long id1 = idVanTestFilm();
        long id2 = idVanTest2Film();
        assertThat(repository
                .findByIds(List.of(id1, id2)))
                .extracting(Film::getId)
                .containsOnly(id1, id2)
                .isSorted();
    }

    @Test
    @DisplayName("findbyids voor een lege verzamling is leeg")
    void findByids2() {
        assertThat(repository.findByIds(List.of())).isEmpty();
    }

    @Test
    @DisplayName("findbyids voor een onbestaande ids geeft leeg")
    void findByIds3() {
        assertThat(repository.findByIds(List.of(-1L))).isEmpty();
    }

    @Test
    void update() {
        var id = idVanTest2Film();
        var genreid = super.jdbcTemplate.queryForObject(
                "select id from genres where naam ='test2'", Long.class);
        var film = new Film(id, genreid, "test2", 0, 1, BigDecimal.ZERO);
        repository.update(film);
        assertThat(super.jdbcTemplate
                .queryForObject("select gereserveerd from films where id=?", BigDecimal.class, id))
                .isLessThanOrEqualTo(BigDecimal.valueOf(2));
    }

    @Test
    @DisplayName("update m.b.v onbestaande film geeft een fout")
    void update2() {
        assertThatExceptionOfType(FilmNietGevondenException.class).isThrownBy(
                () -> repository.update(
                        new Film(-1, genreidVanTestFilm(), "test", 0, 0, BigDecimal.ZERO)));
    }
}
