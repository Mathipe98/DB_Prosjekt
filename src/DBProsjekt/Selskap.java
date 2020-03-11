package DBProsjekt;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class Selskap extends DBConn{

    private final String URL;
    private final String adresse;
    private final String land;

    public Selskap() {
        Scanner s1 = new Scanner(System.in);
        System.out.println("Skriv inn URLen til selskapet");
        String URL = s1.nextLine();

        Scanner s2 = new Scanner(System.in);
        System.out.println("Skriv inn adressen til selskapet");
        String adresse = s2.nextLine();

        Scanner s3 = new Scanner(System.in);
        System.out.println("Skriv inn landet der selskapet hører til");
        String land = s3.nextLine();


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
