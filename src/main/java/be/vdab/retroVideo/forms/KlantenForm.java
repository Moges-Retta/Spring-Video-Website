package be.vdab.retroVideo.forms;

import javax.servlet.http.HttpServlet;
import javax.validation.constraints.NotNull;

public class KlantenForm extends HttpServlet {
    @NotNull
    private final String klantenNaam;

    public KlantenForm(@NotNull String klantenNaam) {
        this.klantenNaam = klantenNaam;
    }

    public String getKlantenNaam() {
        return klantenNaam;
    }
}
