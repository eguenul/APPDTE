package appventas.cesion;

import appventas.cesionario.Cesionario;
import appventas.cesionario.CesionarioModel;
import com.appdte.json.AECjson;
import com.appdte.json.CedenteJson;
import com.appdte.json.CesionJson;
import com.appdte.json.CesionarioJson;
import com.appdte.json.IdDteCesionjson;
import com.appdte.json.RutAutorizadojson;
import com.appdte.sii.utilidades.ConfigClass;
import appventas.empresa.Empresa;
import appventas.empresa.EmpresaModel;
/*
import appventas.funcionesws.ConsultaDTE;

*/
import appventas.usuarios.Usuario;
import appventas.usuarios.UsuarioModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class dataDTEServlet extends HttpServlet{

@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException{

   
    try {
        if(request.getSession().getAttribute("loginauth")==null){
            request.getRequestDispatcher("login").forward(request, response);
        }
        
        
        
        
        
        
        
        
        
        /* CARGO LOS DATOS DEL CLIENTE QUE VA  */
        
        
        
        ConfigClass objconfig = new ConfigClass();
        String loginuser = (String) request.getSession().getAttribute("login");
        
          File ficheroaecanterior = new File(objconfig.getPathdte()+"CESIONESANTERIORES"+loginuser+".xml");         
if(ficheroaecanterior.delete()){
   System.out.println("El fichero ha sido borrado satisfactoriamente");
}
else{
   System.out.println("El fichero no puede ser borrado");
}           
        
        
        
        
        
        
        
        String login = (String) request.getSession().getAttribute("login");
        
        UsuarioModel  objUsuarioModel = new UsuarioModel();
        Usuario objUsuario = objUsuarioModel.getUsuario(login);
        String filepath = (String) request.getSession().getAttribute("pathdte"); 
        
    
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        
        
        Document doc = docBuilder.parse(filepath.trim());
        
       
        
        
        
        
        
        
        
        DocumentBuilderFactory docFactory2 = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder2 = docFactory2.newDocumentBuilder();
        Document doc2 = docBuilder.newDocument();
       
        Element cesionesanteriores =  doc2.createElement("CESIONESANTERIORES");
       
        
        
        int secuencia = 0;
        
        
        if(doc.getElementsByTagName("Cesion").getLength()>0){
            secuencia = doc.getElementsByTagName("Cesion").getLength();
            secuencia++;
        }else{
            secuencia = 1;
        }   
        
        
        int i = 0;
        
        
        if(doc.getElementsByTagName("Cesion").getLength()>0){
            
            
            
          if(doc.getElementsByTagName("DTECedido").item(0)!=null){
            
            Node nodedteaecanterior = doc.getElementsByTagName("DTECedido").item(0);
              StringWriter auxnodecesion3  = new StringWriter();
                
                Transformer t = TransformerFactory.newInstance().newTransformer();
                t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                t.setOutputProperty(OutputKeys.INDENT, "no");
                t.transform(new DOMSource(nodedteaecanterior), new StreamResult(auxnodecesion3));
                
                Node fragmentNodeaecdte = docBuilder2.parse(new InputSource(new StringReader(auxnodecesion3.toString()))).getDocumentElement();
                fragmentNodeaecdte = doc2.importNode(fragmentNodeaecdte, true);
                cesionesanteriores.appendChild(fragmentNodeaecdte);

        }
            
            
            
            
            
            
            
            
            
            for(i=0; i<doc.getElementsByTagName("Cesion").getLength();i++){
                
                Node nodecesion = doc.getElementsByTagName("Cesion").item(i);
                
                
                StringWriter auxnodecesion  = new StringWriter();
                
                Transformer t = TransformerFactory.newInstance().newTransformer();
                t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                t.setOutputProperty(OutputKeys.INDENT, "no");
                t.transform(new DOMSource(nodecesion), new StreamResult(auxnodecesion));
                
                Node fragmentNode = docBuilder2.parse(new InputSource(new StringReader(auxnodecesion.toString()))).getDocumentElement();
                fragmentNode = doc2.importNode(fragmentNode, true);
                cesionesanteriores.appendChild(fragmentNode);
                
            }
            
            
            doc2.appendChild(cesionesanteriores);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            
            DOMSource source = new DOMSource(doc2);
            
            StreamResult result;
            result = new StreamResult(new File(objconfig.getPathdte()+"CESIONESANTERIORES"+loginuser+".xml"));
            
            
            transformer.transform(source, result);
            
            
            
            
            
        }
        
        
        
        /* datos del emisor */
        
        
         if(doc.getElementsByTagName("Folio").item(0)== null  ){
             response.sendRedirect("messageview/errorxml.html");
            
        }
        Node folio = doc.getElementsByTagName("Folio").item(0);
        
        
        
        if(doc.getElementsByTagName("TipoDTE").item(0) == null  ){
             response.sendRedirect("messageview/errorxml.html");
            
        }
        Node tipodte = doc.getElementsByTagName("TipoDTE").item(0);
       
       
        
        
        
        if(doc.getElementsByTagName("RUTEmisor").item(0) == null  ){
             response.sendRedirect("messageview/errorxml.html");
            
        }
        Node rutemisor = doc.getElementsByTagName("RUTEmisor").item(0);
        
        
        /* datos del receptor */
        
        
        
         if( doc.getElementsByTagName("RUTRecep").item(0)== null  ){
             response.sendRedirect("messageview/errorxml.html");
            
        }
        
         Node rutreceptor = doc.getElementsByTagName("RUTRecep").item(0);
       
           if( doc.getElementsByTagName("RznSocRecep").item(0)== null  ){
             response.sendRedirect("messageview/errorxml.html");
            
        }
        
         Node RzSocRecep = doc.getElementsByTagName("RznSocRecep").item(0);
       
         
         
         
         
         
         
          if( doc.getElementsByTagName("FchEmis").item(0)== null  ){
             response.sendRedirect("messageview/errorxml.html");
            
        }
          
          
          
          
          
          
          
          
          
         
         Node fechaemis = doc.getElementsByTagName("FchEmis").item(0);
         
          if( doc.getElementsByTagName("MntTotal").item(0)== null  ){
             response.sendRedirect("messageview/errorxml.html");
            
        }
         
        
        Node mnttotal =doc.getElementsByTagName("MntTotal").item(0);
        
        
        
        
        
        
        
        
        int empresaid =  (int) request.getSession().getAttribute("empresaid");
        request.getSession().setAttribute("empresaid", empresaid);
        EmpresaModel objempresaModel = new EmpresaModel();
        Empresa objempresa = new Empresa();
        
        objempresa = objempresaModel.getData(empresaid);
        
        
        
        /* consulto validez del documento */
        /*
        ConsultaDTE objconsultadte = new ConsultaDTE();
        
        objconsultadte.setTipoDte(tipodte.getTextContent());
        objconsultadte.setFolioDte(folio.getTextContent());
        objconsultadte.setMontoDte(mnttotal.getTextContent());
        String[] arrayrutcompania = rutemisor.getTextContent().split("-");
        objconsultadte.setRutCompania(arrayrutcompania[0]);
        objconsultadte.setDvCompania(arrayrutcompania[1]);
        
        String[] arrayrutconsultante = objUsuario.getRut().split("-");
        objconsultadte.setRutConsultante(arrayrutconsultante[0]);
        objconsultadte.setDvConsultante(arrayrutconsultante[1]);
        
    
        objconsultadte.setFechaEmisionDte(fechaemis.getTextContent());
        
        String[] arrayrutreceptor = rutreceptor.getTextContent().split("-");
        
        
        objconsultadte.setRutReceptor(arrayrutreceptor[0]);
        objconsultadte.setDvReceptor(arrayrutreceptor[1]);
        objconsultadte.getEstDte(objUsuario.getLogin(), objUsuario.getPassword());
        
        */
        
        
        
        
        
        /* cargo los datos del cedente */
        CedenteJson objCedente = new CedenteJson();
       
        objCedente.setDireccion(objempresa.getEmpresadir()+ " - "+objempresa.getEmpresaciu());
        objCedente.setRazonsocial(objempresa.getEmpresaraz());
        
        
        objCedente.setRut(objempresa.getEmpresarut().trim());
        objCedente.setEmail(objempresa.getEmpresaema());
        
        
        
        
        RutAutorizadojson objrutautorizado = new RutAutorizadojson();
        objrutautorizado.setRut(objUsuario.getRut());
        objrutautorizado.setNombre(objUsuario.getUsuarionom()+ " "+objUsuario.getUsuarioap());
        
        
        objCedente.setRutautorizado(objrutautorizado);
        
        
        
        request.getSession().setAttribute("objCedente", objCedente);
        
        
           
        /* cargo los datos del cesionario */
        CesionarioJson objCesionario = new CesionarioJson();
        
        
        objCesionario.setRut("");
        objCesionario.setRazonsocial("");
        
        
        objCesionario.setDireccion("" +" - "+"");
        objCesionario.setEmail("");
        
        
        
        
        request.getSession().setAttribute("objCesionario", objCesionario);
        
        
        /* datos del documento a cesionar */
        
        IdDteCesionjson objiddtecesion =new IdDteCesionjson();
        
        objiddtecesion.setFchemis(fechaemis.getTextContent());
        objiddtecesion.setFolio(folio.getTextContent());
        
        
        objiddtecesion.setRutemisor(rutemisor.getTextContent().trim());
        objiddtecesion.setMnttotal(mnttotal.getTextContent());
        objiddtecesion.setTipodte(tipodte.getTextContent());
                
       if (rutreceptor.getTextContent()==null){
           
            response.sendRedirect("messageview/errorxml.html");
       }
       
        
        objiddtecesion.setRutreceptor(rutreceptor.getTextContent());
        objiddtecesion.setRsreceptor(RzSocRecep.getTextContent());
        request.getSession().setAttribute("objiddtecesion", objiddtecesion);
        request.getSession().setAttribute("secuencia", secuencia);
        
        
        
        /* escribo los datos de la cesion */
        CesionJson objCesion = new CesionJson();
        
        objCesion.setCedente(objCedente);
        objCesion.setCesionario(objCesionario);
        objCesion.setIddte(objiddtecesion);
        
        
        objCesion.setUltimovencimiento("");
        objCesion.setMontocesion(mnttotal.getTextContent());
        
        request.getSession().setAttribute("objCesion", objCesion);
        
        /* ecribo caratula del archivo de cesion */
        
        AECjson objAEC = new AECjson();
        objAEC.setFonocontacto("");
        objAEC.setMailcontacto("");
        objAEC.setRutcedente("");
        objAEC.setRutcesionario(objempresa.getEmpresarut());
        
        request.getSession().setAttribute("objAEC", objAEC);
        
        CesionarioModel objCesionarioModel = new CesionarioModel();
        
        ArrayList<Cesionario> arraylistcesionario = objCesionarioModel.listCesionario();
        
        request.getSession().setAttribute("arraylistcesionario", arraylistcesionario);
        
        request.getRequestDispatcher("/cesionview/datadte.jsp").forward(request, response);
    
    
    } catch (ParserConfigurationException | SAXException | SQLException | ClassNotFoundException ex) {
        Logger.getLogger(dataDTEServlet.class.getName()).log(Level.SEVERE, null, ex);
       response.sendRedirect("messageview/errorxml.html");
    } catch (TransformerConfigurationException ex) {
        Logger.getLogger(dataDTEServlet.class.getName()).log(Level.SEVERE, null, ex);
     response.sendRedirect("messageview/errorxml.html");
    } catch (TransformerException ex) {
        Logger.getLogger(dataDTEServlet.class.getName()).log(Level.SEVERE, null, ex);
     response.sendRedirect("messageview/errorxml.html");
    }
   
        }
        
      
    
}
   
        
         
       
            
      
        
             
                
             


