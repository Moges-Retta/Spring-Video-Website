package be.vdab.retroVideo.services;

import be.vdab.retroVideo.domain.Reservatie;

import java.util.List;
import java.util.Optional;

public interface ReservatieService {
    Optional<Reservatie> findById(long id);
    List<Reservatie> create(Reservatie reservatie);
}
