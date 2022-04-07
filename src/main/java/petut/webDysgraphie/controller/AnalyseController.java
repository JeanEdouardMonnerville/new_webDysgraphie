package petut.webDysgraphie.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import petut.webDysgraphie.api.responseFormat.GraphResponseFormat;
import petut.webDysgraphie.api.responseFormat.GraphResponseImportExcel;
import petut.webDysgraphie.api.responseFormat.GraphVitesseGaussResponseFormat;
import petut.webDysgraphie.dataToolkit.DataTools;
import petut.webDysgraphie.dataToolkit.MathTools;
import petut.webDysgraphie.model.Analyse;
import petut.webDysgraphie.model.Materiel;
import petut.webDysgraphie.model.Patient;
import petut.webDysgraphie.model.Point;
import petut.webDysgraphie.model.Points;
import petut.webDysgraphie.model.Tableau;
import petut.webDysgraphie.model.enumeration.TypeAnalyse;

/**
 * Classe qui fait le lien entre les données envoyer par le client et le fichier
 * de sauvegarde des données XML.
 *
 * @author jemon
 */
@Component
public class AnalyseController {

    private final DataTools dataTools = new DataTools();
    private final MathTools mathTools = new MathTools();

    public AnalyseController() {
    }

    public String getFileName(String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);

        String fileName = analyse.getPatient().getNom() + "_"
                + analyse.getPatient().getPrenom() + "_"
                + analyse.getPatient().getClasse() + "_"
                + analyse.getPatient().getDateExamen().toString();
        return fileName;
    }

    /**
     * Créer un document excel dans le dossier Téléchargement du client
     *
     * @param token d'accès au données
     */
    public ByteArrayInputStream downloadResultat(String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        String fileName = getFileName(token);
        return analyse.getTableau().DownloadExcel(fileName, "sheet1");
    }

    public Analyse getAnalyse(String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        return analyse;
    }

    /**
     * Fonction qui fait débuter un examen, cette examen possède un attribut
     * analyse type au cas où dans le future on voudrait faire une analyse
     * différente en fonction de sa valeur.
     *
     * @param analyseType analyseType de l'examen
     * @param token token d'accès aux données
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public Analyse analyseInit(TypeAnalyse analyseType, String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        analyse.setTypeAnalyse(analyseType);

        dataTools.saveAnalyseToXml(analyse, analyse.getToken());
        return analyse;
    }

    /**
     * Fonction qui permet de récupérer les informations du patient.
     *
     * @param patient jeune qui passe l'exemen
     * @param token token d'accès aux données
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public Analyse ajoutPatient(Patient patient, String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        analyse.setPatient(patient);
        dataTools.saveAnalyseToXml(analyse, analyse.getToken());
        return analyse;
    }

    /**
     * Fonction qui note quel matériel est utilisé pendant l'examen
     *
     * @param materiel matériel utilisé par le client
     * @param token token d'accès aux données
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public Analyse ajoutMateriel(Materiel materiel, String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        analyse.setMateriel(materiel);
        dataTools.saveAnalyseToXml(analyse, analyse.getToken());
        return analyse;
    }

    /**
     *
     * @param autorisation autorisation général d'utilisation de l'application
     * @param token token d'accès aux données
     * @return token d'accès qui est aussi le nom du fichier de sauvegarde XML
     */
    public Analyse ajoutAutorisation(boolean autorisation, String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        analyse.getAutorisation().setAccord(autorisation);
        dataTools.saveAnalyseToXml(analyse, analyse.getToken());
        return analyse;
    }

    /**
     *
     * @param listePoint liste des points de l'examen
     * @param token d'accès au fichier
     * @return
     */
    public Analyse ajoutResultat(ArrayList<Point> listePoint, String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        Points points = new Points(listePoint);
        Tableau tableau = new Tableau(points);
        analyse.setTableau(tableau);
        dataTools.saveAnalyseToXml(analyse, analyse.getToken());

        return analyse;
    }

    /**
     *
     * @param token
     * @return les données de vitesse de l'examen
     */
    public GraphResponseFormat getResultatVitesse(String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);
        GraphResponseFormat result = new GraphResponseFormat();

        result.setListe_x(analyse.getTableau().getVitesses().createListeX());
        result.setListe_y(analyse.getTableau().getVitesses().createListeY());

        return result;
    }

    public GraphVitesseGaussResponseFormat getResultatVitesseInscription(String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);

        GraphVitesseGaussResponseFormat result = new GraphVitesseGaussResponseFormat();
        result.setListe_Gauss_x(mathTools.normalDensityX(-100, 100));
        result.setListe_Gauss_y(mathTools.normalDensityY(result.getListe_Gauss_x()));

        double valeurPatient = mathTools.normalDensity(analyse.getTableau().createMoyenneVitesse()
                / analyse.getTableau().createEcartTypeVitesse());

        result.setListe_x(mathTools.createPatientResultList_x(5, valeurPatient));
        result.setListe_y(mathTools.createPatientResultList_y(5));

        return result;
    }

    /**
     *
     * @param token
     * @return les données d'acceleration de l'examen
     */
    public GraphResponseFormat getResultatAcceleration(String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);

        GraphResponseFormat result = new GraphResponseFormat();

        result.setListe_x(analyse.getTableau().getAccelerations().createListeX());
        result.setListe_y(analyse.getTableau().getAccelerations().createListeY());

        return result;
    }

    /**
     *
     * @param token
     * @return les données de jerk de l'examen
     */
    public GraphResponseFormat getResultatJerk(String token) {
        Analyse analyse = dataTools.readAnalyseFromXml(token);

        GraphResponseFormat result = new GraphResponseFormat();

        result.setListe_x(analyse.getTableau().getJerks().createListeX());
        result.setListe_y(analyse.getTableau().getJerks().createListeY());

        return result;
    }

    /**
     * Met fin à la session en suprimant le dossier xml
     *
     * @param token
     */
    public void finDeSession(String token) {
        dataTools.deleteThisFile(token);
    }

    public GraphResponseImportExcel importexcel(MultipartFile excelfile) {
        GraphResponseImportExcel result = new GraphResponseImportExcel();
        try {
            int i = 0;

            XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());

            XSSFSheet worksheet = workbook.getSheetAt(0);

            while (i <= worksheet.getLastRowNum()) {

                XSSFRow row = worksheet.getRow(i++);

                result.getListe_vitesse_x().add(row.getCell(5).getNumericCellValue());
                result.getListe_acceleration_x().add(row.getCell(5).getNumericCellValue());
                result.getListe_jerk_x().add(row.getCell(5).getNumericCellValue());

                if (i > 1) {
                    result.getListe_vitesse_y().add(row.getCell(9).getNumericCellValue());
                }
                if (i > 2) {
                    result.getListe_acceleration_y().add(row.getCell(10).getNumericCellValue());
                }
                if (i > 3) {
                    result.getListe_jerk_y().add(row.getCell(11).getNumericCellValue());
                }

            }
            XSSFRow row = worksheet.getRow(1);
            result.setListe_Gauss_x(mathTools.normalDensityX(-100, 100));
            result.setListe_Gauss_y(mathTools.normalDensityY(result.getListe_Gauss_x()));

            double valeurPatient = mathTools.normalDensity(row.getCell(13).getNumericCellValue()
                    / row.getCell(14).getNumericCellValue());

            result.setPatientValue(valeurPatient);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
