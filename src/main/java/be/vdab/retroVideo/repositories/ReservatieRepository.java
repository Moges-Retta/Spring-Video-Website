package be.vdab.retroVideo.repositories;

import be.vdab.retroVideo.domain.Reservatie;

import java.util.List;
import java.util.Optional;

public interface ReservatieRepository {
    Optional<Reservatie> findById(long id);
    List<Reservatie> create(Reservatie reservatie);
}
