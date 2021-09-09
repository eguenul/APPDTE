/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.cesion;

import com.appdte.json.AECjson;
import com.appdte.json.CedenteJson;
import com.appdte.json.CesionJson;
import com.appdte.json.CesionarioJson;
import com.appdte.json.IdDteCesionjson;
import com.appdte.json.RutAutorizadojson;
import com.appdte.sii.cesion.MainCesion;
import com.appdte.sii.utilidades.ConfigClass;
import appventas.documento.DocumentoModel;
import appventas.empresa.Empresa;
import appventas.empresa.EmpresaModel;
import appventas.report.Report;
import appventas.usuarios.Usuario;
import appventas.usuarios.UsuarioModel;
import com.appdte.sii.funcionesws.ConsultaAceptacion;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class uploadCesionServlet extends HttpServlet {
    
    
    
 @Override
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
      
     try {
          boolean flag_cesion = true;
         
         
         
         
         /* cargo los datos del cedente */
        
         String login = (String) request.getSession().getAttribute("login");
         
         UsuarioModel objUsuarioModel = new UsuarioModel();
         Usuario objUsuario = objUsuarioModel.getUsuario(login);
         
        String fechacesion = request.getParameter("FECHACESION");
         
         
         int empresaid = Integer.parseInt(request.getParameter("EmpresaId"));
         int cesionarioid = Integer.parseInt(request.getParameter("CesionarioId"));
         CesionModel objCesionModel = new CesionModel(empresaid);
         int correlativo = objCesionModel.buscaCorrelativo();
      
         
         EmpresaModel objEmpresaModel = new EmpresaModel();
         Empresa objEmpresa = objEmpresaModel.getData(empresaid);
         
         
         String empresarut = objEmpresa.getEmpresarut();
         
         
         
         
         
         
         
        String pathdte = (String) request.getSession().getAttribute("pathdte");
        
         
         CedenteJson objCedente = new CedenteJson();
         
         objCedente.setRut(request.getParameter("RUTCEDENTE").trim());
         objCedente.setRazonsocial(request.getParameter("RZSCCEDENTE"));
         objCedente.setDireccion(request.getParameter("DIRCEDENTE"));
        
     
         objCedente.setEmail(request.getParameter("EMAILAUTORIZADO"));
         
         RutAutorizadojson objrutautorizado = new RutAutorizadojson();
         objrutautorizado.setRut(request.getParameter("RUTAUTORIZADO"));
         objrutautorizado.setNombre(request.getParameter("NOMBREAUTORIZADO"));
         
         
         objCedente.setRutautorizado(objrutautorizado);
         
         /* cargo los datos del cesionario */
         CesionarioJson objCesionario = new CesionarioJson();
         
         
         objCesionario.setRut(request.getParameter("RUTCESIONARIO"));
         objCesionario.setRazonsocial(request.getParameter("RZSCCESIONARIO"));
         objCesionario.setDireccion(request.getParameter("DIRCESIONARIO"));
         objCesionario.setEmail(request.getParameter("EMAILCESIONARIO"));
         
         
         /* datos del documento a cesionar */
         IdDteCesionjson objiddtecesion =new IdDteCesionjson();
         
         objiddtecesion.setFchemis(request.getParameter("FECHAEMIS"));
         objiddtecesion.setFolio(request.getParameter("FOLIO"));
         objiddtecesion.setRutemisor(request.getParameter("RUTEMISOR"));
         objiddtecesion.setMnttotal(request.getParameter("MNTTOTAL"));
         objiddtecesion.setTipodte(request.getParameter("TIPODTE"));
         objiddtecesion.setRutreceptor(request.getParameter("RUTRECEPTOR"));
         objiddtecesion.setRsreceptor(request.getParameter("RSRECEPTOR"));
         
         
         
         /* escribo los datos de la cesion */
         CesionJson objCesion = new CesionJson();
         
         objCesion.setCedente(objCedente);
         objCesion.setCesionario(objCesionario);
         objCesion.setIddte(objiddtecesion);
         objCesion.setSeqcesion(request.getParameter("SECUENCIA"));
         objCesion.setUltimovencimiento(request.getParameter("ULTIMOVENCIMIENTO"));
         objCesion.setMontocesion(request.getParameter("MONTOCEDIDO"));
         objCesion.setRsreceptor(objiddtecesion.getRsreceptor());
         
         /* ecribo caratula del archivo de cesion */
         
         AECjson objAEC = new AECjson();
        
        
         objAEC.setRutcedente(objCedente.getRut().trim());
         objAEC.setRutcesionario(objCesionario.getRut());
         
         
         
         
         /* preparao la creaci�n de los archivos xml y su firma */
         ArrayList<CesionJson> arraycesion = new ArrayList<>();
         arraycesion.add(objCesion);
         
         objAEC.setCesiones(arraycesion);
         final Gson gson = new Gson();
         final String stringJSON = gson.toJson(objAEC);
         System.out.print(stringJSON);
         
       
         
         
         String TIPOCESION = (String) request.getSession().getAttribute("TIPOCESION");
           
      /* objConsultaCesion.getEstEnvioCesion(objUsuario.getLogin(), objUsuario.getPassword(), trackid);
       */
        /* si cargo un dte externo hago previa consulta */
         String[] arrayrutemisor = objiddtecesion.getRutemisor().trim().split("-");
      
         
         
   /*       String auxlogin = (String) request.getSession().getAttribute("login");
    */
         ConsultaAceptacion objConsultaAceptacion = new ConsultaAceptacion();
                     
                       
                      objConsultaAceptacion.setRutEmisor(arrayrutemisor[0]);
                      objConsultaAceptacion.setDvEmisor(arrayrutemisor[1]);
                      objConsultaAceptacion.setFolio(objiddtecesion.getFolio());
                      objConsultaAceptacion.setTipoDoc( objiddtecesion.getTipodte());
                        
                     String estadoaceptacion = objConsultaAceptacion.consultarDocDteCedible(objUsuario.getLogin(), objUsuario.getPassword());
                 
            if(TIPOCESION=="AEC"){
                      if (("25".equals(estadoaceptacion)==true) || ("23".equals(estadoaceptacion)==true)  || ("22".equals(estadoaceptacion)==true) ){
                      
                                            
                           flag_cesion = true;
                      }else{
                          flag_cesion = false;
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
            }
                     
         
         
         
         
         
         
         
                
     if (flag_cesion == true){   
         
        MainCesion objmainAEC = new MainCesion(objUsuario.getLogin(),objUsuario.getPassword(), correlativo,empresarut);
        String trackid =  objmainAEC.sendCesion(stringJSON, pathdte, objCedente.getRut(),objCedente.getEmail(),TIPOCESION);
         
       
          /* guardo la cesion en la base de datos */
       
         Object[] objcesion = new Object[14];
         objcesion[0] = empresaid;
         objcesion[1] = cesionarioid;
         
         objcesion[2] = objiddtecesion.getFchemis();
         objcesion[3] = objiddtecesion.getFolio();
         objcesion[4] = objiddtecesion.getRutemisor();
         objcesion[5] = objiddtecesion.getMnttotal();
         
         DocumentoModel objdocumentomodel = new DocumentoModel();
         
         int tipodocumento  = objdocumentomodel.getId(objiddtecesion.getTipodte());
         
         
         objcesion[6] = tipodocumento;
         
         objcesion[7] = objiddtecesion.getRutreceptor();
         objcesion[8] = objCesion.getUltimovencimiento();
         objcesion[9] = objCesion.getMontocesion();
         objcesion[10] = trackid;
         objcesion[12] = objCesionModel.buscaCorrelativo();
         int auxnumcesionblob = objCesionModel.buscaCorrelativo();
         objcesion[13] = fechacesion;
         objCesionModel.addCesion(objcesion, login);
        objCesionModel.updateCorrelativo();
         
        
        BlobCESION  objBlobCesion = new BlobCESION();
         
        String[] arrayrutempresa = objCedente.getRut().trim().split("-");
        String nombrearchivo = "AEC"+ arrayrutempresa[0]+"F"+String.valueOf(auxnumcesionblob)+".xml";
        
        int cesionid = objCesionModel.getId(auxnumcesionblob);
         
       objBlobCesion.registrarXML(cesionid, nombrearchivo);
     /*
          File ficheroaec = new File(nombrearchivo);         
if(ficheroaec.delete()){
   System.out.println("El fichero ha sido borrado satisfactoriamente");
}
else{
   System.out.println("El fichero no puede ser borrado");
}     */  
   
      File ficherodte = new File(pathdte);
 
      if(ficherodte.delete()){
          System.out.println("El fichero ha sido borrado satisfactoriamente");
      }else{
          
          System.out.println("El fichero no puede ser borrado");
      }
 
        
        
     
       
       
       
        
       
     
     String tipocesion = (String) request.getSession().getAttribute("TIPOCESION");
    
        if("DTE".equals(tipocesion)==true  ){
            
            int idmovimiento = (int) request.getSession().getAttribute("MovimientoId");
    
             objCesionModel.updateMovimiento(idmovimiento);
    
 request.getSession().setAttribute("TIPOCESION",null);
             request.getSession().setAttribute("MovimientoId",null);
       
        } 
         
         
         request.getSession().setAttribute("TRACKID",trackid);
         ConfigClass objconfig = new ConfigClass();
         
         
         
     
       File ficheroaecanterior = new File(objconfig.getPathdte()+"CESIONESANTERIORES"+login+".xml");         
if(ficheroaecanterior.delete()){
   System.out.println("El fichero ha sido borrado satisfactoriamente");
}
else{
   System.out.println("El fichero no puede ser borrado");
}           
       
         
         
         
         
         String archivosalida = "cesion"+(String) request.getSession().getAttribute("login");
         Report objReport = new Report("cesion",objconfig.getPathdownload(),archivosalida+".pdf");
         
         
        String auxnumcesion = String.valueOf(objcesion[12]);
        objReport.setParameters("parmEmpresaId", String.valueOf(empresaid));
        objReport.setParameters("parmCesionNum",auxnumcesion);
        objReport.showReport();
        request.getSession().setAttribute("nombredocumento",archivosalida); 
      
        response.sendRedirect("cesionview/printcesion.jsp");
       
     }
        
     } catch (TransformerException ex) {
         Logger.getLogger(uploadCesionServlet.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SAXException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | KeyStoreException | CertificateException | UnrecoverableEntryException | KeyException | MarshalException | XMLSignatureException ex) {
         Logger.getLogger(uploadCesionServlet.class.getName()).log(Level.SEVERE, null, ex);
     } catch (Exception ex) {
         Logger.getLogger(uploadCesionServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
         
      }
    
}
