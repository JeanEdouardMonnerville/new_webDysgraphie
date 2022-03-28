package petut.webDysgraphie.dataToolkit;

import java.util.ArrayList;

/**
 *
 * @author jemon
 */
public class MathTools {

    public MathTools() {
    }

    public ArrayList<Double> normalDensityY(ArrayList<Double> listeX) {
        ArrayList<Double> result = new ArrayList<>();
        for (double x : listeX) {
            result.add(normalDensity(x));
        }
        return result;
    }

    public ArrayList<Double> normalDensityX(int rangeDebut, int rangeFin) {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = rangeDebut; i < rangeFin; i++) {
            result.add( (double) i/100);
        }
        return result;
    }

    public double normalDensity(double x) {
        return 1 / Math.sqrt(2 * Math.PI) * Math.exp(-Math.pow(x, 2) / 2);
    }
}
