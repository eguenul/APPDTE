/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.movimientos;

import appventas.cliprov.CliProv;
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
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class BusquedaDocServlet extends HttpServlet {
    
    @Override
public void    doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try {
            CliProv  objCliProv = (CliProv) request.getSession().getAttribute("objCliProv");
            MovimientoModel2 objMovimientoModel = new MovimientoModel2();
            ArrayList<Object[]> arraylistdoc;
            int auxcodsii = Integer.parseInt(request.getParameter("CodSii"));
            int numdoc = Integer.parseInt(request.getParameter("NumDoc"));
            arraylistdoc = objMovimientoModel.buscaDoc(numdoc,auxcodsii, objCliProv.getCliprovid());
            request.getSession().setAttribute("botonera","no");
            request.getSession().setAttribute("arraylistdoc",arraylistdoc);
            getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/listmovimiento.jsp").forward(request,response);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(BusquedaDocServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
}
