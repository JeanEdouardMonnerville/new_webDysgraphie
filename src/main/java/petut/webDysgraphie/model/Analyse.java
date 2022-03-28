
package petut.webDysgraphie.model;

import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import petut.webDysgraphie.model.enumeration.TypeAnalyse;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "token",
    "typeAnalyse",
    "patient",
    "materiel",
    "autorisation",
    "tableau"
})
@XmlRootElement(name = "analyse")
public class Analyse {
    private TypeAnalyse typeAnalyse;
    private String token; 
    private Tableau tableau;
    private Patient patient;
    private Materiel materiel;
    private Autorisation autorisation;

    public Analyse(TypeAnalyse typeAnalyse, String token, Tableau tableau, Patient patient, Materiel materiel, Autorisation autorisation) {
        this.typeAnalyse = typeAnalyse;
        this.token = token;
        this.tableau = tableau;
        this.patient = patient;
        this.materiel = materiel;
        this.autorisation = autorisation;
    }

    public Analyse() {
    }

    public Analyse(TypeAnalyse typeAnalyse, String token) {
        this.typeAnalyse = typeAnalyse;
        this.token = token;
    }





    

    public TypeAnalyse getTypeAnalyse() {
        return typeAnalyse;
    }

    public String getToken() {
        return token;
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

    public void setTypeAnalyse(TypeAnalyse typeAnalyse) {
        this.typeAnalyse = typeAnalyse;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTableau(Tableau tableau) {
        this.tableau = tableau;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public void setAutorisation(Autorisation autorisation) {
        this.autorisation = autorisation;
    }

    @Override
    public String toString() {
        return "Analyse{" + "typeAnalyse=" + typeAnalyse + ", token=" + token + ", tableau=" + tableau + ", patient=" + patient + ", materiel=" + materiel + ", autorisation=" + autorisation + '}';
    }
    
    
    
    
    
}
