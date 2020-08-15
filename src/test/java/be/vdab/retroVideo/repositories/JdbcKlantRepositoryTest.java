package be.vdab.retroVideo.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
@JdbcTest
@Import(JdbcKlantRepository.class)
@Sql("/insertEntries.sql")
public class JdbcKlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JdbcKlantRepository repository;

    public JdbcKlantRepositoryTest(JdbcKlantRepository repository) {
        this.repository = repository;
    }
    private long idVanTestKlant() {
        return super.jdbcTemplate.queryForObject(
                "select id from klanten where familienaam ='test'", Long.class);
    }
    @Test
    void findByBeginNaam(){
        assertThat(repository.findById(idVanTestKlant()).get().getFamilienaam()).startsWith("t");
    }
    @Test
    void findById(){
        assertThat(repository.findById(idVanTestKlant()).get().getFamilienaam()).isEqualTo("test");
    }
}
