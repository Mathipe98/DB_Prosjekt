package DBProsjekt;

public class Person {

    private final int PersonID;
    private final String Navn;
    private final int foodselsaar;

    private String foodselsland;

    public Person(int personID, String Navn, int foodselsaar, String foodselsland) {
        if (personID < 0 || foodselsaar < 1900) {
            throw new IllegalArgumentException();
        }
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
