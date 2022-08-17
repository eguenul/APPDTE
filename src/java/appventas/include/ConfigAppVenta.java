/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.include;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class ConfigAppVenta {
    private final String serveraddress;
    private final String username;
    private final String userpass;
    private final String databasename;
    private final String pathreports;
    
    public ConfigAppVenta() throws ParserConfigurationException, SAXException, IOException{
        Properties prop = new Properties();
        try (InputStream in = getClass().getResourceAsStream("/appventas/properties/appventas.properties")) {
            prop.load(in);
       
          this.serveraddress = prop.getProperty("server-address");
          this.username = prop.getProperty("user-name");
          this.userpass = prop.getProperty("user-pass");
          this.databasename = prop.getProperty("database-name");
          this.pathreports = prop.getProperty("path-reports");
        }
    }
    
    
    
    public String getUserpass() {
        return userpass;
    }

    public String getServeraddress() {
        return serveraddress;
    }

    public String getUsername() {
        return username;
    }
    
    
  

    public String getDatabasename() {
        return databasename;
    }

    public String getPathreports() {
        return pathreports;
    }


    
}