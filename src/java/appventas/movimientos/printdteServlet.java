/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.movimientos;

import com.appdte.sii.utilidades.PrintDTE;
import appventas.empresa.Empresa;
import appventas.empresa.EmpresaModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.appdte.sii.utilidades.ConfigAppDTE;
public class printdteServlet extends HttpServlet {



@Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    try {
        
        ConfigAppDTE objconfig = new ConfigAppDTE();
      int empresaid = (int)request.getSession().getAttribute("empresaid");
        EmpresaModel objEmpresaModel = new EmpresaModel();
        Empresa objEmpresa = new Empresa();
        objEmpresa = objEmpresaModel.getData(empresaid);
        String rutempresa = objEmpresa.getEmpresarut();
        int idmovimiento = Integer.parseInt(request.getParameter("Id"));
        BlobDTE objblobdte = new BlobDTE();
        objblobdte.getXMLDTE(idmovimiento);
        MovimientoModel datamodel = new MovimientoModel();
        Object[] documentdata = datamodel.showDocument(idmovimiento);
      
        int numcorrelativo = (int) documentdata[9];
        String codsii =(String) documentdata[8];
        PrintDTE objPrint = new PrintDTE(objconfig.getPathdownload());
        objPrint.printDTE(rutempresa.trim(), String.valueOf(numcorrelativo), (String) codsii);
        String arrayrutempresa[] = rutempresa.split("-");
        String auxrutempresa = arrayrutempresa[0];
        request.getSession().setAttribute("nombredocumento","ENVDTE"+auxrutempresa.trim()+"F"+String.valueOf(numcorrelativo)+"T"+codsii);
        request.getSession().setAttribute("tipovista","impresion");
        response.sendRedirect("movimientoview/successfull.jsp");
          
    } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
        Logger.getLogger(printdteServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(printdteServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
                            
                               
}

    
}
