package DBProsjekt;

public class Skuespiller extends Person {

    private final String rolle;


    public Skuespiller(int personID, String Navn, int fødselsår, String fødselsland, String rolle) {
        super(personID, Navn, fødselsår, fødselsland);
        this.rolle = rolle;
    }

    public String getRolle() {
        return this.rolle;
    }
}
