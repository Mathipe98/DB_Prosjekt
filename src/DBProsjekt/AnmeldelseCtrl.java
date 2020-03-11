package DBProsjekt;

import java.sql.PreparedStatement;

public class AnmeldelseCtrl extends DBConn {

    private PreparedStatement settInnAnmeldelse;

    public AnmeldelseCtrl() {
        this.connect();
        this.startReg();
    }

    public void startReg() {
        try {
            settInnAnmeldelse = conn.prepareStatement(
                    "insert into FilmAnmeldelse values ((?), (?), (?), (?), (?))"
            );
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void registrerAnmeldelse(Bruker b, int FilmID, String kommentar, int Rating, int episodenummer) {
        try {
            settInnAnmeldelse.setString(1, b.getBrukernavn());
            settInnAnmeldelse.setInt(2, FilmID);
            settInnAnmeldelse.setString(3, kommentar);
            settInnAnmeldelse.setInt(4, Rating);
            settInnAnmeldelse.setInt(5, episodenummer);
            settInnAnmeldelse.executeUpdate();
            this.startReg();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
