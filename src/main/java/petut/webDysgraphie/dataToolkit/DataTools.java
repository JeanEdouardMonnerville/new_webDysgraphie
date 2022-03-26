
package petut.webDysgraphie.dataToolkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import petut.webDysgraphie.model.Analyse;

/**
 *
 * @author jemon
 */
public class DataTools {
    private String filePath = "src/main/resources/";
    private AlphaNumericStringGenerator randomGen=new AlphaNumericStringGenerator();
    public DataTools() {
    }
            
    public Analyse readAnalyseFromXml(String token) {
        if(token==null){
            token = randomGen.getRandomString(15);
        }
        Analyse analyse = null;
        try {
            JAXBContext cont = JAXBContext.newInstance(Analyse.class);
            Unmarshaller u = cont.createUnmarshaller();
            File file = new File(filePath + token + "-analyse.xml");
            if (file.exists()) {
                analyse = (Analyse) u.unmarshal(file);
            } else {
                InputStream input = getClass().getClassLoader().getResourceAsStream("analyse.xml");
                analyse = (Analyse) u.unmarshal(input);
                assert input != null;
                input.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        analyse.setToken(token);
        return analyse;
    }

    public void saveAnalyseToXml(Analyse analyse, String token) {
        try {
            JAXBContext cont = JAXBContext.newInstance(Analyse.class);
            Marshaller m = cont.createMarshaller();
            File file = new File(filePath + token + "-analyse.xml");

            if (!file.exists()) {
                OutputStream output = new FileOutputStream(filePath + token + "-analyse.xml");
                m.marshal(analyse, output);
                output.close();
            } else {
                OutputStream output = new FileOutputStream(filePath + token + "-analyse.xml");
                m.marshal(analyse, output);

                output.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
