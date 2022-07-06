package appventas.cesion;

import appventas.documento.Documento;
import appventas.documento.DocumentoModel;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appdte.sii.utilidades.ConfigAppDTE;
import appventas.empresa.Empresa;
import appventas.empresa.EmpresaModel;
import appventas.movimientos.BlobDTE;
import appventas.movimientos.MovimientoModel;
import appventas.usuarios.Usuario;
import appventas.usuarios.UsuarioModel;
import com.appdte.sii.funcionesws.ConsultaAceptacion;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
public class CesionServlet extends HttpServlet{

@Override 
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    try {
        if(request.getSession().getAttribute("loginauth")==null){
            request.getRequestDispatcher("login").forward(request, response);
        }
        
        DocumentoModel objDocumentoModel;
        objDocumentoModel = new DocumentoModel();
        List<Documento> arraylistdocumento = objDocumentoModel.listDocuments();
        request.getSession().setAttribute("arraylistdocumento",arraylistdocumento);
            
        
        
        
        request.getSession().setAttribute("modulo","cesion");
        request.getSession().setAttribute("TIPOCESION","DTE");
        getServletContext().getRequestDispatcher("/cesionview/listdocument.jsp").forward(request,response);
    } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
        Logger.getLogger(CesionServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
     
}

@Override 
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try {
            int empresaid = (int) (request.getSession().getAttribute("empresaid"));
            request.getSession().setAttribute("empresaid",empresaid);
            String acc = request.getParameter("ACC");
            
                  
            
            switch (acc){
                
             case "BUSQUEDADOC":
                     
                    int numdoc = Integer.parseInt(request.getParameter("NumDoc"));
                    int tipodocumento = Integer.parseInt(request.getParameter("TipoDocumento"));
                    DocumentoModel objDocumentoModel = new DocumentoModel();
                    int auxcodsii = objDocumentoModel.getSiiCod(tipodocumento);
                            
                            
                            
                    CesionModel objCesionModel = new CesionModel(empresaid);
                    if(objCesionModel.buscaFactura(numdoc, auxcodsii)==false){
                    String mensaje ="<div class=\"alert alert-warning\" role=\"alert\">"+
                     "Documento no existente o no disponible para ceder"+
                     "</div>";
                       java.io.PrintWriter out = response.getWriter();
                       out.print(mensaje);
                    }else{
                  ArrayList<Object[]> arraylistdoc = objCesionModel.showFactura(numdoc,auxcodsii);
                   request.getSession().setAttribute("arraylistdoc",arraylistdoc);     
                  getServletConfig().getServletContext().getRequestDispatcher("/cesionview/listdocument2.jsp").forward(request,response);
                  
                    }
                     break;
                    
          
                
                
                
                 
                case "SELECT":
                      ConfigAppDTE objConfig = new ConfigAppDTE();
                      
                      int iddoc = Integer.parseInt(request.getParameter("MovimientoId"));
                      BlobDTE objblobdte = new BlobDTE();
                      
                      
                      
                      request.getSession().setAttribute("MovimientoId", iddoc);
                      objblobdte.getXMLDTE(iddoc);
      
                      MovimientoModel objMovModel = new MovimientoModel();
                      Object[] objdoc = objMovModel.showDocument(iddoc);
                      
                      int folio = (int) objdoc[9];
                      String codsii = (String) objdoc[8];
                      
                      Empresa objEmpresa = new Empresa();
                      EmpresaModel objEmpresaModel = new EmpresaModel();
                      objEmpresa = objEmpresaModel.getData(empresaid);
                      
                      String auxlogin = (String) request.getSession().getAttribute("login");
                      UsuarioModel objUsuarioModel = new UsuarioModel();
                      
                      Usuario objUsuario = objUsuarioModel.getUsuario(auxlogin);
                      ConsultaAceptacion objConsultaAceptacion = new ConsultaAceptacion();
                     
                      String[] arrayrutemisor = objEmpresa.getEmpresarut().trim().split("-");
                       
                      objConsultaAceptacion.setRutEmisor(arrayrutemisor[0]);
                      objConsultaAceptacion.setDvEmisor(arrayrutemisor[1]);
                      objConsultaAceptacion.setFolio(String.valueOf(folio));
                      objConsultaAceptacion.setTipoDoc(codsii);
                      
                      BlobDTE objBlobDTE = new BlobDTE();
                      objBlobDTE.getXMLDTE(iddoc);
                        
                     String estadoaceptacion = objConsultaAceptacion.consultarDocDteCedible(objUsuario.getLogin(), objUsuario.getPassword());
                      
                      if (("25".equals(estadoaceptacion)==true) || ("23".equals(estadoaceptacion)==true) || ("22".equals(estadoaceptacion)==true)   ){
                      
                                            
                            String[] arrayrutempresa = objEmpresa.getEmpresarut().split("-");
                            String nombredte = "ENVDTE"+arrayrutempresa[0].trim()+"F"+ String.valueOf(folio)+ "T"+codsii+".xml";
                            String pathdte = objConfig.getPathdownload()+nombredte;
                            request.getSession().setAttribute("pathdte",pathdte);
                            response.sendRedirect("datadte");
                      }else{
                          switch (estadoaceptacion){
                              
                              case "1":
                                       request.getSession().setAttribute("MENSAJE","RUT EMISOR ERRONEO");
                                       response.sendRedirect("messageview/acceptdte.jsp");
                                       break;
                               case "2":
                                       request.getSession().setAttribute("MENSAJE","NUMERO DE FOLIO ERRONEO");
                                       response.sendRedirect("messageview/acceptdte.jsp");
                                       break;
                                          
                                case "10":
                                       request.getSession().setAttribute("MENSAJE","DOCUMENTO NO RECIBIDO EN EL SII");
                                       response.sendRedirect("messageview/acceptdte.jsp");
                                       break;
                                        
                                case "18":
                                       request.getSession().setAttribute("MENSAJE","DOCUMENTO NO HA SIDO RECIBO");
                                       response.sendRedirect("messageview/acceptdte.jsp");
                                       break;
                                
                                case "20":
                                       request.getSession().setAttribute("MENSAJE","TIPO DOCUMENTO NO CEDIBLE");
                                       response.sendRedirect("messageview/acceptdte.jsp");
                                       break;
                                
                                case "21":
                                       request.getSession().setAttribute("MENSAJE","DOCUMENTO REFERENCIADO POR NOTA DE CREDITO");
                                       response.sendRedirect("messageview/acceptdte.jsp");
                                       break;
                              /*
                                case "22":
                                       request.getSession().setAttribute("MENSAJE","NO EXISTE REGISTRO DE RECLAMO O RECEPCION");
                                       response.sendRedirect("messageview/acceptdte.jsp");
                                       break;
                              */
                                 case "24":
                                       request.getSession().setAttribute("MENSAJE","DTE RECLAMADO POR RECEPTOR");
                                       response.sendRedirect("messageview/acceptdte.jsp");
                                       break;   
                                    
                              
                          }
                      }
                      
                      
                      break;
                 
            }
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(CesionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
        Logger.getLogger(CesionServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
       
}
}