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
public class GraphVitesseGaussResponseFormat {
    private ArrayList<Double> liste_Gauss_x;
    private ArrayList<Double> liste_Gauss_y;
    private ArrayList<Double> liste_x;
    private ArrayList<Double> liste_y;
    public GraphVitesseGaussResponseFormat() {
    }

    public GraphVitesseGaussResponseFormat(ArrayList<Double> liste_Gauss_x, ArrayList<Double> liste_Gauss_y, ArrayList<Double> liste_x, ArrayList<Double> liste_y) {
        this.liste_Gauss_x = liste_Gauss_x;
        this.liste_Gauss_y = liste_Gauss_y;
        this.liste_x = liste_x;
        this.liste_y = liste_y;
    }

    public ArrayList<Double> getListe_Gauss_x() {
        return liste_Gauss_x;
    }

    public ArrayList<Double> getListe_Gauss_y() {
        return liste_Gauss_y;
    }

    public void setListe_Gauss_x(ArrayList<Double> liste_Gauss_x) {
        this.liste_Gauss_x = liste_Gauss_x;
    }

    public void setListe_Gauss_y(ArrayList<Double> liste_Gauss_y) {
        this.liste_Gauss_y = liste_Gauss_y;
    }







    public ArrayList<Double> getListe_x() {
        return liste_x;
    }

    public ArrayList<Double> getListe_y() {
        return liste_y;
    }

    public void setListe_x(ArrayList<Double> liste_x) {
        this.liste_x = liste_x;
    }

    public void setListe_y(ArrayList<Double> liste_y) {
        this.liste_y = liste_y;
    }
    
}
