package DBProsjekt;

import java.sql.PreparedStatement;

public class Sjanger extends DBConn{
    public final int sjangerID;
    public final String navn;

    public Sjanger(int sjangerID, String navn){
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
