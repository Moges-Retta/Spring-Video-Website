package be.vdab.retroVideo.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class Film {
    private final long id;
    private final long genreId;
    @NotEmpty
    private final String title;
    @PositiveOrZero
    private final long voorraad;
    @PositiveOrZero
    private final long gereserveerd;
    @PositiveOrZero
    @NumberFormat(pattern = "0.00")
    private final BigDecimal prijs;

    public Film(long id, long genreId, String title, long voorraad, long gereserveerd, BigDecimal prijs) {
        this.id = id;
        this.genreId = genreId;
        this.title = title;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
    }

    public long getId() {
        return id;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getTitle() {
        return title;
    }

    public long getVoorraad() {
        return voorraad;
    }

    public long getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

}
