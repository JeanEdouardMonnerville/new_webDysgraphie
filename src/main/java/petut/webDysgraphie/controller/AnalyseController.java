package petut.webDysgraphie.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import petut.webDysgraphie.dataToolkit.DataTools;
import petut.webDysgraphie.model.Accelerations;
import petut.webDysgraphie.model.Analyse;
import petut.webDysgraphie.model.Jerks;
import petut.webDysgraphie.model.Materiel;
import petut.webDysgraphie.model.Vitesses;
import petut.webDysgraphie.model.Patient;
import petut.webDysgraphie.model.Point;
import petut.webDysgraphie.model.Points;
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
    public void downloadResultat(String token) {
        Analyse analyse=dataTools.readAnalyseFromXml(token);
        
        String fileName=analyse.getPatient().getNom()+"_"
                +analyse.getPatient().getPrenom()+"_"+
                analyse.getPatient().getClasse()+"_"+
                analyse.getPatient().getDateExamen().toString();
        analyse.getTableau().DownloadExcel(fileName, "sheet1");
    }
    
    public Analyse getAnalyse(String token){
         Analyse analyse= dataTools.readAnalyseFromXml(token);
         return analyse;
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
    public String ajoutPatient(Patient patient,String token) {
        Analyse analyse=dataTools.readAnalyseFromXml(token);
        analyse.setPatient(patient);
        dataTools.saveAnalyseToXml(analyse, token);
        token=analyse.getToken();
        return token;
    }

    /**
     * Fonction qui note quel matériel est utilisé pendant l'examen
     *
     * @param materielType matériel utilisé pour l'examen
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public String ajoutMateriel(Materiel materiel,String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        analyse.setMateriel(materiel);
        dataTools.saveAnalyseToXml(analyse, token);
        token=analyse.getToken();
        return token;
    }

    /**
     *
     * @param autorisation autorisation général d'utilisation de l'application
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public String ajoutAutorisation(boolean autorisation,String token) {
        Analyse analyse=dataTools.readAnalyseFromXml(token);
        analyse.getAutorisation().setAccord(autorisation);
        dataTools.saveAnalyseToXml(analyse, token);
        token=analyse.getToken();
        return token;
    }

    /**
     *
     * @param listePoint liste des points de l'examen
     * @param token d'accès au fichier
     * @return
     */
    public String ajoutResultat(ArrayList<Point> listePoint, String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        Points points=new Points(listePoint);
        Tableau tableau = new Tableau(points);
        analyse.setTableau(tableau);
        dataTools.saveAnalyseToXml(analyse, token);
        token=analyse.getToken();
        return token;
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
    public Accelerations getResultatAcceleration(String token){
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
