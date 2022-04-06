package petut.webDysgraphie.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import petut.webDysgraphie.api.responseFormat.GraphResponseFormat;
import petut.webDysgraphie.api.responseFormat.GraphResponseImportExcel;
import petut.webDysgraphie.api.responseFormat.GraphVitesseGaussResponseFormat;
import petut.webDysgraphie.controller.AnalyseController;
import petut.webDysgraphie.model.Analyse;
import petut.webDysgraphie.model.Materiel;
import petut.webDysgraphie.model.Patient;
import petut.webDysgraphie.model.Point;
import petut.webDysgraphie.model.enumeration.TypeAnalyse;

/**
 *
 * @author jemon Class controller allant de la création de l'examen jusqu'à sa
 * suppression.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private ObjectMapper objectMapper = new ObjectMapper();
    private AnalyseController analyseController;

    public RestController(AnalyseController analyseController) {
        this.analyseController = analyseController;
    }

    /**
     * Lien: GET /analyse
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @return l'objet analyse en entier format json
     */
    @GetMapping(path = "/analyse", produces = "application/json")
    public Analyse getAnalyse(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getAnalyse(token);
    }

    /**
     * Lien : GET /resultat/vitesse/inscription
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @return Renvoie un objet au format json de type
     * {liste_x:[],liste_y:[],valeurPatient:number}. Il permet de tracer la
     * courbe de Gauss et la la verticale correspondant à la valuer patient.
     */
    @GetMapping(path = "/resultat/vitesse/inscription", produces = "application/json")
    public GraphVitesseGaussResponseFormat getResultatVitesseInscription(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getResultatVitesseInscription(token);
    }

    /**
     * Lien : GET /resultat/vitesse
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @return Renvoie un objet au format json de type {liste_x:[],liste_y:[]}.
     * Il permet de tracer la courbe V(t).
     */
    @GetMapping(path = "/resultat/vitesse", produces = "application/json")
    public GraphResponseFormat getResultatVitesse(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getResultatVitesse(token);
    }

    /**
     * Lien:GET /resultat/acceleration
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @return Renvoie un ibjet au format json de type
     * {liste_x:[],liste_y:[],valeurPatient:number}. Il permet de tracer la
     * courbe A(t).
     */
    @GetMapping(path = "/resultat/acceleration", produces = "application/json")
    public GraphResponseFormat getResultatAcceleration(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getResultatAcceleration(token);
    }

    /**
     * Lien: GET /resultat/jerk
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @return Renvoie un ibjet au format json de type
     * {liste_x:[],liste_y:[],valeurPatient:number}. Il permet de tracer la
     * courbe J(t).
     */
    @GetMapping(path = "/resultat/jerk", produces = "application/json")
    public GraphResponseFormat getResultatJerk(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getResultatJerk(token);
    }

    /**
     * Lien: GET /resultat/download
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @param response ////
     */
    @GetMapping(path = "/resultat/download/{token}")
    public void downloadResult(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathVariable String token) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + analyseController.getFileName(token) + ".xls");
        ByteArrayInputStream stream = analyseController.downloadResultat(token);
        IOUtils.copy(stream, response.getOutputStream());
    }

    /**
     * Lien: POST /analyse
     *
     * @param typeAnalyse Type d'examen passé. Il faut envoyer au serveur
     * {typeAnalyse:BHK,BHKADO ou CINEMATIQUE}.
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @return
     */
    @PostMapping(path = "/analyse", consumes = "application/json", produces = "application/json")
    public Analyse postAnalyse(@RequestBody String typeAnalyse, @Context HttpServletRequest request) {
        String token = request.getHeader("token");
        TypeAnalyse typeAnalyse_p = TypeAnalyse.BHK;

        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(typeAnalyse);
            typeAnalyse = jsonNode.path("typeAnalyse").asText();
            if (typeAnalyse.equals("bhk") || typeAnalyse.equals("BHK")) {
                typeAnalyse_p = TypeAnalyse.BHK;
            }
            if (typeAnalyse.equals("bhkado") || typeAnalyse.equals("BHKADO") || typeAnalyse.equals("bhk ado") || typeAnalyse.equals("BHK ADO")) {
                typeAnalyse_p = TypeAnalyse.BHKADO;
            }
            if (typeAnalyse.equals("cinematique") || typeAnalyse.equals("CINEMATIQUE")) {
                typeAnalyse_p = TypeAnalyse.CINEMATIQUE;
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Analyse response = analyseController.analyseInit(typeAnalyse_p, token);
        return response;
    }

    /**
     * Lien : POST /materiel
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @param materiel Il faut envoyer au serveur materiel :
     * {materielType:TABLETTEGRAPHIQUE,IPAD ou ECRANTACTILE}
     * @return L'objet analyse à l'instant t.
     */
    @PostMapping(path = "/materiel", consumes = "application/json", produces = "application/json")
    public Analyse postMateriel(@Context HttpServletRequest request, @RequestBody Materiel materiel) {
        String token = request.getHeader("token");
        return analyseController.ajoutMateriel(materiel, token);
    }

    /**
     * Lien: POST /autorisation
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @param autorisation Il faut envoyer au serveur {autorisation:true}.
     * @return L'objet analyse à l'instant t.
     */
    @PostMapping(path = "/autorisation", consumes = "application/json", produces = "application/json")
    public Analyse postAutorisation(@Context HttpServletRequest request, @RequestBody String autorisation) {
        String token = request.getHeader("token");
        boolean accord = false;
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(autorisation);
            accord = jsonNode.path("autorisation").asBoolean();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return analyseController.ajoutAutorisation(accord, token);
    }

    /**
     * Lien : POST /patient
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @param patient Il faut envoyer un objet Patient.
     * @return L'objet analyse à l'instant t.
     */
    @PostMapping(path = "/patient", consumes = "application/json", produces = "application/json")
    public Analyse postPatient(@Context HttpServletRequest request, @RequestBody Patient patient) {
        String token = request.getHeader("token");
        return analyseController.ajoutPatient(patient, token);
    }

    /**
     * Lien : POST /ecriture
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     * @param body Il faut juste envoyer au serveur une liste comme
     * {liste_point: {x:1,y:2,...},...}
     * @return L'objet analyse à l'instant t.
     */
    @PostMapping(path = "/ecriture", consumes = "application/json", produces = "application/json")
    public Analyse postEcriture(@Context HttpServletRequest request, @RequestBody() String body) {
        Analyse result = new Analyse();

        String token = request.getHeader("token");
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(body);
            String bodyListePoint = jsonNode.path("liste_point").toString();
            ArrayList<Point> list_point = objectMapper.readValue(bodyListePoint, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Point.class));
            result = analyseController.ajoutResultat(list_point, token);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * Lien: DELETE /remove
     *
     * @param request Il faut dans chaque requête envoyer un token d'accès dans
     * le header comme : "token":"HGSNSLQUHAB"
     */
    @DeleteMapping(path = "/remove")
    public void removeExamen(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        analyseController.finDeSession(token);
    }
    
    @PostMapping(value="resultat/import")
    public GraphResponseImportExcel imports(@RequestParam("excelFile") MultipartFile excelfile) {
       return analyseController.importexcel(excelfile);
    }
}
