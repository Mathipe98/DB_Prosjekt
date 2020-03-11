package DBProsjekt;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class Musikk extends DBConn{

    private final int MusikkID;
    private final String Komponist;
    private final String Artist;

    public Musikk(){

        Scanner s3 = new Scanner(System.in);
        System.out.println("Skriv inn MusikkID");
        String ID = s3.nextLine();
        int MusikkID = Integer.parseInt(ID);

        this.MusikkID = MusikkID;

        Scanner s1 = new Scanner(System.in);
        System.out.println("Skriv inn Komponistnavn");
        String KomponistNavn = s1.nextLine();

        Scanner s2 = new Scanner(System.in);
        System.out.println("Skriv inn ArtistNavn");
        String ArtistNavn = s2.nextLine();



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
