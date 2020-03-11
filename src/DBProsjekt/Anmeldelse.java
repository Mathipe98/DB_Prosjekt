package DBProsjekt;

public class Anmeldelse {

    private final int EpisodeNummer;
    private final String kommentar;
    private final int rating;

    public Anmeldelse (int episodeNummer, String kommentar, int rating) {
        if (rating < 0  || rating > 10) {
            throw new IllegalArgumentException("Rating has to be between 0 and 10");
        }
        this.EpisodeNummer = episodeNummer;
        this.kommentar = kommentar;
        this.rating = rating;
    }

    public int getEpisodeNummer() {
        return EpisodeNummer;
    }

    public String getKommentar() {
        return kommentar;
    }

    public int getRating() {
        return rating;
    }
}
