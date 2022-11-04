/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.consultassii;

import appventas.documento.Documento;
import appventas.documento.DocumentoModel;
import appventas.empresa.Empresa;
import appventas.empresa.EmpresaModel;
import appventas.movimientos.MovimientoModel;
import appventas.movimientos.MovimientoModel2;
import appventas.usuarios.Usuario;
import appventas.usuarios.UsuarioModel;
import com.appdte.sii.funcionesws.ConsultaDTE;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class ConsultaSII extends HttpServlet {

@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        if(request.getSession().getAttribute("loginauth")==null){ 
            request.getRequestDispatcher("login").forward(request, response);
        }
        
        
        
        request.getSession().setAttribute("CABECERA", "CONSULTA ESTADO DTE");
        request.getSession().setAttribute("modulo", "consultadte");
       
        
        
        
        DocumentoModel objDocumentoModel = new DocumentoModel();
        List<Documento> arraylistdocumento = objDocumentoModel.listDocuments();
        
      request.getSession().setAttribute("listdocumento",arraylistdocumento);
        
        
        getServletConfig().getServletContext().getRequestDispatcher("/consultasiiview/listdocument.jsp").forward(request,response);
    } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
        Logger.getLogger(ConsultaSII.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
    
    
@Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    try {
        String acc = request.getParameter("ACC");
        int empresaid = (int) request.getSession().getAttribute("empresaid");
        
        MovimientoModel2 objrecordset = new MovimientoModel2();
                
              
        
        
        switch(acc){
            
            case "BUSCAR":
                
        int tipodocumentoid = Integer.parseInt(request.getParameter("TipoDocumento"));
        int numdoc = Integer.parseInt(request.getParameter("NumDoc"));
                if (objrecordset.flagbuscaDoc2(numdoc, tipodocumentoid, empresaid)==true){
                    
                  ArrayList<Object[]> arraylistdoc = objrecordset.buscaDoc2(numdoc, tipodocumentoid, empresaid);
                  request.getSession().setAttribute("arraylistdoc", arraylistdoc);
                  getServletConfig().getServletContext().getRequestDispatcher("/consultasiiview/listdocument2.jsp").forward(request,response);
  
                
                }else{
            //ya podemos enviar al navegador
            try ( //Objetemos el escritor hacia el Cliente
                    PrintWriter out = response.getWriter()) {
                //ya podemos enviar al navegador
                out.println("<div class=\"alert alert-warning\">DOCUMENTO NO EXISTENTE</div>");
                //c                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     erramos el escritor
            } 
                    
                }
                break;
                
                
            case "CONSULTADTE":
                
                
                      String auxlogin = (String) request.getSession().getAttribute("login");
                      UsuarioModel objUsuarioModel = new UsuarioModel();
                      Usuario objUsuario = objUsuarioModel.getUsuario(auxlogin);
                      
                    
                      String login= objUsuario.getLogin();
                      String clave= objUsuario.getPassword();
                      String rutusuario = objUsuario.getRut();
                   
                      int iddoc = Integer.parseInt(request.getParameter("MovimientoId"));
                
                          MovimientoModel objMovModel = new MovimientoModel();
                      Object[] objdoc = objMovModel.showDocument(iddoc);
                      
                      int folio = (int) objdoc[9];
                      String codsii = (String) objdoc[8];
                      
                      Empresa objEmpresa = new Empresa();
                      EmpresaModel objEmpresaModel = new EmpresaModel();
                      objEmpresa = objEmpresaModel.getData(empresaid);
                      String auxrutempresa = objEmpresa.getEmpresarut().trim();
                      String arrayrutempresa[] = auxrutempresa.split("-");
                       
                      String arrayrutusuario[] = rutusuario.trim().split("-");
                

                      ConsultaDTE objConsultaDTE = new ConsultaDTE();
                
                      objConsultaDTE.setFolioDte(String.valueOf(folio));
                      objConsultaDTE.setRutCompania(arrayrutempresa[0].trim());
                      objConsultaDTE.setDvCompania(arrayrutempresa[1]);
                      
                      objConsultaDTE.setRutConsultante(arrayrutusuario[0]);
                      objConsultaDTE.setDvConsultante(arrayrutusuario[1]);
                      
                      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                      
                      
                      // Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.
                  
                      String fechaTexto = (String) objdoc[10];
                      
                      Date d1 = formatter.parse(fechaTexto);
                      
                      formatter.applyPattern("ddMMyyyy"); 
                      
                      
                      
                      objConsultaDTE.setFechaEmisionDte(formatter.format(d1));
                      objConsultaDTE.setMontoDte(String.valueOf((int)objdoc[14]));
                      
                      
                      objConsultaDTE.setTipoDte(codsii);
                      
                      String auxrutreceptor = (String)objdoc[1];
                      
                      String arrayrutreceptor[] = auxrutreceptor.trim().split("-");
                      
                      objConsultaDTE.setRutReceptor(arrayrutreceptor[0]);
                      objConsultaDTE.setDvReceptor(arrayrutreceptor[1]);
                      
                      
                      
                     String estado = objConsultaDTE.getEstDte(login, clave);
                    PrintWriter out=response.getWriter();
                    String mensaje="";      
                     switch (estado){
                         case "DOK":
                             mensaje = "<div class=\"alert alert-success\"> <strong>DOK:</strong> DOCUMENTO RECIBIDO POR EL SII</div>";
                            out.println(mensaje);
                             
                         break;
                         
                         case "DNK":
                              mensaje = "<div class=\"alert alert-warning\"> <strong>DNK:</strong> DOCUMENTO RECIBIDO POR EL SII, PERO DATOS NO COINCIDEN</div>";
                            out.println(mensaje);
                             
                             
                         break;
                         
                         
                         
                         case "FAU":
                               mensaje = "<div class=\"alert alert-warning\"> <strong>FAU:</strong> DOCUMENTO NO RECIBIDO POR EL SII</div>";
                            out.println(mensaje);
                             
                            
                             
                         break;
                         
                         
                          case "FNA":
                              mensaje = "<div class=\"alert alert-warning\"> <strong>FNA:</strong> DOCUMENTO NO AUTORIZADO</div>";
                            out.println(mensaje);
                             
                             
                         break;
                         
                         
                         case "FAN":
                              mensaje = "<div class=\"alert alert-warning\"> <strong>FAN:</strong> DOCUMENTO ANULADO</div>";
                            out.println(mensaje);
                             
                             
                         break;
                         
                         
                         case "EMP":
                             
                              mensaje = "<div class=\"alert alert-danger\"> <strong>EMP:</strong>EMPRESA NO AUTORIZADA A EMITIR DOCUMENTOS TRIBUTARIOS ELECTRONICOS</div>";
                            out.println(mensaje);
                             
                         break;
                         
                         
                         case "TMD":
                              mensaje = "<div class=\"alert alert-warning\"> <strong>TMD:</strong>EISTE NOTA DE DEBITO QUE MODIFICA TEXTO DEL DOCUMENTO</div>";
                            out.println(mensaje);
                             
                             
                         break;
                         
                         case "TMC":
                              mensaje = "<div class=\"alert alert-warning\"> <strong>TMC:</strong>EXISTE NOTA DE CREDITO QUE MODIFICA TEXTO DEL DOCUMENTO</div>";
                            out.println(mensaje);
                             
                             
                         break;
                         
                         case "MMD":
                                mensaje = "<div class=\"alert alert-warning\"> <strong>MMD:</strong>EXISTE NOTA DE DEBITO QUE MODIFICA MONTO DEL DOCUMENTO</div>";
                            out.println(mensaje);
                           
                             
                         break;
                         
                         case "MMC":
                              mensaje = "<div class=\"alert alert-warning\"> <strong>MMC:</strong>EXISTE NOTA DE CREDITO QUE MODIFICA MONTO DEL DOCUMENTO</div>";
                            out.println(mensaje);
                           
                             
                         break;
                         
                         case "AND":
                              mensaje = "<div class=\"alert alert-warning\"> <strong>AND:</strong>EXISTE NOTA DE DEBITO QUE ANULA EL DOCUMENTO</div>";
                            out.println(mensaje);
                           
                             
                         break;
                         
                         case "ANC":
                              mensaje = "<div class=\"alert alert-warning\"> <strong>ANC:</strong>EXISTE NOTA DE CREDITO QUE ANULA EL DOCUMENTO</div>";
                            out.println(mensaje);
                             
                             
                         break;
                         
                       
                         
                     }
                   
                   
                   
                   
                   
                   
                break;
                
                
                
                                
                
                
        }
    } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException | IOException | NumberFormatException | ServletException ex) {
        Logger.getLogger(ConsultaSII.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(ConsultaSII.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    
    
    }
}
