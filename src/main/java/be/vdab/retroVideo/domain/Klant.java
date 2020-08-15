package be.vdab.retroVideo.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Klant {
    private final long id;
    @NotEmpty
    private final String familienaam;
    @NotEmpty
    private final String voornaam;
    @NotEmpty
    private final String straatNummer;
    @Min(1000)
    @Max(99999)
    private final String postcode;
    @NotEmpty
    private final String gemeente;


    public Klant(long id, String familienaam, String voornaam, String straatNummer, String postcode, String gemeente) {
        this.id = id;
        this.familienaam = familienaam;
        this.voornaam = voornaam;
        this.straatNummer = straatNummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public long getId() {
        return id;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getStraatNummer() {
        return straatNummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
