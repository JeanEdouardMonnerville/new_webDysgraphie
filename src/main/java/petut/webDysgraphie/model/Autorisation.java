
package petut.webDysgraphie.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Autorisation {
    private boolean accord;

    public Autorisation() {
    }

    public Autorisation(boolean autorisation) {
        this.accord = autorisation;
    }

    public boolean isAccord() {
        return accord;
    }

    public void setAccord(boolean accord) {
        this.accord = accord;
    }
    
    
    
}
