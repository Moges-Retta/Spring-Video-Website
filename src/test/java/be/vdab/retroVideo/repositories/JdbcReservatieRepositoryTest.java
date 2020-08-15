package be.vdab.retroVideo.repositories;

import be.vdab.retroVideo.domain.Reservatie;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcReservatieRepository.class)
@Sql("/insertKlant.sql")
public class JdbcReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JdbcReservatieRepository repository;

    public JdbcReservatieRepositoryTest(JdbcReservatieRepository repository) {
        this.repository = repository;
    }
        private long idVanTestFilm() {
        var sql ="insert into films(genreid,titel,voorraad,gereserveerd,prijs) " +
                "values ("+ genreidVanTestFilm() +",'test',0,0,0)";
        super.jdbcTemplate.execute(sql);
        return super.jdbcTemplate.queryForObject(
                "select id from films where titel ='test'", Long.class);
    }
    private long genreidVanTestFilm() {
        var sql ="insert into genres (naam) values ('test')";
        super.jdbcTemplate.execute(sql);
        return super.jdbcTemplate.queryForObject(
                "select id from genres where naam ='test'", Long.class);
    }
    private long idVanTestKlant() {
        return super.jdbcTemplate.queryForObject(
                "select id from klanten where familienaam ='test'", Long.class);
    }
    private long idVanTestReservatie() {
        var sql ="insert into reservaties (klantid,filmid,reservatie) values ("+ idVanTestKlant()
                +","+idVanTestFilm()+",'2020-1-1')";
        super.jdbcTemplate.execute(sql);
        return super.jdbcTemplate.queryForObject(
                "select klantid from reservaties where reservatie ='2020-1-1'", Long.class);
    }

    @Test
    void findById(){
        assertThat(repository.findById(idVanTestReservatie()).get().getKlantid()).isEqualTo(idVanTestKlant() );
    }
    @Test
    void create() {
        var id = repository.create(
                new Reservatie(idVanTestKlant(),idVanTestFilm(),
                        LocalDateTime.now()))
                .get(0)
                .getKlantid();
        assertThat(id).isPositive();
    }
}
