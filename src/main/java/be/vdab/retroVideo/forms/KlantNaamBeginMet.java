package be.vdab.retroVideo.forms;

import javax.servlet.http.HttpServlet;
import javax.validation.constraints.NotBlank;

public class KlantNaamBeginMet extends HttpServlet {
    @NotBlank
    private final String beginLetterMet;

    public KlantNaamBeginMet(@NotBlank String beginLetterMet) {
        this.beginLetterMet = beginLetterMet;
    }
    public String getBeginLetterMet() {
        return beginLetterMet;
    }
}
