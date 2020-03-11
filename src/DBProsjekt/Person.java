package DBProsjekt;

import java.util.Scanner;

public class Person {

    private final int PersonID;
    private final String Navn;
    private final int foodselsaar;

    private String foodselsland;

    public Person() {
        Scanner s4 = new Scanner(System.in);
        System.out.println("Skriv inn PersonID");
        String ID = s4.nextLine();
        int personID = Integer.parseInt(ID);

        Scanner s2 = new Scanner(System.in);
        System.out.println("Skriv inn fødselsår");
        String input = s2.nextLine();
        int foodselsaar = Integer.parseInt(input);

        if (personID < 0 || foodselsaar < 1900) {
            throw new IllegalArgumentException();
        }
        Scanner s1 = new Scanner(System.in);
        System.out.println("Skriv inn navn på personen");
        String Navn = s1.nextLine();

        Scanner s3 = new Scanner(System.in);
        System.out.println("Skriv inn fødselsland");
        String foodselsland = s3.nextLine();



        this.PersonID = personID;
        this.Navn = Navn;
        this.foodselsaar = foodselsaar;
        this.foodselsland = foodselsland;
    }

    public int getPersonID() {
        return PersonID;
    }

    public String getNavn() {
        return Navn;
    }

    public int getFoodselsaar() {
        return foodselsaar;
    }

    public String getFoodselsland() {
        return foodselsland;
    }




}
