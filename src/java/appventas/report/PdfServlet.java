/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.report;

import com.appdte.sii.utilidades.ConfigAppDTE;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
public class PdfServlet extends HttpServlet {
@Override    

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        ConfigAppDTE objconfig = new ConfigAppDTE();
        String nombredocumento= (String) request.getSession().getAttribute("nombredocumento");
        
        File file = new File(objconfig.getPathdownload().trim()+nombredocumento.trim()+".pdf");
        response.setHeader("Content-Type", getServletContext().getMimeType(file.getName().trim()));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\""+nombredocumento.trim()+".pdf" + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
        file.delete();
    } catch (ParserConfigurationException | SAXException ex) {
        Logger.getLogger(PdfServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}

