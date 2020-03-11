package DBProsjekt;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class Sjanger extends DBConn{
    public final int sjangerID;
    public final String navn;

    public Sjanger(){

        Scanner s1 = new Scanner(System.in);
        System.out.println("Skriv inn sjangerID");
        String ID = s1.nextLine();
        int sjangerID = Integer.parseInt(ID);

        Scanner s2 = new Scanner(System.in);
        System.out.println("Skriv inn navnet på sjangeren");
        String navn = s2.nextLine();

        this.sjangerID  = sjangerID;
        this.navn = navn;
    }

    public int getSjangerID() {
        return sjangerID;
    }

    public String getNavn() {
        return navn;
    }

}
