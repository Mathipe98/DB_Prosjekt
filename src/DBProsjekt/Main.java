package DBProsjekt;

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
        List<Skuespiller> skuespillere = new ArrayList<>();
        List<Person> people = new ArrayList<>();
        skuespillere.add(s1);
        people.add(s1);


        test.regFilm(4, "Shawshank Redemption", true, true, true, true, 142, 1995, "1995-06-01", "En dømt forbryter prøver å forlate fengselet",
                skuespillere, people, people, null, null, null, null, null);




        /*
        testctrl.getRoles("Morgan Freeman");
        testctrl.getRoles("Leonardo Dicaprio");
        testctrl.getRoles("Idris Elba");
        testctrl.getMovies("Morgan Freeman");

         */
    }

}