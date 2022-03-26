
package petut.webDysgraphie.model;

import java.util.ArrayList;

/**
 *Class qui permet de récupérer les résultat de vitesse.
 * @author jemon
 */
public class Vitesses {
    private ArrayList<Vitesse> vitesses;

    public Vitesses(ArrayList<Vitesse> vitesses) {
        this.vitesses = vitesses;
    }

    public ArrayList<Vitesse> getVitesses() {
        return vitesses;
    }

    public void setVitesses(ArrayList<Vitesse> vitesses) {
        this.vitesses = vitesses;
    }
    

}
