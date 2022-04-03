
package petut.webDysgraphie.model;

import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import petut.webDysgraphie.model.enumeration.MaterielType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Materiel {
    private MaterielType materielType;

    public Materiel() {
    }

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
