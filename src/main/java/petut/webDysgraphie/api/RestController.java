package petut.webDysgraphie.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public String getAnalyse() {
        return null;
    }

    @GetMapping(path = "/resultat/vitesse/inscription", produces = "application/json")
    public String getResultatVitesseInscription() {
        return null;
    }

    @GetMapping(path = "/resultat/vitesse", produces = "application/json")
    public String getResultatVitesse() {
        return null;
    }

    @GetMapping(path = "/resultat/acceleration", produces = "application/json")
    public String getResultatAcceleration() {
        return null;
    }

    @GetMapping(path = "/resultat/jerk", produces = "application/json")
    public String getResultatJerk() {
        return null;
    }

    @GetMapping(path = "/resultat/download", produces = "application/json")
    public String downloadResult() {
        return null;
    }

    @PostMapping(path = "/analyse", consumes = "application/json", produces = "application/json")
    public String postAnalyse(@RequestBody String typeAnalyse, @Context HttpServletRequest request) {
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

        String response = analyseController.analyseInit(typeAnalyse_p, token);
        return response;
    }

    @PostMapping(path = "/materiel", consumes = "application/json")
    public String postMateriel() {
        return null;
    }

    @PostMapping(path = "/autorisation", consumes = "application/json")
    public String postAutorisation() {
        return null;
    }

    @PostMapping(path = "/patient", consumes = "application/json")
    public String postPatient() {
        return null;
    }

    @PostMapping(path = "/ecriture", consumes = "application/json")
    public String postEcriture() {
        return null;
    }

    @DeleteMapping(path = "/remove")
    public String removeExamen() {
        return null;
    }
}
