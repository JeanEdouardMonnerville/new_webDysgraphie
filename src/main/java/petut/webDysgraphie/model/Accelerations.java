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
public class Accelerations {
    private ArrayList<Double> acceleration;
    private ArrayList<Integer> temps;

    public Accelerations(ArrayList<Double> acceleration, ArrayList<Integer> temps) {
        this.acceleration = acceleration;
        this.temps = temps;
    }

    public ArrayList<Double> getAcceleration() {
        return acceleration;
    }

    public ArrayList<Integer> getTemps() {
        return temps;
    }

    public void setAcceleration(ArrayList<Double> acceleration) {
        this.acceleration = acceleration;
    }

    public void setTemps(ArrayList<Integer> temps) {
        this.temps = temps;
    }

    @Override
    public String toString() {
        return "GraphAcceleration{" + "acceleration=" + acceleration + ", temps=" + temps + '}';
    }
}
