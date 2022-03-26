
package petut.webDysgraphie.model;


public class Autorisation {
    private boolean accord;

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
