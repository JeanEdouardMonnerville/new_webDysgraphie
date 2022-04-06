package petut.webDysgraphie.api.responseFormat;

import java.util.ArrayList;

public class GraphResponseImportExcel {

    private ArrayList<Double> liste_vitesse_x;
    private ArrayList<Double> liste_vitesse_y;

    private ArrayList<Double> liste_acceleration_x;
    private ArrayList<Double> liste_acceleration_y;

    private ArrayList<Double> liste_jerk_x;
    private ArrayList<Double> liste_jerk_y;

    private ArrayList<Double> liste_Gauss_x;
    private ArrayList<Double> liste_Gauss_y;

    private double patientValue;


    public GraphResponseImportExcel(ArrayList<Double> liste_vitesse_x, ArrayList<Double> liste_vitesse_y, ArrayList<Double> liste_acceleration_x, ArrayList<Double> liste_acceleration_y, ArrayList<Double> liste_jerk_x, ArrayList<Double> liste_jerk_y, ArrayList<Double> liste_Gauss_x, ArrayList<Double> liste_Gauss_y, double patientValue) {
        this.liste_vitesse_x = liste_vitesse_x;
        this.liste_vitesse_y = liste_vitesse_y;
        this.liste_acceleration_x = liste_acceleration_x;
        this.liste_acceleration_y = liste_acceleration_y;
        this.liste_jerk_x = liste_jerk_x;
        this.liste_jerk_y = liste_jerk_y;
        this.liste_Gauss_x = liste_Gauss_x;
        this.liste_Gauss_y = liste_Gauss_y;
        this.patientValue = patientValue;
    }

    public GraphResponseImportExcel() {
        this.liste_vitesse_x = new ArrayList<>();
        this.liste_vitesse_y = new ArrayList<>();
        this.liste_acceleration_x = new ArrayList<>();
        this.liste_acceleration_y = new ArrayList<>();
        this.liste_jerk_x = new ArrayList<>();
        this.liste_jerk_y = new ArrayList<>();
        this.liste_Gauss_x = new ArrayList<>();
        this.liste_Gauss_y = new ArrayList<>();
        this.patientValue = 0;
    }

    public ArrayList<Double> getListe_vitesse_x() {
        return liste_vitesse_x;
    }

    public ArrayList<Double> getListe_vitesse_y() {
        return liste_vitesse_y;
    }

    public ArrayList<Double> getListe_acceleration_x() {
        return liste_acceleration_x;
    }

    public ArrayList<Double> getListe_acceleration_y() {
        return liste_acceleration_y;
    }

    public ArrayList<Double> getListe_jerk_x() {
        return liste_jerk_x;
    }

    public ArrayList<Double> getListe_jerk_y() {
        return liste_jerk_y;
    }

    public ArrayList<Double> getListe_Gauss_x() {
        return liste_Gauss_x;
    }

    public ArrayList<Double> getListe_Gauss_y() {
        return liste_Gauss_y;
    }

    public double getPatientValue() {
        return patientValue;
    }

    public void setListe_vitesse_x(ArrayList<Double> liste_vitesse_x) {
        this.liste_vitesse_x = liste_vitesse_x;
    }

    public void setListe_vitesse_y(ArrayList<Double> liste_vitesse_y) {
        this.liste_vitesse_y = liste_vitesse_y;
    }

    public void setListe_acceleration_x(ArrayList<Double> liste_acceleration_x) {
        this.liste_acceleration_x = liste_acceleration_x;
    }

    public void setListe_acceleration_y(ArrayList<Double> liste_acceleration_y) {
        this.liste_acceleration_y = liste_acceleration_y;
    }

    public void setListe_jerk_x(ArrayList<Double> liste_jerk_x) {
        this.liste_jerk_x = liste_jerk_x;
    }

    public void setListe_jerk_y(ArrayList<Double> liste_jerk_y) {
        this.liste_jerk_y = liste_jerk_y;
    }

    public void setListe_Gauss_x(ArrayList<Double> liste_Gauss_x) {
        this.liste_Gauss_x = liste_Gauss_x;
    }

    public void setListe_Gauss_y(ArrayList<Double> liste_Gauss_y) {
        this.liste_Gauss_y = liste_Gauss_y;
    }

    public void setPatientValue(double patientValue) {
        this.patientValue = patientValue;
    }
    
}
