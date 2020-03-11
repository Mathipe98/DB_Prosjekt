package DBProsjekt;

import java.util.Scanner;

public class Skuespiller extends Person {

    private final String rolle;


    public Skuespiller() {
        super();

        Scanner s1 = new Scanner(System.in);
        System.out.println("Skriv inn skuespillerens rolle");
        String rolle = s1.nextLine();
        this.rolle = rolle;
    }

    public String getRolle() {
        return this.rolle;
    }
}
