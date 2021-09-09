/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.movimientos;

import java.io.IOException;
import java.io.PrintWriter;
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
public class searchServlet extends HttpServlet {
    

@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
      request.getSession().setAttribute("botonera","yes");
       if(request.getSession(true).getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
    
    
    request.getSession().setAttribute("referencia","no");
      getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/search.jsp").forward(request,response);
         
}


@Override
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    int empresaid = (int) request.getSession().getAttribute("empresaid");
   request.getSession().setAttribute("botonera","yes");
      
    try {
       request.getSession().setAttribute("referencia","no");
       String acc= request.getParameter("ACC");
         
         
       int pagina = 0;
       int indice = 0;
       String fechadesde = request.getParameter("FechaDesde");
       String fechahasta = request.getParameter("FechaHasta");
       MovimientoModel2 objMovimientoModel = new MovimientoModel2();
       int nroregistros;
       float auxnropaginas;
       int nropaginas; 
       ArrayList<Object[]> arraylistdoc;
       nroregistros = objMovimientoModel.conteoMovimiento(fechadesde, fechahasta, empresaid);  
       auxnropaginas =(float) nroregistros/10;
       nropaginas =(int) Math.ceil(auxnropaginas);
         
         request.getSession().setAttribute("nropaginas",nropaginas);
         
         switch (acc){
        
          case "BUSCAR":    
                        nroregistros = objMovimientoModel.conteoMovimiento(fechadesde, fechahasta, empresaid);
                        arraylistdoc = objMovimientoModel.listFecha(fechadesde, fechahasta, empresaid,indice);

                        if(arraylistdoc== null || arraylistdoc.size() == 0){
                            String mensaje = "<div class=\"alert alert-danger\"> <strong>ERROR</strong> NO SE ENCONTRARON REGISTROS</div>";
                            PrintWriter out=response.getWriter();
                            out.println(mensaje);
                        }else{
                       pagina = 1;
                       request.getSession().setAttribute("arraylistdoc",arraylistdoc);
                       request.getSession().setAttribute("FechaDesde",fechadesde);
                       request.getSession().setAttribute("FechaHasta",fechahasta);
                       request.getSession().setAttribute("pagina",pagina);
                       getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/listmovimiento.jsp").forward(request,response);
                      }

          break;
          
        
          case "ANT":
                  
                     pagina = Integer.parseInt(request.getParameter("pagina"));
                     
                     if(pagina==1){
                         pagina = 1;
                        indice = 0;
                     
                     }else{
                     
                     pagina = pagina -1;
                     indice = ((pagina - 1)*10);
                     }
                     arraylistdoc = objMovimientoModel.listFecha(fechadesde, fechahasta, empresaid,indice);
                     request.getSession().setAttribute("pagina",pagina);
                     request.getSession().setAttribute("FechaDesde",fechadesde);
                     request.getSession().setAttribute("FechaHasta",fechahasta);
                      request.getSession().setAttribute("arraylistdoc",arraylistdoc);
                     getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/listmovimiento.jsp").forward(request,response);
                     break;   
        
                     
           case "SIG":
                     
                     pagina = Integer.parseInt(request.getParameter("pagina"));
                     pagina = pagina + 1;
                     
                     if(pagina>nropaginas){
                         pagina = 1;
                     }
                     
                     indice = ((pagina - 1)*10);
                     arraylistdoc = objMovimientoModel.listFecha(fechadesde, fechahasta, empresaid,indice);
                     request.getSession().setAttribute("pagina",pagina);
                     request.getSession().setAttribute("FechaDesde",fechadesde);
                     request.getSession().setAttribute("FechaHasta",fechahasta);
                     request.getSession().setAttribute("arraylistdoc",arraylistdoc);
                     
                     
                     getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/listmovimiento.jsp").forward(request,response);
                     
                     break;              
                     
            
    }
        
        
        
    } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
        Logger.getLogger(searchServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException | ServletException | NumberFormatException ex) {
        Logger.getLogger(searchServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
 
 
}

}
