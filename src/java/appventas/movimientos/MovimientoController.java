/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.movimientos;

import com.appdte.json.DetalleDteJson;
import com.appdte.json.DteJson;
import com.appdte.json.EmisorJson;
import com.appdte.json.IdDteJson;
import com.appdte.json.ReceptorJson;
import com.appdte.json.ReferenciaJson;
import com.appdte.json.TotalesJson;
import appventas.empresa.Empresa;
import appventas.empresa.EmpresaModel;
import appventas.usuarios.Usuario;
import com.appdte.sii.utilidades.AppDTE;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class MovimientoController {
     
    
 public String sendDTE(Usuario objUsuario,int empresaid, int idmovimiento) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException, Exception{
     MovimientoModel objMovimientoModel = new MovimientoModel();
     Object[] arraymovimiento = objMovimientoModel.showDocument(idmovimiento);
     IdDteJson objIdDteJson = new IdDteJson();       
     objIdDteJson.setNumDTE(String.valueOf(arraymovimiento[9]));
     objIdDteJson.setTipoDTE(String.valueOf(arraymovimiento[8]));
     objIdDteJson.setFechaEmision((String) arraymovimiento[10]);
     
     String docsiicod = (String) arraymovimiento[8];
    
   //   if(docsiicod==52){
        
    //       objIdDteJson.setTipotraslado(String.valueOf(codsiitraslado));
     //   }

     EmisorJson objEmisorJson = new EmisorJson();       
     EmpresaModel objEmpresaModel = new EmpresaModel();    
     Empresa objEmpresa = objEmpresaModel.getData(empresaid);
     objEmisorJson.setRutemisor(objEmpresa.getEmpresarut().trim());
     objEmisorJson.setRsemisor(objEmpresa.getEmpresaraz());
     objEmisorJson.setActecoemisor(String.valueOf(objEmpresa.getEmpresaacteco()));
     objEmisorJson.setCodsiisucur(String.valueOf(objEmpresa.getSucursalsiicod()));
     objEmisorJson.setNumresol(String.valueOf(objEmpresa.getEmpresanumresol()));
     objEmisorJson.setFecharesol(objEmpresa.getEmpresafechresol());
     objEmisorJson.setCiuemisor(objEmpresa.getEmpresaciu());
     objEmisorJson.setCmnaemisor(objEmpresa.getEmpresacom());
     objEmisorJson.setGiroemisor(objEmpresa.getEmpresagir());
     objEmisorJson.setDiremisor(objEmpresa.getEmpresadir());
        
         ReceptorJson objReceptor  = new ReceptorJson();

        objReceptor.setRutreceptor((String)arraymovimiento[1]);
        objReceptor.setRsreceptor((String)arraymovimiento[2]);
        objReceptor.setGiroreceptor((String)arraymovimiento[3]);
        objReceptor.setDirreceptor((String)arraymovimiento[4]);
        objReceptor.setCmnareceptor((String)arraymovimiento[5]);
        objReceptor.setCiureceptor((String)arraymovimiento[6]);
       
       
       TotalesJson  objTotalJson = new TotalesJson();
       objTotalJson.setMontoafecto((int)arraymovimiento[11]);
       objTotalJson.setMontoexento((int)arraymovimiento[12]);
       objTotalJson.setMontoiva((int)arraymovimiento[13]);
        
       ArrayList<Object[]> arraydetalle = objMovimientoModel.showDetails(idmovimiento);
      
   
    int auxlinea = 1;
    
    ArrayList<DetalleDteJson> arraydetallejson = new ArrayList<>();
    for(Object[] i: arraydetalle){
    
         DetalleDteJson objDetalleJson = new DetalleDteJson();
         objDetalleJson.setNrolinea(auxlinea);
         objDetalleJson.setVlrcodigo(String.valueOf(i[0]));
         objDetalleJson.setNmbitem((String) i[1]);
         objDetalleJson.setQtyitem((int) i[2]);
         objDetalleJson.setPrcitem((int) i[3]);
         objDetalleJson.setDescuentomonto(0);
         objDetalleJson.setMontoitem((int) i[6]);
         objDetalleJson.setTpocodigo("INT");
         arraydetallejson.add(objDetalleJson);
         auxlinea++;
    }   
        
       ReferenciaJson objReferencia = new ReferenciaJson(); 
      
       
       objReferencia.setRazonref((String) arraymovimiento[21]);
       objReferencia.setNumref(1);
       
       if("61".equals(docsiicod)){
           objReferencia.setCodref(1);
       }
       
       
       int codsiiref = (int) arraymovimiento[20];
       
       objReferencia.setTpoDocRef(codsiiref);
       objReferencia.setFecharef((String) arraymovimiento[16]);
       
       
            
        switch (codsiiref){
        
         case 33:
             objReferencia.setFolioref((int) arraymovimiento[19]);
            break;
            
        case 34:
             objReferencia.setFolioref((int) arraymovimiento[22]);
            break;
            
         case 52:
             objReferencia.setFolioref((int) arraymovimiento[18]);
            break;
         case 801:
            objReferencia.setFolioref((int) arraymovimiento[17]);
            break;
            
        }
        
                
                
       objTotalJson.setMontototal((int) arraymovimiento[14]);
       objTotalJson.setTasaiva(19);
       
       
       DteJson objdtejson = new DteJson();
       objdtejson.setIdDte(objIdDteJson);
       objdtejson.setEmisor(objEmisorJson);
       objdtejson.setReceptor(objReceptor);
       objdtejson.setTotales(objTotalJson);
       objdtejson.setDetalleDteJson(arraydetallejson);
       
       objdtejson.setReferencia(objReferencia);
       
       
  objdtejson.setNumdte(String.valueOf(arraymovimiento[9]));
 objdtejson.setTipodte(String.valueOf(arraymovimiento[8]));
        final Gson gson = new Gson();
	final String stringJSON = gson.toJson(objdtejson);   
        System.out.print(stringJSON);

        AppDTE objfirma = new AppDTE("eguenul","maullin.sii.cl");
   

      String trackid = objfirma.sendDTE(stringJSON, objUsuario.getLogin(),objUsuario.getPassword(), objUsuario.getRut(), false);
        
        return trackid;
            
 }
}
