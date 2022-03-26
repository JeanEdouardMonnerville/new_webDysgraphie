/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petut.webDysgraphie.model;

import java.util.ArrayList;

/**
 *
 * @author jemon
 */
public class Jerks {

    private ArrayList<Double> jerk;
    private ArrayList<Integer> temps;

    public Jerks(ArrayList<Double> jerk, ArrayList<Integer> temps) {
        this.jerk = jerk;
        this.temps = temps;
    }

    public ArrayList<Double> getJerk() {
        return jerk;
    }

    public ArrayList<Integer> getTemps() {
        return temps;
    }

    public void setJerk(ArrayList<Double> jerk) {
        this.jerk = jerk;
    }

    public void setTemps(ArrayList<Integer> temps) {
        this.temps = temps;
    }

    @Override
    public String toString() {
        return "GraphJerk{" + "jerk=" + jerk + ", temps=" + temps + '}';
    }
}
