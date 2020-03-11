package DBProsjekt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SjangerCtrl extends DBConn {

    private PreparedStatement fåUtSelskaper;
    private PreparedStatement fåUtFilmer;
    private PreparedStatement fåUtSjangre;
    private PreparedStatement sjangre;

    public SjangerCtrl() {
        this.connect();
    }

    public void registerQueries() {
        try {

            this.fåUtSelskaper = conn.prepareStatement(
                    "select URL from Selskap"
            );

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
                    "select count(*), Navn from Film f inner join SjangerIFilm SIF on f.FilmID = SIF.FilmID "
                    + "inner join Sjanger s on SIF.SjangerID = s.SjangerID" +
                            " AND f.Tittel = (?)"
            );
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Something went wrong, fuck");
        }
    }


    public List<String> URLerIDatabase () {
        try {
            List<String> templist = new ArrayList<>();
            ResultSet results = fåUtSelskaper.executeQuery();
            while (results.next()) {
                templist.add(results.getString(1));
            }
            return templist;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
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

    //public int countSjanger(

    //public void mainSjangerMethod(List<>)

}
