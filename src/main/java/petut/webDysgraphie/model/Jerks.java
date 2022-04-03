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
public class Jerks {
 private ArrayList<Jerk> jerks;

    public Jerks() {
    }

    public Jerks(ArrayList<Jerk> jerks) {
        this.jerks = jerks;
    }

    public ArrayList<Jerk> getJerks() {
        return jerks;
    }

    public void setJerks(ArrayList<Jerk> jerks) {
        this.jerks = jerks;
    }

    @Override
    public String toString() {
                String string="{ liste_x : [";
        for(Jerk j:jerks){
            string =string + j.getX() + "," ;
        }
        string=string+"], liste_y : [";
        for(Jerk j:jerks){
            string =string + j.getY()+ "," ;
        }
        string=string+"]}";
        return string;
    }

    public ArrayList<Double> createListeX() {
        ArrayList<Double> result = new ArrayList<Double>();

        for (int i = 0; i < jerks.size(); i++) {
            result.add((double) jerks.get(i).getX());
        }
        return result;
    }

    public ArrayList<Double> createListeY() {
        ArrayList<Double> result = new ArrayList<Double>();

        for (int i = 0; i < jerks.size(); i++) {
            result.add((double) jerks.get(i).getY());
        }
        return result;
    }
 
}
