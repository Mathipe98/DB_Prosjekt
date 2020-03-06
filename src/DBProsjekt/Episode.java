package DBProsjekt;

public class Episode {

    private final int episodeNummer;
    private final String episodeNavn;

    public Episode(int episodeNummer, String episodeNavn) {
        this.episodeNummer = episodeNummer;
        this.episodeNavn = episodeNavn;
    }

    public int getEpisodeNummer() {
        return episodeNummer;
    }

    public String getEpisodeNavn() {
        return episodeNavn;
    }
}
