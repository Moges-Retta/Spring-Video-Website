package be.vdab.retroVideo.services;

import be.vdab.retroVideo.domain.Klant;
import be.vdab.retroVideo.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultKlantService implements KlantService{
    private final KlantRepository repository;

    public DefaultKlantService(KlantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Klant> findByBeginNaam(String beginNaam) {
        return repository.findByBeginNaam(beginNaam);
    }

    @Override
    public Optional<Klant> findById(long id) {
        return  repository.findById(id);
    }
}
