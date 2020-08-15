package be.vdab.retroVideo.repositories;

import be.vdab.retroVideo.domain.Klant;

import java.util.List;
import java.util.Optional;

public interface KlantRepository {
    List<Klant> findByBeginNaam(String beginNaam);
    Optional<Klant> findById(long id);
}
