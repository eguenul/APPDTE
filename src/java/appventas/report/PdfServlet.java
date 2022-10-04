/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appventas.report;

import com.appdte.sii.utilidades.ConfigAppDTE;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class PdfServlet  extends HttpServlet{
private final int ARBITARY_SIZE = 1048;
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
                try{            
                    response.setContentType("application/pdf");
                    response.setHeader("Content-disposition", "inline; filename=sample.pdf");
                    ConfigAppDTE objConfig = new ConfigAppDTE();
                    String nombredte = (String) request.getSession().getAttribute("nombredocumento");
                    String path_dte = objConfig.getPathpdf()+nombredte+".pdf";
                    File initialFile = new File(path_dte);
                    
                    try(InputStream in =  new FileInputStream(initialFile)){
                        OutputStream out = response.getOutputStream();
                        byte[] buffer = new byte[ARBITARY_SIZE];
                        
                        int numBytesRead;
                        while ((numBytesRead = in.read(buffer)) > 0) {
                            out.write(buffer, 0, numBytesRead);
                        }
                    }     
                } catch (ParserConfigurationException | SAXException ex) {
        Logger.getLogger(PdfServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
                    
    }  
}
