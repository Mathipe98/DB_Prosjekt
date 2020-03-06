package DBProsjekt;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class FilmCtrl extends PersonCtrl{

    private PreparedStatement sjangre;
    private PreparedStatement settInnFilm;
    private PreparedStatement settInnSkuespiller;
    private PreparedStatement settInnForfatter;
    private PreparedStatement settInnRegissør;

    public FilmCtrl() {
        super();
        this.startReg();
    }

    public void startReg() {
        try {
            String del1 = "select Sjanger.Navn from Selskap inner join ErSjanger on Sjanger.URL = ErSjanger.URL" +
                    " inner join Film on ErSjanger.FilmID = Film.FilmID ";
            String del2 = "inner join SjangerIFilm on Film.FilmID = SjangerIFilm.FilmID " +
                    "inner join Sjanger on SjangerIFilm.FilmID = Sjanger.FilmID";
            this.sjangre = conn.prepareStatement(
                    del1 + del2
            );

            this.settInnFilm = conn.prepareStatement(
                    "insert into film values ((?), (?), (?), (?), (?), (?), (?), (?), (?), (?))"
            );

            this.settInnSkuespiller = conn.prepareStatement(
                    "insert into ErSkuespiller values ((?), (?), (?))"
            );

            this.settInnRegissør = conn.prepareStatement(
                    "insert into ErRegissør values ((?), (?))"
            );

            this.settInnForfatter = conn.prepareStatement(
                    "insert into ErForfatter values ((?), (?))"
            );


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDateFormat(String date) {
        if (date.indexOf("-") == 4 && date.lastIndexOf("-") == 7) {
            return true;
        }
        return false;
    }

    public void regFilm(
            int FilmID, String tittel, boolean påVideo, boolean Streaming, boolean TV,
            boolean Kino, int lengde, int utgivelsesår, String lanseringsdato, String beskrivelse,
            List<Skuespiller> skuespillere, List<Person> regissører, List<Person> forfattere, List<Sjanger> sjangre,
            List<Musikk> musikk, List<Selskap> selskap, List<Bruker> brukere, List<Episode> episoder) {
        try {
            if (!checkDateFormat(lanseringsdato)) {
                throw new IllegalArgumentException("The date must be in format: YYYY-MM-DD");
            }
            settInnFilm.setInt(1, FilmID);
            settInnFilm.setString(2, tittel);
            settInnFilm.setBoolean(3, påVideo);
            settInnFilm.setBoolean(4, Streaming);
            settInnFilm.setBoolean(5, TV);
            settInnFilm.setBoolean(6, Kino);
            settInnFilm.setInt(7, lengde);
            settInnFilm.setInt(8, utgivelsesår);
            settInnFilm.setString(9, lanseringsdato);
            settInnFilm.setString(10, beskrivelse);
            settInnFilm.executeUpdate();

            for (Skuespiller p : skuespillere) {
                if (!personExists(p)) {
                    regPerson(p);
                }
                settInnSkuespiller.setInt(1, p.getPersonID());
                settInnSkuespiller.setInt(2, FilmID);
                settInnSkuespiller.setString(3, p.getRolle());
                settInnSkuespiller.executeUpdate();
            }

            for (Person p : regissører) {
                if (!personExists(p)) {
                    regPerson(p);
                }
                settInnRegissør.setInt(1, p.getPersonID());
                settInnRegissør.setInt(2, FilmID);
                settInnRegissør.executeUpdate();
            }

            for (Person p : forfattere) {
                if (!personExists(p)) {
                    regPerson(p);
                }
                settInnForfatter.setInt(1, p.getPersonID());
                settInnForfatter.setInt(2, FilmID);
                settInnForfatter.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
