package DBProsjekt;

public class Episode{

    private final int episodeNummer;
    private final String episodeNavn;

    public Episode(int episodeNummer, String episodeNavn) {
        if (episodeNummer < 0) {
            throw new IllegalArgumentException("Episode number must be >= 0");
        }
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
