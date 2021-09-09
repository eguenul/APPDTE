package appventas.movimientos;

import appventas.cliprov.CliProv;
import appventas.cliprov.CliProvModel;
import com.appdte.sii.utilidades.ConfigClass;
import com.appdte.sii.utilidades.PrintDTE;
import appventas.documento.DocumentoModel;
import appventas.empresa.Empresa;
import appventas.empresa.EmpresaModel;
import appventas.include.Funciones;
import appventas.producto.Producto;
import appventas.producto.ProductoModel;
import appventas.usuarios.Usuario;
import appventas.usuarios.UsuarioModel;
import com.appdte.sii.utilidades.FuncionesCAF;
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
public class addMovimientoServlet extends HttpServlet { 
    @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
                int empresaid = (int) request.getSession().getAttribute("empresaid");
    
               
       if(request.getSession(true).getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
             
            CliProvModel objCliProvModel = new CliProvModel(empresaid);      
            CliProv objCliProv = objCliProvModel.searchCliProv(Integer.parseInt(request.getParameter("CliProvCod")));
            Movimiento objMovimiento = new Movimiento();
            objMovimiento.setObjcliprov(objCliProv);
            int exento = Integer.parseInt(request.getParameter("Exento"));
            int iva = Integer.parseInt(request.getParameter("Iva"));
            int totalneto = Integer.parseInt(request.getParameter("TotalNeto"));
            int totalbruto = Integer.parseInt(request.getParameter("TotalBruto"));
            int tipodocumento =  Integer.parseInt(request.getParameter("TipoDocumento"));
            objMovimiento.setTipodoc(tipodocumento);
            DocumentoModel objDocumento = new DocumentoModel();
            String fechadoc = request.getParameter("FechaDoc");
            String codsii = String.valueOf(objDocumento.getSiiCod(tipodocumento));
            String campo="";
           
         
            String fchref =(String)request.getParameter("FchRef");
            
            objMovimiento.setFchref(fchref);
             
            
            int  tipodespachoid = Integer.parseInt(request.getParameter("TipoDespacho"));
            int   tipotrasladoid = Integer.parseInt(request.getParameter("TipoTraslado"));
             /* Despacho objDespacho = new Despacho(); */
           DespachoModel objDespachoModel = new DespachoModel();
           Despacho objDespacho = objDespachoModel.getData(tipodespachoid);
           objMovimiento.setTipodespacho(objDespacho);
           
           TrasladoModel objTrasladoModel = new TrasladoModel();
           Traslado objTraslado = objTrasladoModel.getData(tipotrasladoid);
           
           objMovimiento.setTipotraslado(objTraslado);
           objMovimiento.setReferenciaGlobal(request.getParameter("Observacion"));
           
           /*
            objDespacho.setDespachoid(tipodespachoid);
            Traslado objTraslado = new Traslado();
             */       
            switch (codsii) {
            
               case "33":
                         campo = "FacVentaAfecta";
                         break;
                       
               case "52":
                         campo = "GuiaDespacho";   
                         break;
               
               
               case "61":
                         campo = "NotaCredito";
                         break;
            
                         
                         
              
               
               case "34":
                         campo = "FacVentaExenta";
                         break;
           }
           
            
          if(request.getSession(true).getAttribute("referencia")=="no"){
              objMovimiento.setBolref(false);
              objMovimiento.setOrdcompranum(Integer.parseInt(request.getParameter("OrdCompraNum")));
              objMovimiento.setCodsiiref(801);
              
              
          }else{
                objMovimiento.setBolref(true);
                objMovimiento.setOrdcompranum(1);
         
                  switch (codsii) { 
                  case "33":
                      
                  objMovimiento.setGuiadesp(Integer.parseInt(request.getParameter("FolioRef")));
                  objMovimiento.setCodsiiref(52);
            
                  /* campo = "FacVentaAfecta"; */
                  break;
                           /*   case "52":       
                                       campo = "GuiaDespacho";   
                                        break;
                             */ 
                 case "61":
                     
                 int auxcodsiiref = (int) request.getSession().getAttribute("codsii");    
                 objMovimiento.setCodsiiref(auxcodsiiref);
                 if(auxcodsiiref==33){
                 objMovimiento.setFacafecta(Integer.parseInt(request.getParameter("FolioRef")));
                 }
                 
                 if(auxcodsiiref==34){
                 objMovimiento.setFacexenta(Integer.parseInt(request.getParameter("FolioRef")));
                 }
                 
                   System.out.print(auxcodsiiref);
                    break;
                  }   
                
                
                
                
                
                
                
                
                
          }
          
          
          
          
          boolean validacionform = true;
          
          
            Funciones objfunciones = new Funciones();
          
            if(objfunciones.buscaFolios(empresaid, codsii)==false){
                validacionform = false;
                  response.sendRedirect("messageview/errorfolio.html");
            }
            
        
            
             if(("34".equals(codsii)) & (totalneto > 0)){
                  validacionform = false;
                 response.sendRedirect("messageview/errormontoafecto.html");
            }
         
              if(("33".equals(codsii)) & (totalneto==0)){
                  validacionform = false;
                 response.sendRedirect("messageview/errormontoafecto2.html");
            }
         
          
          
            int numcorrelativo =  objDocumento.searchCorrelativo(empresaid, campo);
            objMovimiento.setFechamov(fechadoc);
            objMovimiento.setNumdoc(numcorrelativo);
            objMovimiento.setMontoexento(exento);
            objMovimiento.setMontoiva(iva);
            objMovimiento.setMontototal(totalbruto);
            objMovimiento.setMontoafecto(totalneto);
            MovimientoModel objMovimientoModel = new MovimientoModel();
            
           EmpresaModel objEmpresaModel = new EmpresaModel();
           Empresa objEmpresa = new Empresa();
           objEmpresa = objEmpresaModel.getData(empresaid);
          
            FuncionesCAF objCAF = new FuncionesCAF();
            
            if (objCAF.validaCAF(objEmpresa.getEmpresarut(),Integer.parseInt(codsii),numcorrelativo)==false){
                validacionform = false;
                response.sendRedirect("messageview/errorcaf.html");
             
            }
            
           if(validacionform==true){ 
            
            objMovimientoModel.addDocumento(empresaid, objCliProv.getCliprovid(), objMovimiento);
            
          int referenciaflag = (int) request.getSession().getAttribute("ReferenciaFlag");
          int auxidmovimiento = (int) request.getSession().getAttribute("MovimientoId");
            
          if(referenciaflag==1){
              objMovimientoModel.updateFlagReferencia(auxidmovimiento, referenciaflag);
          }
            
            
            
            
            
            
            
            
            
            int idmovimiento = objMovimientoModel.searchId(objCliProv.getCliprovid(), tipodocumento, numcorrelativo);
            objDocumento.updateCorrelativo(empresaid, campo);
           
           // ArrayList<DetalleMovimiento> arraydetalle = new ArrayList<>();           
            int nrofilas = Integer.parseInt(request.getParameter("NRO_FILAS"));
             
              
            for(int i=0;i<nrofilas;i++){
               int productocod = Integer.parseInt(request.getParameter("ProductoCod"+String.valueOf(i)));
               ProductoModel objProductoModel = new ProductoModel(empresaid);
               Producto objProducto = new Producto();      
               objProducto =  objProductoModel.searchProducto(productocod);
               DetalleMovimiento objDetalleMovimiento = new DetalleMovimiento();
               objDetalleMovimiento.setObjProducto(objProducto);
               objDetalleMovimiento.setCantidad(Integer.parseInt(request.getParameter("Cantidad"+String.valueOf(i))));
               objDetalleMovimiento.setTotal(Integer.parseInt(request.getParameter("Total"+String.valueOf(i))));
        //     arraydetalle.add(objDetalleMovimiento);            
               objMovimientoModel.addDetalle(idmovimiento, objDetalleMovimiento);
            }
            /* inicio la secuencia de crear el xml */
           MovimientoController objMovimientoController;
           objMovimientoController = new MovimientoController();
           
           
           
           String login = (String) request.getSession().getAttribute("login");
           
           UsuarioModel objUsuarioModel = new UsuarioModel();
           
           Usuario objUsuario = objUsuarioModel.getUsuario(login);
           
            
           
           String trackid = objMovimientoController.sendDTE(objUsuario, empresaid, idmovimiento);
           
        if("0".equals(trackid)==true ){
               objMovimientoModel.deleteMovimiento(idmovimiento);
              response.sendRedirect("messageview/errorenvio.html");
            
        }else{
           objMovimientoModel.updateTRACKID(idmovimiento, trackid);  
          
           
           String rutempresa = objEmpresa.getEmpresarut();
           
           
            ConfigClass objConfig = new ConfigClass();
           /* */
           
         /* preparo la impresion del documento */
            BlobDTE objblob = new BlobDTE();
            objblob.getXMLDTE(idmovimiento);
         
         
          PrintDTE objPrint = new PrintDTE(objConfig.getPathdownload());
          System.out.print(objConfig.getPathcaf());
           objPrint.printDTE(rutempresa.trim(), String.valueOf(numcorrelativo), codsii);
           String[] arrayrutemisor = rutempresa.split("-");
           rutempresa = arrayrutemisor[0];
           //request.getSession().setAttribute("nombre_param", "valor_param");
           request.getSession().setAttribute("trackid",trackid);
           request.getSession().setAttribute("nombredocumento","ENVDTE"+rutempresa.trim()+"F"+String.valueOf(numcorrelativo)+"T"+codsii);
           request.getSession().setAttribute("tipovista","emision");
           response.sendRedirect("movimientoview/successfull.jsp");
           
        }    
        }
         } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
             Logger.getLogger(addMovimientoServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(addMovimientoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(addMovimientoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            
     
     }
     

}
