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
    private double valeurPatient;

    public GraphResponseFormat() {
    }

    public GraphResponseFormat(ArrayList<Double> liste_x, ArrayList<Double> liste_y) {
        this.liste_x = liste_x;
        this.liste_y = liste_y;
    }

    public GraphResponseFormat(ArrayList<Double> liste_x, ArrayList<Double> liste_y, double valeurPatient) {
        this.liste_x = liste_x;
        this.liste_y = liste_y;
        this.valeurPatient = valeurPatient;
    }

    public void setValeurPatient(double valeurPatient) {
        this.valeurPatient = valeurPatient;
    }

    public double getValeurPatient() {
        return valeurPatient;
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
