package DBProsjekt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonCtrl extends DBConn {
    private PreparedStatement personStatement;
    private PreparedStatement fåUtRoller;
    private PreparedStatement fåUtFilmer;
    private PreparedStatement regissørStatement;
    private PreparedStatement skuespillerStatement;
    private PreparedStatement forfatterStatement;

    public PersonCtrl() {
        this.connect(); /* her kobler vi objektet vi lager (altså this, dette PersonCtrl objektet)
        til databasen med en gang det opprettes */

        this.startReg(); /* her oppretter vi med en gang spørringene/insertion statements ved oppretting av dette objektet */
    }

    /* Forklaring: java SQL implementasjon har noe som heter "PreparedStatement" objekter, og et slikt objekt er omtrent
    hva det høres ut som: du forbereder en allerede ferdigskrevet SQL spørring, hvor du etterlater visse verdier
    i spørringen som du kan sette inn senere. Der hvor du putter '(?)' kan du senere putte inn de verdiene du ønsker
     */
    public void startReg() {
        try {

            /* Må bruke conn objektet (det har vi fra DBConn klassen, basically et objekt som er koblet til databasen)
            for å forberede og utføre SQL spørringer. Alle spørringer og oppdateringer blir utført fra dette samme objektet.
             */
            //SQL insertion for en Person-entitet
            this.personStatement = conn.prepareStatement
                    ("insert into Person values((?), (?), (?), (?))");

            //SQL insertion for ErRegissør
            this.regissørStatement = conn.prepareStatement
                    ("insert into erregissør values ( (?), (?))");

            //SQL insertion for ErSkuespiller
            this.skuespillerStatement = conn.prepareStatement
                    ("insert into erskuespiller values ( (?), (?))");

            //SQL insertion for ErForfatter
            this.forfatterStatement = conn.prepareStatement
                    ("insert into erforfatter values ( (?), (?))");

            /*SQL spørring for å få ut alle roller en skuespiller spiller som
            (denne printer også ut alle filmer) */
            this.fåUtRoller = conn.prepareStatement
                    ("select ErSkuespiller.Rolle, Film.Tittel from ErSkuespiller inner join Person on ErSkuespiller.PersonID = Person.PersonID AND Person.Navn = (?)" +
                            "inner join Film on Film.FilmID = ErSkuespiller.FilmID");

            //SQL spørring for å få ut alle filmer en skuespiller spiller i
            this.fåUtFilmer = conn.prepareStatement(
                    "select Film.Tittel from Person inner join ErSkuespiller on Person.PersonID = ErSkuespiller.PersonID AND Person.Navn = (?) " +
                            "inner join Film on Film.FilmID = ErSkuespiller.FilmID"
            );

        }
        catch(SQLException s) {
            System.out.println("DB error with prepare of insert to Person");
        }
    }

    public void regPerson(String navn, int fødselsår, String fødselsland) {
        try {

            /*
            Husk at formen på insertion for Person så slik ut:
            insert into Person values((?), (?), (?), (?))
                                       ^1   ^2   ^3   ^4
             Tallene i forrige linje representerer index.
             Hvis vi da sier setInt(1), så betyr det at vi skal sette et tall inn i første '(?)',
             fordi java SQL objekter har metoder for å sette inn ulike datatyper (String, int, double, bool etc.)
             Så vi setter da første '(?)' til å være 0, andre (?) til å være 'navn', altså variabelen vi tar inn,
             tredje (?) til å være fødselsår, og fjerde (?) til å bli fødselsland.

             Den fullførte SQL setningen vil da set ut som:
             insert into Person values(0, navn, fødselsår, fødselsland)

             Hvis vi da kaller metoden med regPerson("Morgan Freeman", 1937, "Shawshank Redemption"), da vil det se slik ut:
             insert into Person values(0, "Morgan Freeman", 1937, "Shawshank Redemption")
             */
            personStatement.setInt(1, 0);
            personStatement.setString(2, navn);
            personStatement.setInt(3, fødselsår);
            personStatement.setString(4, fødselsland);
            personStatement.executeUpdate(); /*executeUpdate brukes når man skal oppdatere informasjon i en tabell
            executeQuery brukes når man har spørringer, i.e. select */
        }
        catch(SQLException s) {
            System.out.println("Error during insert into Person with navn = "
            + navn + " , fødselsår = " + fødselsår + " , and fødselsland = " + fødselsland);
            s.printStackTrace();
        }
    }

    public void regSkuespiller(String filmnavn, String skuespillernavn) {

    }

    public List<String> getRoles(String skuespillerNavn) {
        try {
            /* Setter (?) = skuespillerNavn, slik at vi putter inn i fåUtRoller queryen den verdien som mangler */
            fåUtRoller.setString(1, skuespillerNavn);

            /* Resultatene fra queryen blir satt til variabelen result */
            ResultSet result = fåUtRoller.executeQuery();

            /* Lager liste for å lagre informasjon fra query */
            List<String> roles = new ArrayList<>();

            /* Itererer gjennom alle rollenavnene vi får, altså antall elementer vi får ut fra query */
            while (result.next()) {
                System.out.println(skuespillerNavn + " spiller rollen som " + result.getString(1)
                + " i filmen " + result.getString(2));

                /* siden vi spør om Rolle i query (og ingen flere attributter), så vil denne ha index 1 (og det vil ikke
                eksistere noen andre indekser til å hente ut info. Altså vil getString(1) gi ut rollenavnet her.
                 */
                roles.add(result.getString(1));
            }
            return roles;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong, fucko.");
        }
        return null;
    }

    public List<String> getMovies(String skuespillernavn) {
        try {
            /* Setter inn strengen i fåUtFilmer query til skuespillernavn, altså (?)  = skuespillernavn */
            fåUtFilmer.setString(1, skuespillernavn);

            /* Variabelen results blir innholdet fra select query */
            ResultSet results = fåUtFilmer.executeQuery();

            /* Liste for å lagre informasjonen vi får ut fra query */
            List<String> roles = new ArrayList<>();

            /* Iterer gjennom alle verdier queryen fikk */
            while (results.next()) {
                System.out.println(skuespillernavn + " spiller i filmen " + results.getString(1));

                /* Siden vi velger select Film.Tittel, så vil getString(index = 1) returnere den første
                variabelen vi spurte etter, altså Tittel. results.getString(1) vil derfor gi ut tittelen */
                roles.add(results.getString(1));
            }

            //returnerer lista med filmtitler
            return roles;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
