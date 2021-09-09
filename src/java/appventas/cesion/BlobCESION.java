/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.cesion;

import com.appdte.sii.utilidades.ConfigClass;
import appventas.include.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class BlobCESION {
        public void  registrarXML(int cesionid,String file) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException {
        ConfigClass objconfig = new ConfigClass();
        Conexion objaux = new Conexion();
        Connection conn = objaux.obtener();
        FileInputStream input = null;
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO BlobCESION (CesionId,ARCHIVO,BLOBDATA) VALUES(?,?,?)";
            System.out.print(sql);
            stmt = conn.prepareStatement(sql);
            input = new FileInputStream(new File(objconfig.getPathdte()+file));
            stmt.setInt(1, cesionid);
            stmt.setString(2, file);
            stmt.setBinaryStream(3, input);
            stmt.execute();
            System.out.println("> Archivo '" + file + "' registrado en la base de datos");
          
        } catch (FileNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (IOException | SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
    }

    
        public  void getXMLCESION(int cesionid) throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException {
        ConfigClass objconfig = new ConfigClass();
        Conexion objaux = new Conexion();
        Connection conn = objaux.obtener();
        
        
        Statement stmt = null;
        InputStream input = null;
        FileOutputStream output = null;
        try {
            String sql = "SELECT * from BlobCESION where MovimientoId="+ String.valueOf(cesionid);
           System.out.print(sql);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
   
            if (rs.next()) {
                File file = new File(objconfig.getPathdte()+rs.getString("ARCHIVO"));
                 output = new FileOutputStream(file);

                System.out.println("Leyendo archivo desde la base de datos...");
                byte[] buffer = rs.getBytes("BLOBDATA");
                
                    output.write(buffer);
                
                System.out.println("> Archivo guardado en : " + file.getAbsolutePath());
            }
        } catch (SQLException | IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (IOException | SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    

}
