package DBProsjekt;

public class Sjanger {
    public static int sjangerID;
    public static String navn;

    public Sjanger(int sjangerID, String navn){
        this.sjangerID  = sjangerID;
        this.navn = navn;
    }

    public static int getSjangerID() {
        return sjangerID;
    }

    public static String getNavn() {
        return navn;
    }
}
