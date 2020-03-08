package DBProsjekt;

import java.sql.PreparedStatement;

public class Musikk extends DBConn{

    private final int MusikkID;
    private final String Komponist;
    private final String Artist;

    public Musikk(int MusikkID, String KomponistNavn, String ArtistNavn){
        this.MusikkID = MusikkID;
        this.Komponist = KomponistNavn;
        this.Artist = ArtistNavn;
    }

    public int getMusikkID() {
        return MusikkID;
    }

    public String getKomponist() {
        return Komponist;
    }

    public String getArtist() {
        return Artist;
    }

}
