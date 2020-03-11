package DBProsjekt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author sveinbra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        FilmCtrl test = new FilmCtrl();

        Skuespiller s1 = new Skuespiller(5, "Morgan Freeman", 1937, "USA", "Ellis 'Red' Redding");
        Musikk m1 = new Musikk(1, "John Williams", "Orchestra");
        Selskap selskap1 = new Selskap("www.warnerbros.com/", "4000 Warner Boulevard Burbank", "USA");
        Sjanger sjanger1 = new Sjanger(1, "Crime");
        Bruker b1 = new Bruker("CoffeeDrinker69");
        Episode e1 = new Episode(1, "Cool episode");
        List<Skuespiller> skuespillere = new ArrayList<>();
        List<Person> people = new ArrayList<>();
        List<Musikk> musikk = new ArrayList<>();
        List<Selskap> selskaper = new ArrayList<>();
        List<Sjanger> sjangre = new ArrayList<>();
        List<Bruker> brukere = new ArrayList<>();
        List<Episode> episoder = new ArrayList<>();
        skuespillere.add(s1);
        people.add(s1);
        musikk.add(m1);
        selskaper.add(selskap1);
        sjangre.add(sjanger1);
        brukere.add(b1);
        episoder.add(e1);

        /*
        test.regFilm(4, "Shawshank Redemption", true, true, true, true, 142, 1995, "1995-06-01", "En dømt forbryter prøver å forlate fengselet",
                skuespillere, people, people, sjangre, musikk, selskaper, episoder); */

        AnmeldelseCtrl testing = new AnmeldelseCtrl();

        Bruker brukertesting = new Bruker("xX_lillepia69_Xx");

        testing.registrerAnmeldelse(brukertesting, 4, "This movie fucking sucks", 9, 2);





        /*
        testctrl.getRoles("Morgan Freeman");
        testctrl.getRoles("Leonardo Dicaprio");
        testctrl.getRoles("Idris Elba");
        testctrl.getMovies("Morgan Freeman");

         */
    }

}