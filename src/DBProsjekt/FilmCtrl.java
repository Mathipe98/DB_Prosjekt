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
    private PreparedStatement settInnSjanger;
    private PreparedStatement settInnSelskap;
    private PreparedStatement settInnMusikk;

    public FilmCtrl() {
        super();
        this.startReg();
    }

    public void startReg() {
        try {

            this.settInnFilm = conn.prepareStatement(
                    "insert into Film values ((?), (?), (?), (?), (?), (?), (?), (?), (?), (?))"
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

            this.settInnSjanger = conn.prepareStatement(
                    "insert into SjangerIFilm values ((?), (?))"
            );

            this.settInnSelskap = conn.prepareStatement(
                    "insert into GirUtFilm values ((?), (?))"
            );

            this.settInnMusikk = conn.prepareStatement(
                    "insert into MusikkIFilm values ((?), (?))"
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
            List<Musikk> musikk, List<Selskap> selskap, List<Episode> episoder) {
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

            for (Sjanger s : sjangre) {
                if (!sjangerExists(s)) {
                    regSjanger(s);
                }
                settInnSjanger.setInt(1, s.getSjangerID());
                settInnSjanger.setInt(2, FilmID);
                settInnSjanger.executeUpdate();
            }

            for (Selskap s : selskap) {
                if (!selskapExists(s)) {
                    regSelskap(s);
                }
                settInnSelskap.setInt(1, FilmID);
                settInnSelskap.setString(2, s.getURL());
                settInnSelskap.executeUpdate();
            }

            for (Musikk m : musikk) {
                if (!musicExists(m)) {
                    regMusikk(m);
                }
                settInnMusikk.setInt(1, m.getMusikkID());
                settInnMusikk.setInt(2, FilmID);
                settInnMusikk.executeUpdate();
            }

            PreparedStatement temp1 = conn.prepareStatement("insert into Episode values ((?), (?), (?))");
            PreparedStatement temp2 = conn.prepareStatement("insert into EpisodeIFilm values ((?), (?))");

            for (Episode e : episoder) {
                temp1.setInt(1, e.getEpisodeNummer());
                temp1.setString(2, e.getEpisodeNavn());
                temp1.setInt(3, FilmID);
                temp2.setInt(1, e.getEpisodeNummer());
                temp2.setInt(2, FilmID);
                temp1.executeUpdate();
                temp2.executeUpdate();
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Film registration went to fuck all");
        }

    }

    public boolean sjangerExists(Sjanger s) {
        try {
            PreparedStatement getSjanger = conn.prepareStatement(
                    "select * from Sjanger where SjangerID = (?)"
            );
            getSjanger.setInt(1, s.getSjangerID());
            //ResultSet temp = getPersons.executeQuery();
            return getSjanger.executeQuery().next();
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean selskapExists(Selskap s) {
        try {
            PreparedStatement getURL = conn.prepareStatement(
                    "select * from Selskap where URL = (?)"
            );
            getURL.setString(1, s.getURL());
            //ResultSet temp = getPersons.executeQuery();
            return getURL.executeQuery().next();
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean brukerExists(Bruker b) {
        try {
            PreparedStatement getBruker = conn.prepareStatement(
                    "select * from Bruker where Brukernavn = (?)"
            );
            getBruker.setString(1, b.getBrukernavn());
            return getBruker.executeQuery().next();
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean musicExists(Musikk m) {
        try {
            PreparedStatement getMusic = conn.prepareStatement(
                    "select * from Musikk where MusikkID = (?)"
            );
            getMusic.setInt(1, m.getMusikkID());
            //ResultSet temp = getPersons.executeQuery();
            return getMusic.executeQuery().next();
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public void regSjanger(Sjanger s) {
        try {
            PreparedStatement registrer = conn.prepareStatement(
                    "insert into Sjanger values ((?), (?))");
            registrer.setInt(1, s.getSjangerID());
            registrer.setString(2, s.getNavn());
            registrer.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error when inserting Sjanger");
            e.printStackTrace();
        }
    }

    public void regSelskap(Selskap s) {
        try {
            PreparedStatement registrer = conn.prepareStatement(
                    "insert into Selskap values ((?), (?), (?))");
            registrer.setString(1, s.getURL());
            registrer.setString(2, s.getAdresse());
            registrer.setString(3, s.getLand());
            registrer.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error when inserting Selskap");
            e.printStackTrace();
        }
    }

    public void regBruker(Bruker b) {
        try {
            PreparedStatement registrer = conn.prepareStatement(
                    "insert into Bruker values ((?))");
            registrer.setString(1, b.getBrukernavn());
            registrer.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error when inserting Bruker");
            e.printStackTrace();
        }
    }

    public void regMusikk(Musikk m) {
        try {
            PreparedStatement registrer = conn.prepareStatement(
                    "insert into Musikk values ((?), (?), (?))");
            registrer.setInt(1, m.getMusikkID());
            registrer.setString(2, m.getKomponist());
            registrer.setString(3, m.getArtist());
            registrer.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error when inserting Musikk");
            e.printStackTrace();
        }
    }

}
