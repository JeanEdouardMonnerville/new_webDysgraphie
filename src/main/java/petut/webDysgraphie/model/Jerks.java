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
 
}
