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
public class Acceleration {
        private long x;
    private double y;

    public Acceleration() {
    }

    public Acceleration(long x, double y) {
        this.x = x;
        this.y = y;
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
        return "Acceleration{" + "x=" + x + ", y=" + y + '}';
    }
}
