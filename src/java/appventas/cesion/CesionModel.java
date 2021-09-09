/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.cesion;

import appventas.include.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class CesionModel {
    
    int empresaid;
    public CesionModel(int empresaid){
        
        this.empresaid = empresaid;
        
        
    }
    
    
    
    
public ArrayList<Object[]> listDoc() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
String sql;    
Conexion auxconexion = new Conexion();
Connection objconexion = auxconexion.obtener();

sql= "Select DATE_FORMAT(Movimiento.MovimientoFecha,'%d-%m-%Y') as MovimientoFecha, \n"+
"TipoDocumentos.TipoDocumentoDes,TipoDocumentos.CodigoSii, \n"+ 
"Movimiento.NumDoc,Movimiento.MovimientoTotalBruto,Movimiento.MovimientoId, \n"+ 
"Movimiento.MovimientoIdentificadorEnvio, \n"+
"CliProv.CliProvRut, Empresa.EmpresaId,Movimiento.ReferenciaFlag \n"+
"from Movimiento \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Movimiento.TipoDocumentoId \n"+ 
"inner join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n"+
"inner join Empresa on Empresa.EmpresaId = CliProv.EmpresaId \n"+
"where Empresa.EmpresaId =" +String.valueOf(this.empresaid)+ " and Cesion=0 and TipoDocumentos.CodigoSii=33 and Movimiento.ReferenciaFlag=0 \n";


System.out.print(sql);
Statement stm = objconexion.createStatement();
ResultSet objrecordset = stm.executeQuery(sql);
ArrayList<Object[]> arraymovimientos = new ArrayList<>();
while(objrecordset.next()){
  Object[] objMovimiento = new Object[8];
  objMovimiento[0] = objrecordset.getString("TipoDocumentoDes");  
  objMovimiento[1] = objrecordset.getInt("NumDoc");
  objMovimiento[2] =   objrecordset.getString("MovimientoFecha");
  objMovimiento[3]  =  objrecordset.getInt("MovimientoTotalBruto");
  objMovimiento[4]  =  objrecordset.getInt("MovimientoIdentificadorEnvio");
  objMovimiento[5]  =  objrecordset.getString("CliProvRut");
  objMovimiento[6]  =  objrecordset.getString("MovimientoId");
  objMovimiento[7]  =  objrecordset.getString("CodigoSii");
  arraymovimientos.add(objMovimiento);
}
System.out.print(sql);
return arraymovimientos;
}
    
public boolean buscaFactura(int numdoc, int codsii) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
    
String sql;    
Conexion auxconexion = new Conexion();
Connection objconexion = auxconexion.obtener();

sql= "Select DATE_FORMAT(Movimiento.MovimientoFecha,'%d-%m-%Y') as MovimientoFecha, \n"+
"TipoDocumentos.TipoDocumentoDes,TipoDocumentos.CodigoSii, \n"+ 
"Movimiento.NumDoc,Movimiento.MovimientoTotalBruto,Movimiento.MovimientoId, \n"+ 
"Movimiento.MovimientoIdentificadorEnvio, \n"+
"CliProv.CliProvRut, Empresa.EmpresaId,Movimiento.ReferenciaFlag \n"+
"from Movimiento \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Movimiento.TipoDocumentoId \n"+ 
"inner join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n"+
"inner join Empresa on Empresa.EmpresaId = CliProv.EmpresaId \n"+
"where Empresa.EmpresaId =" +String.valueOf(this.empresaid)+  " and Cesion=0 and TipoDocumentos.CodigoSii="+ String.valueOf(codsii) +" and Movimiento.ReferenciaFlag=0  and \n"+
 "Movimiento.NumDoc=" + String.valueOf(numdoc);

Statement stm = objconexion.createStatement();
ResultSet objrecordset = stm.executeQuery(sql);
return objrecordset.next();
  
}
    



    
public ArrayList<Object[]> showFactura(int numdoc, int codsii) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
String sql;    
Conexion auxconexion = new Conexion();
Connection objconexion = auxconexion.obtener();

sql= "Select DATE_FORMAT(Movimiento.MovimientoFecha,'%d-%m-%Y') as MovimientoFecha, \n"+
"TipoDocumentos.TipoDocumentoDes,TipoDocumentos.CodigoSii, \n"+ 
"Movimiento.NumDoc,Movimiento.MovimientoTotalBruto,Movimiento.MovimientoId, \n"+ 
"Movimiento.MovimientoIdentificadorEnvio, \n"+
"CliProv.CliProvRut, Empresa.EmpresaId,Movimiento.ReferenciaFlag \n"+
"from Movimiento \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Movimiento.TipoDocumentoId \n"+ 
"inner join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n"+
"inner join Empresa on Empresa.EmpresaId = CliProv.EmpresaId \n"+
"where Empresa.EmpresaId =" +String.valueOf(this.empresaid)+ " and Cesion=0 and TipoDocumentos.CodigoSii=" + String.valueOf(codsii) +" and Movimiento.ReferenciaFlag=0 \n"+
"and Movimiento.NumDoc="+String.valueOf(numdoc);


System.out.print(sql);
Statement stm = objconexion.createStatement();
ResultSet objrecordset = stm.executeQuery(sql);
ArrayList<Object[]> arraymovimientos = new ArrayList<>();
while(objrecordset.next()){
  Object[] objMovimiento = new Object[8];
  objMovimiento[0] = objrecordset.getString("TipoDocumentoDes");  
  objMovimiento[1] = objrecordset.getInt("NumDoc");
  objMovimiento[2] =   objrecordset.getString("MovimientoFecha");
  objMovimiento[3]  =  objrecordset.getInt("MovimientoTotalBruto");
  objMovimiento[4]  =  objrecordset.getInt("MovimientoIdentificadorEnvio");
  objMovimiento[5]  =  objrecordset.getString("CliProvRut");
  objMovimiento[6]  =  objrecordset.getString("MovimientoId");
  objMovimiento[7]  =  objrecordset.getString("CodigoSii");
  arraymovimientos.add(objMovimiento);
}
System.out.print(sql);
return arraymovimientos;
}


public void addCesion( Object[]   objcesion,String login ){
        try {
            String sql = null;
            Conexion auxconexion = new Conexion();
            Connection objconexion = auxconexion.obtener();
            
            
         int auxempresaid = this.empresaid;
         int cesionarioid = (int) objcesion[1];
         String fechaemision  = (String) objcesion[2];
         int folio = Integer.parseInt((String)  objcesion[3]);
         String rutemisor = (String) objcesion[4];
         int mnttotal = Integer.parseInt((String) objcesion[5]);
         int tipodocumento = (int) objcesion[6]; /* tipodocumento */
         String rutreceptor = (String) objcesion[7];
         String ultimovencimiento = (String) objcesion[8];
         int montocesion = Integer.parseInt((String) objcesion[9]);
         String trackid  = (String) objcesion[10];
         int numcesion = (int)  objcesion[12]; 
         String cesionfecha = (String) objcesion[13];
         sql = "insert into Cesiones (CesionFecha, CesionNum,EmpresaId,CesionarioId,MontoTotal,MontoCedido, FechaEmisionDoc, \n"+
               "RUTEMISOR,RUTRECEPTOR,TipoDocumentoId,UltimoVencimiento,NumDoc,UsuarioLogin,TrackID) \n"+
               "values('" + cesionfecha +"',"+String.valueOf(numcesion)+"," + String.valueOf(auxempresaid)+","+String.valueOf(cesionarioid)+", \n"+ 
                String.valueOf(mnttotal)+","+String.valueOf(montocesion)+",'"+fechaemision+"', \n"+ 
                "'"+rutemisor+"'"+",'"+rutreceptor+"',"+ String.valueOf(tipodocumento)  +",'"+ultimovencimiento+"',"+String.valueOf(folio)+ 
                ",'"+ login + "'," + trackid+")";  
            System.out.print(sql);
            Statement stm = objconexion.createStatement();
            stm.execute(sql);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(CesionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public int buscaCorrelativo() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
    
    String sql = "Select Cesion from Correlativo where EmpresaId="+ String.valueOf(this.empresaid);
            Conexion auxconexion = new Conexion();
            Connection objconexion = auxconexion.obtener();
            Statement stm = objconexion.createStatement();
            ResultSet objrecordset = stm.executeQuery(sql);
            int correlativo = 0;
            
            while (objrecordset.next()){
            correlativo = objrecordset.getInt("Cesion");
          }
            return correlativo;
    
}

public void  updateCorrelativo() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
    
    String sql = "Update Correlativo set Cesion=Cesion+1 where EmpresaId="+ String.valueOf(this.empresaid);
    Conexion auxconexion = new Conexion();
    Connection objconexion = auxconexion.obtener();
    Statement stm = objconexion.createStatement();
    stm.execute(sql);
    
}


public ArrayList<Object[]> buscaCesion(int numcesion) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
 Object [] objectcesion = new Object[9];
    
 
 String sql = 
 
"Select Empresa.*, Cesiones.*,DATE_FORMAT(Cesiones.CesionFecha,'%d/%m/%Y') as FechaCesion, TipoDocumentos.* from Cesiones \n"+
"inner join Empresa on Empresa.EmpresaId = Cesiones.EmpresaId \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Cesiones.TipodocumentoId \n"+

"where Empresa.EmpresaId =" + String.valueOf(this.empresaid) + " and CesionNum =" + String.valueOf(numcesion);
 System.out.print(sql);
 Conexion auxconexion = new Conexion();
  Connection objconexion = auxconexion.obtener();
  Statement stm = objconexion.createStatement();
  ResultSet objrecordset =  stm.executeQuery(sql);
  /*
    <th>NRO CESION</th>
       <th>FECHA CESION</th>
       <th>TIPO DOCUMENTO</th>
       <th>NRO DOCUMENTO</th>
       <th>RUT EMISOR</th>
       <th>RUT RECEPTOR</th>
       <th>MONTO CEDIDO</th>
       <th>MONTO TOTAL</th>
        <th>TRACKID</th>
  */
  ArrayList<Object[]> arraycesion = new ArrayList<>();
  
  
   while(objrecordset.next()){
      objectcesion[0] = objrecordset.getInt("CesionNum");
      objectcesion[1] = objrecordset.getString("FechaCesion");
      objectcesion[3] = objrecordset.getString("TipoDocumentoDes");
      objectcesion[2] = objrecordset.getInt("NumDoc");
      objectcesion[4] = objrecordset.getString("RUTEMISOR");
      objectcesion[5] = objrecordset.getString("RUTRECEPTOR");
      objectcesion[6]  = objrecordset.getInt("MontoTotal");
      objectcesion[7]  = objrecordset.getInt("MontoCedido");
      objectcesion[8]  = objrecordset.getInt("TrackID");
    
     arraycesion.add(objectcesion);
   }
   return arraycesion;
}



   
 

public int  conteoCesion(String fechadesde, String fechahasta,int empresaid) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
    
 String fecha1 = fechadesde;
 String fecha2 = fechahasta;


String sql ="select count(*) as NroRegistros  \n"+
"from Cesiones \n";
if(fecha1.equals(fecha2)){
    sql= sql+"where Cesiones.EmpresaId="+String.valueOf(empresaid) + " and CesionFecha='"+fecha1+"'";
}else{

sql= sql+"where CesionFecha between DATE_FORMAT('"+fecha1+"','%Y-%m-%d') and" +  " DATE_FORMAT('"+fecha2+"','%Y-%m-%d') and Cesiones.EmpresaId="+String.valueOf(empresaid);   
}
   Conexion auxconexion = new Conexion();
  Connection objconexion = auxconexion.obtener();
  Statement stm = objconexion.createStatement();
  ResultSet objrecordset =  stm.executeQuery(sql);
      int nroregistro = 0;
      while(objrecordset.next()){
       nroregistro =  objrecordset.getInt("NroRegistros");
      }  
    return nroregistro;
    
}


public ArrayList<Object[]> listFecha(String fechadesde, String fechahasta,int empresaid,int indice) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
String sql;    

 
 String fecha1 = fechadesde;


 
 String fecha2 = fechahasta;


sql = "Select  Cesiones.*, TipoDocumentos.*,DATE_FORMAT(Cesiones.CesionFecha,'%d/%m/%Y') as FechaCesion  from Cesiones \n" +
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Cesiones.TipoDocumentoId \n";



if(fecha1.equals(fecha2)){
    sql= sql+" where Cesiones.EmpresaId="+ String.valueOf(empresaid)+" and CesionFecha='"+fecha1+"' limit "+String.valueOf(indice)+",10";
}else{

sql= sql+"where CesionFecha between DATE_FORMAT('"+fecha1+"','%Y-%m-%d') and" +  " DATE_FORMAT('"+fecha2+"','%Y-%m-%d') and Cesiones.EmpresaId="+String.valueOf(empresaid)+" limit "+String.valueOf(indice)+",10";   
}

System.out.print(sql);
Conexion auxconexion = new Conexion();
Connection objconexion = auxconexion.obtener();
 Statement stm = objconexion.createStatement();
  ResultSet objrecordset =  stm.executeQuery(sql);
    
ArrayList<Object[]> arraycesion = new ArrayList<>();
while(objrecordset.next()){
     Object [] objectcesion = new Object[9];
      objectcesion[0] = objrecordset.getInt("CesionNum");
      objectcesion[1] = objrecordset.getString("FechaCesion");
      objectcesion[3] = objrecordset.getString("TipoDocumentoDes");
      objectcesion[2] = objrecordset.getInt("NumDoc");
      objectcesion[4] = objrecordset.getString("RUTEMISOR");
      objectcesion[5] = objrecordset.getString("RUTRECEPTOR");
      objectcesion[6]  = objrecordset.getInt("MontoTotal");
      objectcesion[7]  = objrecordset.getInt("MontoCedido");
      objectcesion[8]  = objrecordset.getInt("TrackID");
      arraycesion.add(objectcesion);
}
System.out.print(sql);
return arraycesion;
}

   
public int getId(int cesionnum) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
    int cesionid = 0;
    String sql = null;
    Conexion auxconexion = new Conexion();
    Connection objconexion = auxconexion.obtener();
    Statement stm = objconexion.createStatement();
    
    
    sql = "Select * from Cesiones where CesionNum=" + String.valueOf(cesionnum)+"\n"+
    " and EmpresaId=" + String.valueOf(this.empresaid);        
    
    
    ResultSet objrecordset =  stm.executeQuery(sql);
    
    while(objrecordset.next()){
        
        cesionid = objrecordset.getInt("CesionId");
    }
    
    return cesionid;
}


public void updateMovimiento(int id) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
    
    
    String sql = null;
    Conexion auxconexion = new Conexion();
    Connection objconexion = auxconexion.obtener();
    Statement stm = objconexion.createStatement();
    sql = "Update Movimiento set Cesion=1 where Movimiento.MovimientoId="+ String.valueOf(id);  
    stm.execute(sql);

}









}
