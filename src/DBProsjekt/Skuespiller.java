package DBProsjekt;

public class Skuespiller extends Person {

    private final String rolle;


    public Skuespiller(int personID, String Navn, int foodselsaar, String foodselsland, String rolle) {
        super(personID, Navn, foodselsaar, foodselsland);
        this.rolle = rolle;
    }

    public String getRolle() {
        return this.rolle;
    }
}
