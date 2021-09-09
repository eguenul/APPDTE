/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.cesion;

import com.appdte.sii.utilidades.ConfigClass;
import appventas.report.Report;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JRException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class BuscaCesionServlet extends HttpServlet{
    
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
   if(request.getSession().getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
     

    
    
    request.getSession().setAttribute("modulo", "buscacesion");
getServletContext().getRequestDispatcher("/cesionview/buscacesion.jsp").forward(request,response);
request.getSession().setAttribute("botonera","no");
}
    
     @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
       
    try {
        String acc = request.getParameter("ACC");
        
        
        switch (acc){
            
            case "BUSCAR":
                
                
                int numcesion = Integer.parseInt(request.getParameter("CesionNum"));
                int empresaid = (int) request.getSession().getAttribute("empresaid");
                CesionModel objCesionModel = new CesionModel(empresaid);
                ArrayList<Object[]> arraycesion = objCesionModel.buscaCesion(numcesion);
                request.getSession().setAttribute("arraycesion", arraycesion);
                  
                ConfigClass objconfig = new ConfigClass();
                
        String archivosalida = "cesion"+(String) request.getSession().getAttribute("login");
        Report objReport = new Report("cesion",objconfig.getPathdownload(),archivosalida+".pdf");
        objReport.setParameters("parmEmpresaId", String.valueOf(empresaid));
        objReport.setParameters("parmCesionNum",String.valueOf(numcesion));
        objReport.showReport();
        request.getSession().setAttribute("nombredocumento",archivosalida); 
        getServletContext().getRequestDispatcher("/cesionview/listacesion.jsp").forward(request,response);
        break;              
        }
    } catch (ParserConfigurationException | SAXException | JRException | SQLException | ClassNotFoundException ex) {
        Logger.getLogger(BuscaCesionServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    } 
       
    
    
    
    
    
}
