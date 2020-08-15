package be.vdab.retroVideo.domain;

import javax.validation.constraints.NotEmpty;

public class Genre {
    private final long id;
    @NotEmpty
    private final String naam;

    public Genre(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
