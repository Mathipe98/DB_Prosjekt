package DBProsjekt;

<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.util.Scanner;

=======
>>>>>>> 0e9b4bff971677b2721eff0237a029a6d6b5c30a
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
