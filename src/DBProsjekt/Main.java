package DBProsjekt;

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

        PersonCtrl testctrl = new PersonCtrl();
        FilmCtrl test = new FilmCtrl();


        testctrl.getRoles("Morgan Freeman");
        testctrl.getRoles("Leonardo Dicaprio");
        testctrl.getRoles("Idris Elba");
        testctrl.getMovies("Morgan Freeman");

    }

}