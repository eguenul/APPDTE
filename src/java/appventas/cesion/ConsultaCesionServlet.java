package appventas.cesion;


import appventas.usuarios.Usuario;
import appventas.usuarios.UsuarioModel;
import com.appdte.sii.funcionesws.ConsultaCesion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.xml.sax.SAXException;

public class ConsultaCesionServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
   
        try {
            ConsultaCesion objConsultaCesion = new ConsultaCesion();
          
             String login = (String) request.getSession().getAttribute("login");
           
             UsuarioModel objUsuarioModel = new UsuarioModel();
           
             Usuario objUsuario = objUsuarioModel.getUsuario(login);
           
             String trackid = request.getParameter("TRACKID");
            
             String clave = objUsuario.getPassword();
            
             String estadocesion = objConsultaCesion.getEstEnvioCesion(login.trim(), clave.trim(), trackid);
             
             PrintWriter out = response.getWriter();           
           
             String mensaje ="";
             switch (estadocesion){
                 
                 
                 case "UPL":
                      mensaje = "<div class=\"alert alert-success\"> <strong>UPL: </strong>ENVIO DE EN PROCESO DE UPLOAD</div>";
                       out.print(mensaje);         
                         
                 break;
                 
                 
                  case "RCP":
                       mensaje = "<div class=\"alert alert-success\"> <strong>RCP: </strong>ENVIO RECIBIDO</div>";
                       out.print(mensaje);         
                     
                         
                 break;
                 
                  case "RSC":
                       mensaje = "<div class=\"alert alert-danger\"> <strong>RSC: </strong>RECHAZADO POR ERROR EN SCHEMA</div>";
                       out.print(mensaje);         
                     
                  break;
                  
                  
                  case "SOK":
                       mensaje = "<div class=\"alert alert-success\"> <strong>SOK: </strong>SCHEMA VALIDADO</div>";
                       out.print(mensaje);         
                     
                      
                  break;
                  
                  
                  case "RFS":
                       mensaje = "<div class=\"alert alert-danger\"> <strong>RFS: </strong>RECHAZADO POR FIRMA DE SOBRE</div>";
                       out.print(mensaje);         
                     
                      
                  break;
                  
                  
                  case "FSO":
                       mensaje = "<div class=\"alert alert-success\"> <strong>FSO: </strong>FIRMA DE SOBRE VALIDADA</div>";
                       out.print(mensaje);         
                     
                  break;
                  
                  
                  case "RCR":
                        mensaje = "<div class=\"alert alert-danger\"> <strong>RCR: </strong>ERROR DE CARATULA</div>";
                       out.print(mensaje);         
                     
                  break;
                  
                  
                  case "COK":
                       mensaje = "<div class=\"alert alert-success\"> <strong>COK: </strong>CARATULA OK</div>";
                       out.print(mensaje);         
                     
                   break;
                   
                  case "VDC":
                       mensaje = "<div class=\"alert alert-warning\"> <strong>VDC: </strong>VALIDACION DE DOCUMENTO</div>";
                       out.print(mensaje);         
                     
                  break;
                  
                  case "RDC":
                        mensaje = "<div class=\"alert alert-warning\"> <strong>RDC: </strong>DOCUMENTO INVALIDO</div>";
                       out.print(mensaje);         
                     
                  break;
                  
                  
                  case "VCS":
                      mensaje = "<div class=\"alert alert-warning\"> <strong>VCS: </strong>VALIDACION DE CESIONES</div>";
                       out.print(mensaje);         
                     
                  break;
                  
                  case "RCS":
                        mensaje = "<div class=\"alert alert-danger\"> <strong>RCS: </strong>CESION INVALIDA</div>";
                       out.print(mensaje);         
                     
                  break;
                  
                  
                  
                  case "EOK":
                      mensaje = "<div class=\"alert alert-success\"> <strong>EOK: </strong>ENVIO OK</div>";
                       out.print(mensaje);         
                     
                  break;
                  
                  case "EAN":
                       mensaje = "<div class=\"alert alert-warning\"> <strong>EAN: </strong>ENVIO ANULADO</div>";
                       out.print(mensaje);         
                     
                  break;
                  
                  
                  
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
             }
             
             
             
        } catch (SAXException ex) {
            Logger.getLogger(ConsultaCesionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaCesionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
}
