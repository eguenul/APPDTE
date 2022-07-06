package com.appdte.sii.utilidades;

import com.appdte.models.DetalleDteModel;
import com.appdte.models.DteModel;
import com.appdte.models.ReferenciaModel;
import com.appdte.json.ReceptorJson;
import com.appdte.json.EmisorJson;
import com.appdte.json.IdDteJson;
import com.appdte.json.ReferenciaJson;
import com.appdte.json.DetalleDteJson;
import com.appdte.json.TotalesJson;
import com.appdte.json.DteJson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import com.appdte.sii.cl.Semilla;
import com.appdte.sii.cl.Token;
import com.appdte.sii.cl.UploadSii;
import appventas.movimientos.BlobDTE;
import com.google.gson.Gson;
import java.util.List;
import java.io.ByteArrayInputStream;


public class AppDTE {
    
    @SuppressWarnings("empty-statement")
    public  String sendDTE(int idmovimiento,String stringDTE,String certificado,String clave,String rutEnvia, boolean blReferencia) throws TransformerException, ParserConfigurationException, SAXException, IOException, Exception{
try{
    
   ConfigAppDTE objconfiguracion = new ConfigAppDTE();
   String pathdata = objconfiguracion.getPathdata();
   String pathcaf = objconfiguracion.getPathcaf();
   String pathcertificado = objconfiguracion.getPathcert()+certificado;
   /* CARGO LOS PARAMETROS DE CONFIGURACION */
   String pathdte = objconfiguracion.getPathdte();
   String urlenvironment = objconfiguracion.getPathenvironment();   
   /* ingreso el DTE en formato JSON */
             
             
   System.out.println("Reading JSON from a file");
   System.out.println("----------------------------");
  
   
  
    InputStream isjson = new ByteArrayInputStream(stringDTE.getBytes("UTF-8")); 
    BufferedReader br1 = new BufferedReader(new InputStreamReader(isjson));
  
  
    Gson gson = new Gson(); 
    DteJson objdtejson = gson.fromJson(br1, DteJson.class);
 
        
     
     /* DATOS DEL EMISOR EN JSON */
     EmisorJson objemisor = objdtejson.getEmisor();
    /* DATOS DEL RECEPTOR EN JSON */
    ReceptorJson objreceptor = objdtejson.getReceptor();
    IdDteJson iddoc = objdtejson.getIdDte();
    TotalesJson totales = objdtejson.getTotales(); 
   /* inicializar el xml */        
    DteModel objdte = new DteModel();
    ClassDteDao obj = new ClassDteDao();
    DetalleDteModel objdetalledte = new DetalleDteModel();
    /* DEFINO DATOS DEL EMISOR Y RECEPTOR 
   */
    objdte.setRutemisor(objemisor.getRutemisor());
    objdte.setTipodte(iddoc.getTipoDTE());
    objdte.setNumdte(iddoc.getNumDTE());
    objdte.setFechadte(iddoc.getFechaEmision());
    
    if(Integer.parseInt(iddoc.getTipoDTE())==52){
        objdte.setTipotraslado(iddoc.getTipotraslado());
    }
    
    
    String[] arrayrutemisor = objdte.getRutemisor().split("-");
    
    String rutemisor = arrayrutemisor[0];
    String nombredte = "DTE"+rutemisor+"F"+iddoc.getNumDTE()+"T"+iddoc.getTipoDTE();
        
    
    
    
    
    objdte.setFecharesol(objemisor.getFecharesol());
    objdte.setNumresol(objemisor.getNumresol());
    
    /* DATOS DEL EMISOR EN EL XML */
  
   objdte.setRsemisor(objemisor.getRsemisor());
   objdte.setGiroemisor(objemisor.getGiroemisor());
   objdte.setActecoemisor(objemisor.getActecoemisor());
  
    
   objdte.setCodigosii(objemisor.getCodsiisucur());
   objdte.setDiremisor(objemisor.getDiremisor());
   objdte.setCmnaemisor(objemisor.getCmnaemisor());
   objdte.setCiuemisor(objemisor.getCiuemisor());
    
    
    /* DATOS DEL RECEPTOR EN EL XML */
    objdte.setRutreceptor(objreceptor.getRutreceptor());
    objdte.setRsreceptor(objreceptor.getRsreceptor());
    objdte.setGiroreceptor(objreceptor.getGiroreceptor());
    objdte.setCmnareceptor(objreceptor.getCmnareceptor());
    objdte.setCiureceptor(objreceptor.getCiureceptor());
    objdte.setDirreceptor(objreceptor.getDirreceptor());
    objdte.setNumdte(iddoc.getNumDTE());
    
    
    /* DEFINO EL TOTAL Y TASA DE IMPUESTO */
     objdte.setMontofecto(totales.getMontoafecto());
     objdte.setMontexento(totales.getMontoexento());
     objdte.setMontoiva(totales.getMontoiva());
    objdte.setTasaiva(totales.getTasaiva());
    objdte.setMontototal(totales.getMontototal());
      
    /* INICIALIZO EL OBJETO DE DOCUMENTO */
    obj.crearXml(objdte);
    
      /* cargo los detalles */
     List<DetalleDteJson> detalle = objdtejson.getDetalleDteJson();
  
    
   Timbre objTimbre = new Timbre(pathdte,nombredte,pathdata,pathcaf);
   String auxDescripcion;
for (DetalleDteJson i :  detalle){     
  if(i.getNrolinea()==1){  
       objTimbre.setItem1(i.getNmbitem());
       auxDescripcion = i.getNmbitem();
 }
   
    
    objdetalledte.setNrolinea(i.getNrolinea());
    objdetalledte.setTpocodigo(i.getTpocodigo());
    objdetalledte.setVlrcodigo(i.getVlrcodigo());
    objdetalledte.setNmbitem(i.getNmbitem());
    objdetalledte.setDscitem(i.getNmbitem());
    objdetalledte.setQtyitem(i.getQtyitem());
    objdetalledte.setPrcitem(i.getPrcitem());
    objdetalledte.setDescuentopct(i.getDescuentopct());
    objdetalledte.setDescuentomonto(i.getDescuentomonto());
    objdetalledte.setMontoitem(i.getMontoitem());
    obj.agregaDetalle(objdetalledte);
    
    }
    
  
   ReferenciaJson referencia = objdtejson.getReferencia();
   ReferenciaModel objReferenciaModel = new ReferenciaModel();
   objReferenciaModel.setRazonref(referencia.getRazonref());
   objReferenciaModel.setNumref(referencia.getNumref());
   
   objReferenciaModel.setFecharef(referencia.getFecharef());
   objReferenciaModel.setFolioref(referencia.getFolioref());
   objReferenciaModel.setCodref(referencia.getCodref());
    
   /*     objReferenciaModel.setTpoDocRef(referencia.getTpoDocRef()); */
       
objReferenciaModel.setTpoDocRef(referencia.getTpoDocRef());
    
obj.agregaRegerencia(objReferenciaModel,blReferencia);
    
auxDescripcion = objTimbre.getItem1();
obj.guardarDocumento(nombredte,pathdte);
objTimbre.creaTimbre(objdte, auxDescripcion,rutemisor);
  
    
/* preparo el DTE para firmar */
SignDTE objFirma = new SignDTE();
objFirma.signDTE(pathdte,nombredte,pathcertificado,clave);
   
    /* ahora envuelvo el DTE en un sobre electrónico */
   
EnvioDTE objenvio = new EnvioDTE();
objenvio.generaEnvio(objdte,nombredte,pathdte,rutEnvia);
   
SignENVDTE objFirmaENV = new SignENVDTE();
objFirmaENV.signENVDTE(pathdte,nombredte,pathcertificado,clave);
    
BlobDTE objblob = new BlobDTE();
    
objblob.registrarXML(idmovimiento,"ENVDTE"+rutemisor+"F"+iddoc.getNumDTE()+"T"+iddoc.getTipoDTE()+".xml");



 /* OBTENGO LA SEMILLA PARA AUTENTIFICARME AL SII   */ 
 
  Semilla objsemilla = new Semilla();
  String valorsemilla = new String(); 
  
  if(!"0".equals(valorsemilla)){
 valorsemilla =  objsemilla.getSeed(urlenvironment);
 
 Token objtoken = new Token(urlenvironment);
 String valortoken =  objtoken.getToken(valorsemilla,pathcertificado,clave,nombredte);


 UploadSii objupload = new UploadSii(urlenvironment);

 String valortrackid = "0";
 valortrackid = objupload.uploadSii(valortoken,pathdte,nombredte,objdte.getRutemisor(),rutEnvia);
 
return valortrackid;

  }else{
      return "0";
  }
}

catch(Exception ex){
    return "0";
}

    }

   
}
