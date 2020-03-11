package DBProsjekt;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FilmCtrl extends PersonCtrl{

    private PreparedStatement sjangre;
    private PreparedStatement settInnFilm;
    private PreparedStatement settInnSkuespiller;
    private PreparedStatement settInnForfatter;
    private PreparedStatement settInnRegissoor;
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

            this.settInnRegissoor = conn.prepareStatement(
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

    public void regFilm() {
        try {

            Scanner s1 = new Scanner(System.in);
            System.out.println("Skriv inn FilmID");
            String ID = s1.nextLine();
            int FilmID = Integer.parseInt(ID);


            System.out.println("Skriv inn tittel på filmen");
            String tittel = s1.nextLine();


            System.out.println("Er filmen på video? (Y/N)");
            boolean paaVideo = checkYesNo();


            System.out.println("Kan filmen  streames? (Y/N)");
            boolean Streaming = checkYesNo();


            System.out.println("Er filmen på TVn? (Y/N)");
            boolean TV = checkYesNo();


            System.out.println("Er filmen på kino? (Y/N)");
            boolean Kino = checkYesNo();


            System.out.println("Hva er lengden på filmen?");
            String temp = s1.nextLine();
            int lengde = Integer.parseInt(temp);


            System.out.println("Skriv inn utgivelsesår");
            String temp1 = s1.nextLine();
            int utgivelsesaar = Integer.parseInt(temp1);


            System.out.println("Skriv inn lanseringsdato");
            String lanseringsdato = s1.nextLine();


            System.out.println("Skriv inn beskrivelse av filmen");
            String beskrivelse = s1.nextLine();



            if (!checkDateFormat(lanseringsdato)) {
                throw new IllegalArgumentException("The date must be in format: YYYY-MM-DD");
            }
            settInnFilm.setInt(1, FilmID);
            settInnFilm.setString(2, tittel);
            settInnFilm.setBoolean(3, paaVideo);
            settInnFilm.setBoolean(4, Streaming);
            settInnFilm.setBoolean(5, TV);
            settInnFilm.setBoolean(6, Kino);
            settInnFilm.setInt(7, lengde);
            settInnFilm.setInt(8, utgivelsesaar);
            settInnFilm.setString(9, lanseringsdato);
            settInnFilm.setString(10, beskrivelse);
            settInnFilm.executeUpdate();

            System.out.println("Hvor mange skuespillere er det i filmen?");
            int iterate = Integer.parseInt(s1.nextLine());
            for (int i = 0; i < iterate; i++) {
                Skuespiller p = new Skuespiller();
                if (!personExists(p)) {
                    regPerson(p);
                }
                settInnSkuespiller.setInt(1, p.getPersonID());
                settInnSkuespiller.setInt(2, FilmID);
                settInnSkuespiller.setString(3, p.getRolle());
                settInnSkuespiller.executeUpdate();
            }

            System.out.println("Hvor mange regissører er med i filmen?");
            int iterate1 = Integer.parseInt(s1.nextLine());
            for (int i = 0; i < iterate1; i++) {
                Person p = new Person();
                if (!personExists(p)) {
                    regPerson(p);
                }
                settInnRegissoor.setInt(1, p.getPersonID());
                settInnRegissoor.setInt(2, FilmID);
                settInnRegissoor.executeUpdate();
            }

            System.out.println("Hvor mange forfattere er med i filmen?");
            int iterate2 = Integer.parseInt(s1.nextLine());
            for (int i = 0; i < iterate2; i++) {
                Person p = new Person();
                if (!personExists(p)) {
                    regPerson(p);
                }
                settInnForfatter.setInt(1, p.getPersonID());
                settInnForfatter.setInt(2, FilmID);
                settInnForfatter.executeUpdate();
            }

            System.out.println("Hvor mange sjangre har denne filmen?");
            int iterate3 = Integer.parseInt(s1.nextLine());
            for (int i = 0; i < iterate3; i++) {
                Sjanger sjanger = new Sjanger();
                if (!sjangerExists(sjanger)) {
                    regSjanger(sjanger);
                }
                settInnSjanger.setInt(1, sjanger.getSjangerID());
                settInnSjanger.setInt(2, FilmID);
                settInnSjanger.executeUpdate();
            }

            System.out.println("Hvor mange selskaper har produsert denne filmen?");
            int iterate4 = Integer.parseInt(s1.nextLine());
            for (int i = 0; i < iterate4; i++) {
                Selskap s = new Selskap();
                if (!selskapExists(s)) {
                    regSelskap(s);
                }
                settInnSelskap.setInt(1, FilmID);
                settInnSelskap.setString(2, s.getURL());
                settInnSelskap.executeUpdate();
            }

            System.out.println("Hvor mange forskjellige musikkstykker er med i filmen?");
            int iterate5 = Integer.parseInt(s1.nextLine());
            for (int i = 0; i < iterate5; i++) {
                Musikk m = new Musikk();
                if (!musicExists(m)) {
                    regMusikk(m);
                }
                settInnMusikk.setInt(1, m.getMusikkID());
                settInnMusikk.setInt(2, FilmID);
                settInnMusikk.executeUpdate();
            }

            PreparedStatement tempStatement = conn.prepareStatement("insert into Episode values ((?), (?), (?))");
            PreparedStatement tempStatement2 = conn.prepareStatement("insert into EpisodeIFilm values ((?), (?))");

            System.out.println("Er dette en serie, og hvis så, hvor mange episoder er det? Skriv 0 hvis det ikke er noen episoder");
            int iterate6 = Integer.parseInt(s1.nextLine());
            if (iterate6 >= 1) {
                for (int i = 0; i < iterate6; i++) {
                    Episode e = new Episode();
                    tempStatement.setInt(1, e.getEpisodeNummer());
                    tempStatement.setString(2, e.getEpisodeNavn());
                    tempStatement.setInt(3, FilmID);
                    tempStatement2.setInt(1, e.getEpisodeNummer());
                    tempStatement2.setInt(2, FilmID);
                    tempStatement.executeUpdate();
                    tempStatement2.executeUpdate();
                }
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

    public boolean checkYesNo() {
        Scanner s = new Scanner(System.in);
        String temp = s.nextLine();
        if(temp.equals("Y")){
            return true;
        } else if (temp.equals("N")) {
            return false;
        }
        else {
            System.out.println("Du må skrive inn enten Y eller N:");
            return checkYesNo();
        }
    }

}
