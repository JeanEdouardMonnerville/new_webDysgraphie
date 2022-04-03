/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petut.webDysgraphie.api.responseFormat;

import java.util.ArrayList;

/**
 *
 * @author jemon
 */
public class GraphResponseFormat {
    private ArrayList<Double> liste_x;
    private ArrayList<Double> liste_y;

    public GraphResponseFormat() {
    }

    public GraphResponseFormat(ArrayList<Double> liste_x, ArrayList<Double> liste_y) {
        this.liste_x = liste_x;
        this.liste_y = liste_y;
    }





    public ArrayList<Double> getListe_x() {
        return liste_x;
    }

    public ArrayList<Double> getListe_y() {
        return liste_y;
    }

    public void setListe_x(ArrayList<Double> liste_x) {
        ArrayList<Double> result = new ArrayList<>();
        
        //On s'assure que la courbe commence à 0
        double firstValue=liste_x.get(0);
        for(int i=0; i<liste_x.size();i++){
            result.add(liste_x.get(i)-firstValue);
        }
        
        this.liste_x = result;
    }

    public void setListe_y(ArrayList<Double> liste_y) {
        this.liste_y = liste_y;
    }
    
}
