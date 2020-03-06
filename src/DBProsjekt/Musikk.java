package DBProsjekt;

public class Musikk {

    public static int KomponistID;
    public static String KomponistNavn;
    public static String ArtistNavn;

    public Musikk(int KomponistID, String KomponistNavn, String ArtistNavn){
        this.KomponistID = KomponistID;
        this.KomponistNavn = KomponistNavn;
        this.ArtistNavn = ArtistNavn;
    }

    public static int getKomponistID() {
        return KomponistID;
    }

    public static String getKomponistNavn() {
        return KomponistNavn;
    }

    public static String getArtistNavn() {
        return ArtistNavn;
    }
}
