package DBProsjekt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmCtrl extends DBConn implements DBInterface{

    private PreparedStatement sjangre;
    private PreparedStatement settInnFilm;
    private PreparedStatement settInnSkuespiller;
    private PreparedStatement settInnForfatter;
    private PreparedStatement settInnRegissør;
    private PreparedStatement settInnPerson;

    public FilmCtrl() {
        this.connect();
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


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void regFilm(
            int FilmID, String tittel, boolean påVideo, boolean Streaming, boolean TV,
            boolean Kino, int lengde, int utgivelsesår, String lanseringsdato, String beskrivelse) {

    }


}
