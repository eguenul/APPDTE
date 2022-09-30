/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.movimientos;


import appventas.cliprov.CliProv;
import appventas.cliprov.CliProvModel;
import appventas.documento.Documento;
import appventas.documento.DocumentoModel;
import appventas.fpago.FPago;
import appventas.fpago.FPagoModel;
import appventas.producto.Producto;
import appventas.producto.ProductoModel;
import java.io.IOException;
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

public class MovimientoServlet extends HttpServlet {

    /**
     * Servlet de ejemplo que procesa una petición GET
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         if(request.getSession().getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
     
        
        
        int empresaid =  (int) request.getSession().getAttribute("empresaid");
        request.getSession().setAttribute("empresaid", empresaid);
        try {
           
         
        request.getSession().setAttribute("referencia","no");
        
        request.getSession().setAttribute("ReferenciaFlag",0);
        request.getSession().setAttribute("MovimientoId",0);
       
        
        
        
        
       DespachoModel objDespacho = new DespachoModel();    
       List<Despacho> arraydespacho = objDespacho.listDespacho();
          
       
       TrasladoModel objTraslado = new TrasladoModel();    
       List<Traslado> arraytraslado = objTraslado.listTraslado();
    
       
       
      DocumentoModel objDocumentoModel = new DocumentoModel();
      List<Documento> arraylistdocumento = objDocumentoModel.listDocuments();
      
      CliProvModel objCliProvModel = new CliProvModel(empresaid);
      ArrayList<CliProv> arraylistcliprov = objCliProvModel.listaCliProv(0);
      
      ProductoModel objProductoModel = new ProductoModel(empresaid);
      ArrayList<Producto>  arrayproducto = objProductoModel.listProducto(0);
      
      FPagoModel objFPagoModel =  new FPagoModel();
      
      ArrayList<FPago> arrayfpago = objFPagoModel.listFpago();
              
      
       
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
      
      
      request.getSession().setAttribute("servletName",arraylistdocumento);
      request.getSession().setAttribute("arraylistcliprov",arraylistcliprov);
     request.getSession().setAttribute("objcliprov",objcliprov);
     request.getSession().setAttribute("arrayproducto",arrayproducto);   
    
    request.getSession().setAttribute("arraydespacho",arraydespacho);
    request.getSession().setAttribute("arraytraslado",arraytraslado);
         
    
      request.getSession().setAttribute("arrayfpago",arrayfpago);
    
    
    
    
       request.getSession().setAttribute("modulo", "movimiento");
     getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/addmovimiento.jsp").forward(request,response);
  
       
    } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
        Logger.getLogger(MovimientoServlet.class.getName()).log(Level.SEVERE, null, ex);
    
    
    }
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           int empresaid = (int) request.getSession().getAttribute("empresaid");
   
        String acc = request.getParameter("ACC");
         try {
        if("BUSCAR".equals(acc)){    
            
                
                request.getSession().setAttribute("ReferenciaFlag",0);
                request.getSession().setAttribute("MovimientoId",0);
                 
               DespachoModel objDespacho = new DespachoModel();    
               request.getSession().setAttribute("arraydespacho",objDespacho.listDespacho());
       
                DocumentoModel objDocumentoModel;
                objDocumentoModel = new DocumentoModel();
                
                List<Documento> arraylistdocumento = objDocumentoModel.listDocuments();
                CliProvModel objCliProvModel = new CliProvModel(empresaid);
                
                CliProv objCliProv = objCliProvModel.searchCliProv(Integer.parseInt(request.getParameter("CliProvCod")));
                
                
                ArrayList<CliProv> arraylistcliprov = objCliProvModel.listaCliProv(0);
                
                ProductoModel objProductoModel = new ProductoModel(empresaid);
                ArrayList<Producto>  arrayproducto = objProductoModel.listProducto(0);
                request.getSession().setAttribute("arrayproducto",arrayproducto);
                
                
                request.getSession().setAttribute("servletName",arraylistdocumento);
                request.getSession().setAttribute("arraylistcliprov",arraylistcliprov);
                request.getSession().setAttribute("objcliprov",objCliProv);
                request.getSession().setAttribute("referencia","no");
                
                request.getSession().setAttribute("modulo", "movimiento");
                
                
                getServletConfig().getServletContext().getRequestDispatcher("/movimientoview/addmovimiento.jsp").forward(request,response);
            } 
                
                    
                } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException | IOException | NumberFormatException ex) {
                    Logger.getLogger(MovimientoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            
             }
            
            
            
            
            
                
            }

    


