package DBProsjekt;

public class Person {

    private final int PersonID;
    private final String Navn;
    private final int fødselsår;

    private String fødselsland;

    public Person(int personID, String Navn, int fødselsår, String fødselsland) {
        if (personID < 0 || fødselsår < 1900) {
            throw new IllegalArgumentException();
        }
        this.PersonID = personID;
        this.Navn = Navn;
        this.fødselsår = fødselsår;
        this.fødselsland = fødselsland;
    }

    public int getPersonID() {
        return PersonID;
    }

    public String getNavn() {
        return Navn;
    }

    public int getFødselsår() {
        return fødselsår;
    }

    public String getFødselsland() {
        return fødselsland;
    }




}
