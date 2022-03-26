package petut.webDysgraphie.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import petut.webDysgraphie.controller.AnalyseController;

/**
 *
 * @author jemon
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

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

    @PostMapping(path = "/analyse", consumes = "application/json")
    public String postAnalyse() {
        return null;
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
