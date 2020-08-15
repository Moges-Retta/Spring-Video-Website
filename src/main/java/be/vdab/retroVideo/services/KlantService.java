package be.vdab.retroVideo.services;

import be.vdab.retroVideo.domain.Klant;

import java.util.List;
import java.util.Optional;

public interface KlantService {
    List<Klant> findByBeginNaam(String beginNaam);
    Optional<Klant> findById(long id);

}
