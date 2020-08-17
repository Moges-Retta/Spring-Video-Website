package be.vdab.retroVideo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservatie {
    private final long klantid;
    private final long filmid;
    @DateTimeFormat
    private final LocalDateTime reservatie;

    public Reservatie(long klantid, long filmid, LocalDateTime reservatie) {
        this.klantid = klantid;
        this.filmid = filmid;
        this.reservatie = reservatie;
    }
    public long getKlantid() {
        return klantid;
    }

    public long getFilmid() {
        return filmid;
    }

    public LocalDateTime getReservatie() {
        return reservatie;
    }
}
