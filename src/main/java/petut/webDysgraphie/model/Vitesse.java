/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petut.webDysgraphie.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jemon
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Vitesse {
    private long x;
    private double y;

    public Vitesse(long x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vitesse() {
    }

    public long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vitesse{" + "x=" + x + ", y=" + y + '}';
    }
    
}
