/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.parametros;

import java.io.IOException;
import java.sql.SQLException;
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
public class CorrelativoServlet extends HttpServlet{
    
    @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try {
            CorrelativoModel objCorrelativoModel = new CorrelativoModel();
            int empresaid = (int) request.getSession().getAttribute("empresaid");
            Correlativo objCorrelativo = objCorrelativoModel.showCorrelativo(empresaid);
            request.getSession().setAttribute("objCorrelativo", objCorrelativo);
               getServletConfig().getServletContext().getRequestDispatcher("/parametrosview/setcorrelativo.jsp").forward(request,response);
  
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(CorrelativoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     }
     
      @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try {
            int empresaid = (int) request.getSession().getAttribute("empresaid");
            Correlativo objCorrelativo = new Correlativo();
            
            objCorrelativo.setFacventaafecta(Integer.parseInt(request.getParameter("FacVentaAfecta")));
            objCorrelativo.setNotacredito(Integer.parseInt(request.getParameter("NotaCredito")));
            objCorrelativo.setFacventaexenta(Integer.parseInt(request.getParameter("FacVentaExenta")));
            CorrelativoModel objCorrelativoModel = new CorrelativoModel();
            objCorrelativoModel.setCorrelativos(objCorrelativo, empresaid);
            objCorrelativo = objCorrelativoModel.showCorrelativo(empresaid);
            request.getSession().setAttribute("objCorrelativo", objCorrelativo);
            request.getSession().setAttribute("ESTADO", "ok");
            
            
            
            getServletConfig().getServletContext().getRequestDispatcher("/parametrosview/setcorrelativo.jsp").forward(request,response);
  
            
            
            
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(CorrelativoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
     }
     
     
    
    
}
