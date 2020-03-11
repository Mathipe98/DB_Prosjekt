package DBProsjekt;

import java.util.Scanner;

public class Episode{

    private final int episodeNummer;
    private final String episodeNavn;

    public Episode() {
        Scanner s1 = new Scanner(System.in);

        System.out.println("Skriv episodenummer");
        String ID = s1.nextLine();
        int episodeNummer = Integer.parseInt(ID);


        System.out.println("Skriv inn navnet på episoden");
        String episodeNavn = s1.nextLine();

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
