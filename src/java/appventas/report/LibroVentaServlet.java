
package appventas.report;

import com.appdte.sii.utilidades.ConfigClass;
import appventas.movimientos.MovimientoModel2;
import java.io.IOException;
import java.io.PrintWriter;
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


public class LibroVentaServlet extends HttpServlet {
    
    
  @Override
  public void  doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
         
      /*
       if(request.getSession(true).getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
*/
         int empresaid = (int) request.getSession().getAttribute("empresaid");
          request.getSession().setAttribute("empresaid", empresaid); 
      getServletConfig().getServletContext().getRequestDispatcher("/reportview/libroventa.jsp").forward(request,response);
  
        
    }
    
  @Override
  public void  doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
       try {
               int empresaid = (int) request.getSession().getAttribute("empresaid");
          request.getSession().setAttribute("empresaid", empresaid); 
    
          MovimientoModel2  objMovimientoModel = new MovimientoModel2();
            String fechadesde = request.getParameter("FechaDesde");
            String fechahasta = request.getParameter("FechaHasta");
                   String extension;
                   extension =request.getParameter("extension");
          int nroregistros = objMovimientoModel.conteoMovimiento(fechadesde, fechahasta, empresaid);
          
          if(nroregistros>0){

                            System.out.print(empresaid);
                            String fecha1 = fechadesde;



                            String fecha2 = fechahasta;
              
                            ConfigClass objconfig = new ConfigClass();
                            Report objReport = new Report("ventas",objconfig.getPathdownload(),"VENTAS"+String.valueOf(empresaid) +"."+extension);
                            
                          
                            request.getSession().setAttribute("nombredocumento", "VENTAS"+String.valueOf(empresaid));
                            objReport.setParameters("parmEmpresaId", String.valueOf(empresaid));
                            objReport.setParameters("parmFechaDesde", fecha1);
                            objReport.setParameters("parmFechaHasta", fecha2);
                            request.getSession().setAttribute("extension",extension);
                        if( "xls".equals(extension)){        
                            objReport.showExcel();
                        }else{
                            objReport.showReport();
                            
                        }
              getServletConfig().getServletContext().getRequestDispatcher("/reportview/download.jsp").forward(request,response);
          }else{
                    String mensaje = "<div class=\"alert alert-danger\"> <strong>ERROR</strong> NO SE ENCONTRARON REGISTROS</div>";
                            PrintWriter out=response.getWriter();
                            out.println(mensaje);
                       
          }
      
          } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException | JRException ex) {
          
                 PrintWriter out=response.getWriter();
                            out.println(ex.getMessage());
                       
              Logger.getLogger(LibroVentaServlet.class.getName()).log(Level.SEVERE, null, ex);
      
          }
   
      
    }
    
}
