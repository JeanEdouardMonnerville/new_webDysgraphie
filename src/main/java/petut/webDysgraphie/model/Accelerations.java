/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petut.webDysgraphie.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jemon
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Accelerations {

    ArrayList<Acceleration> accelerations;

    public Accelerations() {
    }

    public Accelerations(ArrayList<Acceleration> accelerations) {
        this.accelerations = accelerations;
    }

    public ArrayList<Acceleration> getAccelerations() {
        return accelerations;
    }

    public void setAccelerations(ArrayList<Acceleration> accelerations) {
        this.accelerations = accelerations;
    }

    @Override
    public String toString() {
        String string = "{ liste_x : [";
        for (Acceleration a : accelerations) {
            string = string + a.getX() + ",";
        }
        string = string + "], liste_y : [";
        for (Acceleration a : accelerations) {
            string = string + a.getY() + ",";
        }
        string = string + "]}";
        return string;
    }

    public ArrayList<Double> createListeX() {
        ArrayList<Double> result = new ArrayList<Double>();

        for (int i = 0; i < accelerations.size(); i++) {
            result.add((double) accelerations.get(i).getX());
        }
        return result;
    }

    public ArrayList<Double> createListeY() {
        ArrayList<Double> result = new ArrayList<Double>();

        for (int i = 0; i < accelerations.size(); i++) {
            result.add((double) accelerations.get(i).getY());
        }
        return result;
    }

}
