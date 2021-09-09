
package appventas.movimientos;
import appventas.cliprov.CliProv;
import appventas.cliprov.CliProvModel;
import appventas.documento.Documento;
import appventas.documento.DocumentoModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class MovimientoServlet2 extends HttpServlet {
    
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    try {
        
       if(request.getSession().getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
        
         int empresaid = (int) request.getSession().getAttribute("empresaid");
    
         
          request.getSession().setAttribute("ReferenciaFlag",1);
          request.getSession().setAttribute("MovimientoId",0);
               
         request.getSession().setAttribute("modulo","movimiento");
         
         
        CliProv objcliprov = new CliProv();
        objcliprov.setCliprovciu("");
        objcliprov.setCliprovcod(0);
        objcliprov.setCliprovcom("");
        objcliprov.setCliprovdir("");
        objcliprov.setCliprovema("");
        objcliprov.setCliprovfon("");
        objcliprov.setCliprovgir("");
        objcliprov.setCliprovraz("");
        objcliprov.setCliprovrut("");
        request.getSession().setAttribute("objcliprov",objcliprov); 
        request.getSession().setAttribute("referencia","yes");
        
        
        DocumentoModel objDocumentoModel;
        objDocumentoModel = new DocumentoModel();
        List<Documento> arraylistdocumento = objDocumentoModel.listDocuments();
        
        
        request.getSession().setAttribute("servletName",arraylistdocumento);
        CliProvModel objCliProvModel = new CliProvModel(empresaid);
             /*   CliProv objCliProv = objCliProvModel.searchCliProv(Integer.parseInt(request.getParameter("CliProvCod")));
               */         
        ArrayList<CliProv> arraylistcliprov = objCliProvModel.listaCliProv(0);
        request.getSession().setAttribute("arraylistcliprov",arraylistcliprov);   
        DocumentoModel objDocumentoModel2;
        objDocumentoModel2 = new DocumentoModel();
        List<Documento> arraylistdocumento2 = objDocumentoModel2.listDocuments();
        
        request.getSession().setAttribute("servletName",arraylistdocumento2);
             
               
        /*
                request.getSession().setAttribute("arrayproducto",arrayproducto);
        */
        
        getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/addmovimiento2.jsp").forward(request,response);
   
        }catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
        Logger.getLogger(MovimientoServlet2.class.getName()).log(Level.SEVERE, null, ex);                                        
    }
  
       
        
    }

 @Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
    try {
         int empresaid = (int) request.getSession().getAttribute("empresaid");
    
        CliProvModel objCliProvModel = new CliProvModel(empresaid);
        CliProv objCliProv = objCliProvModel.searchCliProv(Integer.parseInt(request.getParameter("CliProvCod")));
       request.getSession().setAttribute("CliProvCod", objCliProv.getCliprovcod());
        
         objCliProv.setCliprovciu("");
        objCliProv.setCliprovcom("");
        objCliProv.setCliprovdir("");
        objCliProv.setCliprovema("");
        objCliProv.setCliprovfon("");
        objCliProv.setCliprovgir("");
        objCliProv.setCliprovraz("");
        objCliProv.setCliprovrut("");
        
           String acc = request.getParameter("ACC");
        
        
         if("BUSCAR".equals(acc)){
                            CliProv objCliProv2 = objCliProvModel.searchCliProv(Integer.parseInt(request.getParameter("CliProvCod")));
                            request.getSession().setAttribute("objcliprov",objCliProv2);    
                            
         
         
        
        ArrayList<CliProv> arraylistcliprov2 = objCliProvModel.listaCliProv(0);
     
        request.getSession().setAttribute("referencia","yes");
        request.getSession().setAttribute("arraylistcliprov",arraylistcliprov2);
        
        
        
             
        DocumentoModel objDocumentoModel2;
        objDocumentoModel2 = new DocumentoModel();
                        List<Documento> arraylistdocumento2 = objDocumentoModel2.listDocuments();
        
        
        request.getSession().setAttribute("servletName",arraylistdocumento2);
             
        
        getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/addmovimiento2.jsp").forward(request,response);
     }
        
         if("LISTAR".equals(acc)){
            request.getSession().setAttribute("referencia","yes");
            
             CliProvModel auxobjCliProvModel = new CliProvModel(empresaid);      
             objCliProv = auxobjCliProvModel.searchCliProv(Integer.parseInt(request.getParameter("CliProvCod")));
            request.getSession().setAttribute("objCliProv", objCliProv);
       
            int tipodocumento =  Integer.parseInt(request.getParameter("TipoDocumento"));
            DocumentoModel objDocumento = new DocumentoModel();
            String codsii = String.valueOf(objDocumento.getSiiCod(tipodocumento));
            
            
            String nombredoc =  objDocumento.getNombreDoc(tipodocumento);
            request.getSession().setAttribute("nombredoc",nombredoc);  
            request.getSession().setAttribute("TipoDocumento",tipodocumento);  
             
            request.getSession().setAttribute("codsii",codsii);
           
            getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/select.jsp").forward(request,response);
     
            
            
                 
         }
         
         
        
         
         if("SELECT".equals(acc)){
              request.getSession().setAttribute("referencia","yes");
             int idmovimiento = Integer.parseInt(request.getParameter("Id"));
             MovimientoModel2 objMovimientoModel = new MovimientoModel2();
             Movimiento objMovimiento = objMovimientoModel.getData(idmovimiento);
             
             
             
         
              request.getSession().setAttribute("ReferenciaFlag",1);
              request.getSession().setAttribute("MovimientoId",idmovimiento);
               
             
             
             
             
            request.getSession().setAttribute("objMovimiento", objMovimiento);
             objCliProv = objCliProvModel.searchCliProv(Integer.parseInt(request.getParameter("CliProvCod")));
             request.getSession().setAttribute("objcliprov", objCliProv);           
             ArrayList<DetalleMovimiento> arraydetalle = objMovimientoModel.listDetalle(idmovimiento);
             request.getSession().setAttribute("arraydetalle", arraydetalle);
             Object[] arrayreferencia = objMovimientoModel.getReferencia(idmovimiento);
            
             int numdoc = (int) arrayreferencia[1];
             String fechadoc = (String) arrayreferencia[0];
             String docdes = (String) arrayreferencia[2];
             int codsii = (int) arrayreferencia[3];
             System.out.print(codsii);
            
             request.getSession().setAttribute("numdoc", numdoc);
             request.getSession().setAttribute("docdes", docdes);
             request.getSession().setAttribute("codsii", codsii);
             request.getSession().setAttribute("fechadoc", fechadoc);
             
             
              
              DespachoModel objDespacho = new DespachoModel();    
       List<Despacho> arraydespacho = objDespacho.listDespacho();
          
       
       TrasladoModel objTraslado = new TrasladoModel();    
       List<Traslado> arraytraslado = objTraslado.listTraslado();
     
         
    request.getSession().setAttribute("arraydespacho",arraydespacho);
    request.getSession().setAttribute("arraytraslado",arraytraslado);
             
             
             
            
             //   PrintWriter out = response.getWriter(); 
             //ya podemos enviar al navegador
             // out.println(request.getSession().getAttribute("numdoc", numdoc));
             
             
            
            getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/addmovimiento.jsp").forward(request,response);
            
             //  PrintWriter out = response.getWriter(); 
             //ya podemos enviar al navegador
           //  out.println(idmovimiento);
         }
         
         
        
        
        
} catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException | IOException ex) {
     
         PrintWriter out = response.getWriter(); 
                   //ya podemos enviar al navegador
                                   out.println(ex.getMessage());
       
    
    
    Logger.getLogger(MovimientoServlet2.class.getName()).log(Level.SEVERE, null, ex);
    }
  
                

}
   

}
