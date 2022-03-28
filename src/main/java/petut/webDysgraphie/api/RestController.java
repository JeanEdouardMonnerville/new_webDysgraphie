package petut.webDysgraphie.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import petut.webDysgraphie.controller.AnalyseController;
import petut.webDysgraphie.model.Analyse;
import petut.webDysgraphie.model.Materiel;
import petut.webDysgraphie.model.Patient;
import petut.webDysgraphie.model.Point;
import petut.webDysgraphie.model.enumeration.TypeAnalyse;

/**
 *
 * @author jemon
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private ObjectMapper objectMapper = new ObjectMapper();
    private AnalyseController analyseController;

    public RestController(AnalyseController analyseController) {
        this.analyseController = analyseController;
    }

    @GetMapping(path = "/analyse", produces = "application/json")
    public Analyse getAnalyse(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getAnalyse(token);
    }

    @GetMapping(path = "/resultat/vitesse/inscription", produces = "application/json")
    public String getResultatVitesseInscription(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getResultatVitesseInscription(token);
    }

    @GetMapping(path = "/resultat/vitesse", produces = "application/json")
    public String getResultatVitesse(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getResultatVitesse(token);
    }

    @GetMapping(path = "/resultat/acceleration", produces = "application/json")
    public String getResultatAcceleration(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getResultatAcceleration(token);
    }

    @GetMapping(path = "/resultat/jerk", produces = "application/json")
    public String getResultatJerk(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        return analyseController.getResultatJerk(token);
    }

    @GetMapping(path = "/resultat/download", produces = "application/json")
    public String downloadResult(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        analyseController.downloadResultat(token);
        return token;
    }

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

    @PostMapping(path = "/materiel", consumes = "application/json", produces = "application/json")
    public String postMateriel(@Context HttpServletRequest request,@RequestBody Materiel materiel ) {
        String token = request.getHeader("token");
        token=analyseController.ajoutMateriel(materiel,token);
        return token;
    }

    @PostMapping(path = "/autorisation", consumes = "application/json", produces = "application/json")
    public String postAutorisation(@Context HttpServletRequest request,@RequestBody String autorisation) {
        String token = request.getHeader("token");
        boolean accord=false;
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(autorisation);
            accord = jsonNode.path("autorisation").asBoolean();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        token=analyseController.ajoutAutorisation(accord,token);
        return token;
    }

    @PostMapping(path = "/patient", consumes = "application/json", produces = "application/json")
    public String postPatient(@Context HttpServletRequest request,@RequestBody Patient patient) {
        String token = request.getHeader("token");
        return analyseController.ajoutPatient(patient, token);
    }

    @PostMapping(path = "/ecriture", consumes = "application/json", produces = "application/json")
    public String postEcriture(@Context HttpServletRequest request,@RequestBody() String body) {
        String token = request.getHeader("token");
        JsonNode jsonNode;
        try{
        jsonNode = objectMapper.readTree(body);
        String bodyListePoint=jsonNode.path("liste_point").toString();
        ArrayList<Point> list_point =objectMapper.readValue(bodyListePoint,objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Point.class));
        token=analyseController.ajoutResultat(list_point, token);
        }catch(Exception e){
            System.out.println(e);
        }
        return token;
    }

    @DeleteMapping(path = "/remove")
    public void removeExamen(@Context HttpServletRequest request) {
        String token = request.getHeader("token");
        analyseController.finDeSession(token);
    }
}
