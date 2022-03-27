
package petut.webDysgraphie.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *Class qui permet de récupérer les résultat de vitesse.
 * @author jemon
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Vitesses {
    private ArrayList<Vitesse> vitesses;

    public Vitesses(ArrayList<Vitesse> vitesses) {
        this.vitesses = vitesses;
    }

    public Vitesses() {
    }

    public ArrayList<Vitesse> getVitesses() {
        return vitesses;
    }

    public void setVitesses(ArrayList<Vitesse> vitesses) {
        this.vitesses = vitesses;
    }

    @Override
    public String toString() {
        String string="{ liste_x : [";
        for(Vitesse v:vitesses){
            string =string + v.getX() + "," ;
        }
        string=string+"], liste_y : [";
        for(Vitesse v:vitesses){
            string =string + v.getY()+ "," ;
        }
        string=string+"]}";
        return string;
    }
    
    

}
