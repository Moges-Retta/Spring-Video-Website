package be.vdab.retroVideo.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Long> ids = new LinkedList<>();
    public void voegToe(long id) {
        ids.add(id);
    }
    public List<Long> getIds() {
        return ids;
    }
    public void verwijder(long id) {
        ids.remove(id);
    }
}
