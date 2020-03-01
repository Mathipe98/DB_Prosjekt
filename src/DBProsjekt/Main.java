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
        /*
        testctrl.connect();
        testctrl.regPerson("Daniel Day Lewis", 1990, "Prompeland");
        */
        testctrl.getRoles("Morgan Freeman");
        testctrl.getRoles("Leonardo Dicaprio");
        testctrl.getRoles("Idris Elba");

    }

}