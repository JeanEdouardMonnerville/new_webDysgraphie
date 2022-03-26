package petut.webDysgraphie.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import petut.webDysgraphie.dataToolkit.DataTools;
import petut.webDysgraphie.model.Accelerations;
import petut.webDysgraphie.model.Analyse;
import petut.webDysgraphie.model.Jerks;
import petut.webDysgraphie.model.Vitesses;
import petut.webDysgraphie.model.Patient;
import petut.webDysgraphie.model.Point;
import petut.webDysgraphie.model.Tableau;
import petut.webDysgraphie.model.enumeration.MaterielType;
import petut.webDysgraphie.model.enumeration.TypeAnalyse;

/**
 * Classe qui fait le lien entre les données envoyer par le client et le fichier
 * de sauvegarde des données XML.
 *
 * @author jemon
 */
@Component
public class AnalyseController {
    private DataTools dataTools=new DataTools();
    public AnalyseController() {
    }
    
    /**
     * Créer un document excel dans le dossier Téléchargement du client
     *
     * @param tableau résultat de l'examen
     */
    public void downloadResultat(Tableau tableau) {

    }

    /**
     * Fonction qui fait débuter un examen, cette examen possède un attribut
     * analyse type au cas où dans le future on voudrait faire une analyse
     * différente en fonction de sa valeur.
     *
     * @param analyseType analyseType de l'examen
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public String analyseInit(TypeAnalyse analyseType,String token) {
        Analyse analyse= dataTools.readAnalyseFromXml(token);
        token=analyse.getToken();
        
        analyse.setTypeAnalyse(analyseType);
        dataTools.saveAnalyseToXml(analyse, token);
        return token;
    }

    /**
     * Fonction qui permet de récupérer les informations du patient.
     *
     * @param patient jeune qui passe l'exemen
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public String ajoutPatient(Patient patient) {

        return null;
    }

    /**
     * Fonction qui note quel matériel est utilisé pendant l'exemen
     *
     * @param materielType matériel utilisé pour l'examen
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public String ajoutMateriel(MaterielType materielType) {
        return null;
    }

    /**
     *
     * @param autorisation autorisation général d'utilisation de l'application
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public String ajoutAutorisation(boolean autorisation) {
        return null;
    }

    /**
     *
     * @param listePoint liste des points de l'examen
     * @param patient nom du patient conserné
     * @return
     */
    public String ajoutResultat(ArrayList<Point> listePoint, Patient patient) {
        return null;
    }
    
    /**
     * 
     * @param tableau
     * @return les données de vitesse de l'examen
     */
    public Vitesses getResultatVitesse(Tableau tableau){
        return null;
    }
    /**
     * 
     * @param tableau
     * @return les données d'acceleration de l'examen
     */
    public Accelerations getResultatAcceleration(Tableau tableau){
        return null;
    }

    /**
     * 
     * @param tableau
     * @return les données de jerk de l'examen
     */
    public Jerks getResultatJerk(Tableau tableau){
        return null;
    }   
    
    /**
     * Met fin à la session en suprimant le dossier xml
     * @param token 
     */
    public void finDeSession(String token){
    }
}
