package petut.webDysgraphie.config;



import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import petut.webDysgraphie.api.RestController;
import petut.webDysgraphie.security.CORSResponseFilter;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RestController.class);
        register(CORSResponseFilter.class);
    }

}
