package DBProsjekt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

        Skuespiller s1 = new Skuespiller();
        Musikk m1 = new Musikk();
        Selskap selskap1 = new Selskap();
        Sjanger sjanger1 = new Sjanger();
        Bruker b1 = new Bruker();
        Episode e1 = new Episode();
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


        test.regFilm(
                skuespillere, people, people, sjangre, musikk, selskaper, episoder);





        /*
        testctrl.getRoles("Morgan Freeman");
        testctrl.getRoles("Leonardo Dicaprio");
        testctrl.getRoles("Idris Elba");
        testctrl.getMovies("Morgan Freeman");

         */
    }

}