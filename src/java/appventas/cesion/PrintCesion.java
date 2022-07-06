package appventas.cesion;

import com.appdte.sii.utilidades.ConfigAppDTE;
import appventas.report.Report;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JRException;
import org.xml.sax.SAXException;

public class PrintCesion extends HttpServlet{

@Override
 
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
 
    
    try {
        ConfigAppDTE objconfig = new ConfigAppDTE();
        String archivosalida = "cesion"+(String) request.getSession().getAttribute("login");
        Report objReport = new Report("cesion",objconfig.getPathdownload(),archivosalida+".pdf");
        int empresaid = (int) request.getSession().getAttribute("empresaid");
        
        int auxnumcesion = Integer.parseInt(request.getParameter( "CesionNum"));
        objReport.setParameters("parmEmpresaId",String.valueOf(empresaid));
        objReport.setParameters("parmCesionNum",String.valueOf(auxnumcesion));
        objReport.showReport();
        request.getSession().setAttribute("nombredocumento",archivosalida);
        request.getSession().setAttribute("extension","pdf");
        getServletConfig().getServletContext().getRequestDispatcher("/reportview/download.jsp").forward(request,response);
    
    
    } catch (ParserConfigurationException | SAXException | JRException | SQLException | ClassNotFoundException ex) {
        Logger.getLogger(PrintCesion.class.getName()).log(Level.SEVERE, null, ex);
    }
      
 }
}
