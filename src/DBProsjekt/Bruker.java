package DBProsjekt;

import java.sql.PreparedStatement;

public class Bruker {

    private final String brukernavn;

    public Bruker(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getBrukernavn() {
        return this.brukernavn;
    }


}
