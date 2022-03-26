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
    ArrayList<Acceleration> accelerations;

    public Accelerations(ArrayList<Acceleration> accelerations) {
        this.accelerations = accelerations;
    }

    public ArrayList<Acceleration> getAccelerations() {
        return accelerations;
    }

    public void setAccelerations(ArrayList<Acceleration> accelerations) {
        this.accelerations = accelerations;
    }
    
}
