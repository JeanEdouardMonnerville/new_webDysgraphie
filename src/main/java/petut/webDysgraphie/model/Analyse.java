
package petut.webDysgraphie.model;

import java.util.UUID;
import petut.webDysgraphie.model.enumeration.TypeAnalyse;


public class Analyse {
    private TypeAnalyse typeAnalyse;
    private String Token; 
    private Tableau tableau;
    private Patient patient;
    private Materiel materiel;
    private Autorisation autorisation;

    public Analyse(TypeAnalyse typeAnalyse,  Tableau tableau, Patient patient, Materiel materiel, Autorisation autorisation) {
        this.typeAnalyse = typeAnalyse;
        this.Token = UUID.randomUUID().toString();
        this.tableau = tableau;
        this.patient = patient;
        this.materiel = materiel;
        this.autorisation = autorisation;
    }

    public TypeAnalyse getTypeAnalyse() {
        return typeAnalyse;
    }

    public String getToken() {
        return Token;
    }

    public Tableau getTableau() {
        return tableau;
    }

    public Patient getPatient() {
        return patient;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public Autorisation getAutorisation() {
        return autorisation;
    }
    
    
    
    
    
}
