package be.vdab.retroVideo.repositories;

import be.vdab.retroVideo.domain.Klant;
import be.vdab.retroVideo.domain.Reservatie;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public class JdbcReservatieRepository implements ReservatieRepository{
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-M-dd HH:mm:ss");

    private final RowMapper<Reservatie> reservatieMapper =
            (result, rowNum) ->
                    new Reservatie(result.getLong("klantid"),
                            result.getLong("filmid"),
                            LocalDateTime.parse(
                            result.getDate("reservatie").toLocalDate().format(format)));

    public JdbcReservatieRepository(JdbcTemplate template) {
        this.template = template;
        this.insert  = new SimpleJdbcInsert(template)
                .withTableName("reservaties")
                .usingGeneratedKeyColumns("id");;
    }

    @Override
    public Optional<Reservatie> findById(long id) {
        try {
            var sql = "select * from reservaties where klantid = ?";
            return Optional.of(template.queryForObject(sql, reservatieMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Reservatie> create(Reservatie reservatie) {
        var kolomWaarden
                = Map.of("klantid", reservatie.getKlantid(),
                "filmid", reservatie.getFilmid(),
                "reservatie",reservatie.getReservatie());
        insert.execute(kolomWaarden);
        return  template.query(
                "select * from reservaties where reservatie =?"
                ,reservatieMapper, reservatie.getReservatie());
    }
}
