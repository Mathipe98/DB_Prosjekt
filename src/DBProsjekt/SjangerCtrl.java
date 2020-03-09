package DBProsjekt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SjangerCtrl extends DBConn {

    private PreparedStatement fåUtFilmer;
    private PreparedStatement fåUtSjangre;
    private PreparedStatement sjangre;

    public SjangerCtrl() {
        this.connect();
    }

    public void registerQueries() {
        try {
            String del1 = "select Sjanger.Navn from Selskap as s inner join ErSjanger as ES on s.URL = ES.URL" +
                    " inner join Film as f on ES.FilmID = f.FilmID ";
            String del2 = "inner join SjangerIFilm as SIF on f.FilmID = SIF.FilmID " +
                    "inner join Sjanger on SIF.FilmID = Sjanger.FilmID";
            this.sjangre = conn.prepareStatement(
                    del1 + del2
            );

            this.fåUtFilmer = conn.prepareStatement(
                    "select count(*) from Selskap inner join GirUtFilm on Selskap.URL = GirUtFilm.url "
                    + "inner join Film on GirUtFilm.FilmID = Film.FilmID AND Selskap.URL = (?)"
            );

            this.fåUtSjangre = conn.prepareStatement(
                    "select count(*) from Film f inner join SjangerIFilm SIF on f.FilmID = SIF.FilmID "
                    + "inner join Sjanger s on SIF.SjangerID = s.SjangerID" +
                            "AND s.Navn = (?) AND f.Tittel = (?)"
            );
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Something went wrong, fuck");
        }
    }

    public int filmerPerSelskap(Selskap s) {
        try {
            fåUtFilmer.setString(1, s.getURL());
            return fåUtFilmer.executeQuery().getInt(1);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.printf("Something's wrong");
            return 0;
        }
    }

}
