package DBProsjekt;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class Bruker {

    private final String brukernavn;

    public Bruker() {
        Scanner s1 = new Scanner(System.in);
        System.out.println("Skriv inn brukernavn");
        String brukernavn = s1.nextLine();

        this.brukernavn = brukernavn;
    }

    public String getBrukernavn() {
        return this.brukernavn;
    }


}
