package be.vdab.retroVideo.repositories;

import be.vdab.retroVideo.domain.Klant;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JdbcKlantRepository implements KlantRepository{
    private final JdbcTemplate template;

    private final RowMapper<Klant> klantMapper =
            (result, rowNum) ->
                    new Klant(result.getLong("id"),
                            result.getString("familienaam"),
                            result.getString("voornaam"),
                            result.getString("straatNummer"),
                            result.getString("postcode"),
                            result.getString("gemeente"));

    public JdbcKlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Klant> findByBeginNaam(String beginNaam) {
        var sql =
                "select * from klanten where familienaam like '%' ? order by familienaam";
        return template.query(sql, klantMapper, beginNaam+'%');
    }

    @Override
    public Optional<Klant> findById(long id) {
        try {
            var sql = "select * from klanten where id = ?";
            return Optional.of(template.queryForObject(sql, klantMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
}
