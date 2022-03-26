
package petut.webDysgraphie.model;

import java.util.logging.Logger;
import petut.webDysgraphie.model.enumeration.MaterielType;

public class Materiel {
    private MaterielType materielType;

    public Materiel(MaterielType materielType) {
        this.materielType = materielType;
    }

    public MaterielType getMaterielType() {
        return materielType;
    }

    public void setMaterielType(MaterielType materielType) {
        this.materielType = materielType;
    }

    @Override
    public String toString() {
        return "Materiel{" + "materielType=" + materielType + '}';
    }
    
    
}
