package DBProsjekt;

import java.sql.PreparedStatement;

public class Selskap extends DBConn{

    private final String URL;
    private final String adresse;
    private final String land;

    public Selskap(String URL, String adresse, String land) {
        this.URL = URL;
        this.adresse = adresse;
        this.land = land;
    }

    public String getURL() {
        return URL;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLand() {
        return land;
    }

}
