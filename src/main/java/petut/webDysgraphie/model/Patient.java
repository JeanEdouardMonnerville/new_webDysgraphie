
package petut.webDysgraphie.model;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Patient {
    private String nom;
    private String prenom;
    private String sexe;
    private int age; 
    private String classe;
    private int anonymat;
    private LocalDate dateExamen;

    public Patient(String nom, String prenom, String sexe, int age, String classe, int anonymat,LocalDate dateExamen) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.classe = classe;
        this.anonymat = anonymat;
        this.dateExamen=dateExamen;
    }

    public Patient() {
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public int getAge() {
        return age;
    }

    public String getClasse() {
        return classe;
    }

    public int getAnonymat() {
        return anonymat;
    }

    public LocalDate getDateExamen() {
        return dateExamen;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setAnonymat(int anonymat) {
        this.anonymat = anonymat;
    }

    public void setDateExamen(LocalDate dateExamen) {
        this.dateExamen = dateExamen;
    }

    @Override
    public String toString() {
        return "Patient{" + "nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", age=" + age + ", classe=" + classe + ", anonymat=" + anonymat + '}';
    }
    
}
