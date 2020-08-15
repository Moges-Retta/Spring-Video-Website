package be.vdab.retroVideo.services;

import be.vdab.retroVideo.domain.Reservatie;
import be.vdab.retroVideo.repositories.JdbcReservatieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DefaultReservatieService implements ReservatieService{
    private final JdbcReservatieRepository repository;

    public DefaultReservatieService(JdbcReservatieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Reservatie> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public long create(Reservatie reservatie) {
        return repository.create(reservatie);
    }
}
