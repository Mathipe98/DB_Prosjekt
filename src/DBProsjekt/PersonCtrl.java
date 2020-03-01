package DBProsjekt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCtrl extends DBConn {
    private PreparedStatement personStatement;
    private PreparedStatement regissørStatement;
    private PreparedStatement skuespillerStatement;
    private PreparedStatement forfatterStatement;
    private PreparedStatement fåUtRoller;

    public PersonCtrl() {
        this.connect();
        this.startReg();
    }

    public void startReg() {
        try {
            this.personStatement = conn.prepareStatement
                    ("insert into Person values((?), (?), (?), (?))");
            this.regissørStatement = conn.prepareStatement
                    ("insert into erregissør values ( (?), (?))");
            this.skuespillerStatement = conn.prepareStatement
                    ("insert into erskuespiller values ( (?), (?))");
            this.forfatterStatement = conn.prepareStatement
                    ("insert into erforfatter values ( (?), (?))");
            this.fåUtRoller = conn.prepareStatement
                    ("select ErSkuespiller.Rolle, Film.Tittel from ErSkuespiller inner join Person on ErSkuespiller.PersonID = Person.PersonID AND Person.Navn = (?)" +
                            "inner join Film on Film.FilmID = ErSkuespiller.FilmID");
        }
        catch(SQLException s) {
            System.out.println("DB error with prepare of insert to Person");
        }
    }

    public void regPerson(String navn, int fødselsår, String fødselsland) {
        try {
            personStatement.setInt(1, 0);
            personStatement.setString(2, navn);
            personStatement.setInt(3, fødselsår);
            personStatement.setString(4, fødselsland);
            personStatement.executeUpdate();
        }
        catch(SQLException s) {
            System.out.println("Error during insert into Person with navn = "
            + navn + " , fødselsår = " + fødselsår + " , and fødselsland = " + fødselsland);
            s.printStackTrace();
        }
    }

    public void regSkuespiller(String filmnavn, String skuespillernavn) {

    }

    public void getRoles(String skuespillerNavn) {
        try {
            fåUtRoller.setString(1, skuespillerNavn);
            ResultSet result = fåUtRoller.executeQuery();
            while (result.next()) {
                System.out.println(skuespillerNavn + " spiller rollen som " + result.getString(1)
                + " i filmen " + result.getString(2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong, fucko.");
        }
    }
}
