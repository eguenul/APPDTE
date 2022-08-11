package appventas.movimientos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import appventas.include.Conexion;
import appventas.producto.Producto;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class MovimientoModel {
    Connection objconexion;
    public MovimientoModel() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
        Conexion auxconexion = new Conexion();
        this.objconexion = auxconexion.obtener();
    }
    
    public String addDocumento(int empresaid,int cliprovid,Movimiento objMovimiento) throws SQLException{
        
        int numdoc = objMovimiento.getNumdoc();
        String fechadoc = objMovimiento.getFechamov();
        int montoafecto = objMovimiento.getMontoafecto();
        int montoexento = objMovimiento.getMontoexento();
        int montoiva = objMovimiento.getMontoiva();
        int montototal = objMovimiento.getMontototal();
        int tipodoc = objMovimiento.getTipodoc();
        String fchref = objMovimiento.getFchref();
        
        
        Despacho objDespacho = objMovimiento.getTipodespacho();
        Traslado objTraslado = objMovimiento.getTipotraslado();
        
        String referencia = objMovimiento.getReferenciaGlobal();
       boolean bolref = objMovimiento.getBolref();
       String strbolref;
      
       if(bolref==false){
           strbolref="no";
           
       }else{
           
           strbolref="si";
       }
        
        
        
        String sql;
        sql="INSERT INTO Movimiento(MovimientoFecha,CliProvId,TipoDocumentoId,NumDoc, \n"+
        "MovimientoValorNeto,MovimientoExento,MovimientoIva,MovimientoTotalBruto,\n"
        +"MovimientoTipo,DespachoId,TrasladoId,ReferenciaGlobal,\n"
        +"OrdCompraNum,bolref,FchRef,FacAfecta,FacExenta,NotaCred,NotaDeb,CodSiiRef, EmpresaId) \n"+
        "values('"+fechadoc+"',"+String.valueOf(cliprovid) +","+String.valueOf(tipodoc)+"," +String.valueOf(numdoc)+
        ","+String.valueOf(montoafecto)+"," + String.valueOf(montoexento)+","+String.valueOf(montoiva) +
        ","+String.valueOf(montototal)+","+"1"+","+String.valueOf(objDespacho.getDespachoid())+","+String.valueOf(objTraslado.getTipotrasladoid())+",'"+ referencia +"' \n"
        +","+ objMovimiento.getOrdcompranum()+",'"+strbolref+"','"+fchref+"'"+","+objMovimiento.getFacafecta()+
        ","+objMovimiento.getFacexenta()+","+objMovimiento.getNotacredito()+","+objMovimiento.getNotadebito()+","+objMovimiento.getCodsiiref()+ ","+ String.valueOf(empresaid) +  ")";
       
        System.out.print(sql);
        
        Statement stm = objconexion.createStatement();
        stm.execute(sql);
        return sql; 
     }

    public int searchId(int cliprovid,int iddoc, int numdoc) throws SQLException{
        String sql;
        sql="Select * from Movimiento where CliProvId="+String.valueOf(cliprovid) + "\n"+
        " and TipoDocumentoId="+String.valueOf(iddoc)+" and NumDoc="+ String.valueOf(numdoc);        
        Statement stm = objconexion.createStatement();
        ResultSet objrecordset = stm.executeQuery(sql);
        System.out.print(sql);
        objrecordset.next();
        return objrecordset.getInt("MovimientoId");
    }
    
    
    
    public int searchDocId(int empresaid, int iddoc, int numdoc) throws SQLException{
        String sql;
        sql="Select Movimiento.*, CliProvCod from Movimiento \n"+ 
        "inner join CliProv on Movimiento.CliProvId=CliProv.CliProvId"+
         " where CliProv.EmpresaId="+String.valueOf(empresaid)+
        " and TipoDocumentoId="+String.valueOf(iddoc)+" and NumDoc="+ String.valueOf(numdoc);        
        Statement stm = objconexion.createStatement();
        ResultSet objrecordset = stm.executeQuery(sql);
        System.out.print(sql);
        objrecordset.next();
        return objrecordset.getInt("MovimientoId");
    }
    
    
    
    
    
    public void addDetalle(int idmovimiento,DetalleMovimiento objdetalle) throws SQLException{
           String sql;
           Statement stm = objconexion.createStatement();
           int cantidad = objdetalle.getCantidad();
           int total = objdetalle.getTotal();
          Producto objproducto = objdetalle.getObjProducto();
           int productoid = objproducto.getProductoid();
           int precio = objproducto.getProductoprevent();
           int descuentopct = objdetalle.getDescuentopct();
        int descuentomonto = objdetalle.getDescuentomonto();
          sql = "INSERT INTO DetalleMovimiento(MovimientoId,ProductoId,Cantidad,ProductoPrecio,DescuentoPct,DescuentoMonto, \n"+
          "TotalDetalle) values("+String.valueOf(idmovimiento)+","+String.valueOf(productoid)+"\n"+
           ","+String.valueOf(cantidad)+","+String.valueOf(precio)+","+String.valueOf(descuentopct) +","+String.valueOf(descuentomonto) +","+String.valueOf(total) +")";        
            stm.execute(sql);
         
          
    }
    
    
   
    
    private int searchIdProducto(int productocod) throws SQLException{
        String sql;
        sql = "Select * from Producto where ProductoCod="+String.valueOf(productocod);
        Statement stm = objconexion.createStatement();
        ResultSet objrecordset = stm.executeQuery(sql);
        objrecordset.next();
        return objrecordset.getInt("ProductoId");
    }  
    
    
    
    
public ArrayList<Traslado> listTraslado() throws SQLException{
    
    ArrayList<Traslado> arraylist1 = new  ArrayList<>();
    String sql = "Select * from TipoTraslado";     
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    while(objrecordset.next()){
        Traslado objTraslado = new Traslado();
        objTraslado.setTipotrasladoid(objrecordset.getInt("TipoTrasladoId"));
        objTraslado.setTrasladocod(objrecordset.getInt("TrasladoCod"));
        objTraslado.setTrasladodes(objrecordset.getString("TrasladoDes"));
        arraylist1.add(objTraslado);
    }
    
    
    
    return arraylist1;
}
    

    
public ArrayList<Despacho> listDespacho() throws SQLException{
    
    ArrayList<Despacho> arraylist1 = new  ArrayList<>();
    String sql = "Select * from TipoDespacho";     
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    while(objrecordset.next()){
        Despacho objDespacho = new Despacho();
        objDespacho.setDespachoid(objrecordset.getInt("DespachoId"));
        objDespacho.setDespachocod(objrecordset.getInt("DespachoCod"));
        objDespacho.setDespachodes(objrecordset.getString("DespachoDes"));
        arraylist1.add(objDespacho);
    }
    
    
    
    return arraylist1;
}
    


public int getIdDespacho(String tipodespacho) throws SQLException{
    String sql = "Select * from TipoDespacho  where DespachoDes='"+tipodespacho+"'";     
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    objrecordset.next();
    return objrecordset.getInt("TipoDespachoId");
    
}


public int getCodSiiDespacho(int idtipodespacho) throws SQLException{
    String sql = "Select * from TipoDespacho WHERE TipoDespachoId="+String.valueOf(idtipodespacho);     
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    objrecordset.next();
    return objrecordset.getInt("DespachoCod");
    
}




public int getIdTraslado(String tipotraslado) throws SQLException{
    String sql = "Select * from TipoTraslado  where TrasladoDes='"+tipotraslado+"'";     
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    objrecordset.next();
    return objrecordset.getInt("TipoTrasladoId");
    
}


public int getCodSiiTraslado(int idtipotraslado) throws SQLException{
    String sql = "Select * from TipoTraslado WHERE TipoTrasladoId="+String.valueOf(idtipotraslado);     
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    objrecordset.next();
    return objrecordset.getInt("TrasladoCod");
}


public Object[] showDocument(int idmovimiento) throws SQLException{
String sql = "select TipoDocumentos.TipoDocumentoDes,TipoDocumentos.CodigoSii, TipoDocumentos.TipoDocumentoId, CliProv.*, Movimiento.MovimientoFecha, \n"+
    "Movimiento.NumDoc,Movimiento.OrdCompraNum, \n"+
    "Movimiento.MovimientoValorNeto,Movimiento.MovimientoExento,Movimiento.MovimientoTotalBruto, \n"+
    "Movimiento.MovimientoIva,Movimiento.MovimientoId,Movimiento.bolref,Movimiento.FchRef ,Movimiento.FacAfecta,Movimiento.FacExenta, \n"+
    "Movimiento.NotaCred,Movimiento.NotaDeb,Movimiento.GuiaDesp,Movimiento.CodSiiRef, \n"+
    "Movimiento.ReferenciaGlobal \n"+    
    "from Movimiento \n"+
    "inner Join TipoDocumentos on Movimiento.TipoDocumentoId = TipoDocumentos.TipoDocumentoId \n"+
    "inner Join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n"+
    "where Movimiento.MovimientoId="+String.valueOf(idmovimiento);   
    System.out.print(sql);
      Statement stm = objconexion.createStatement();
      ResultSet objrecordset = stm.executeQuery(sql);
    Object[] auxData = new Object[23];
    
      while(objrecordset.next()){
            auxData[0] = objrecordset.getInt("CliProvCod");
          auxData[1] =  objrecordset.getString("CliProvRut");
          auxData[2] =  objrecordset.getString("CliProvRaz");
          auxData[3] =  objrecordset.getString("CliProvGir");
          auxData[4] =  objrecordset.getString("CliProvDir");
          auxData[5] =  objrecordset.getString("CliProvCom");
          auxData[6] =  objrecordset.getString("CliProvCiu");
          auxData[7] =  objrecordset.getString("TipoDocumentoDes");
          auxData[8] =  objrecordset.getString("CodigoSii");
          auxData[9] =  objrecordset.getInt("NumDoc");
          auxData[10] =  objrecordset.getString("MovimientoFecha");
          auxData[11] =  objrecordset.getInt("MovimientoValorNeto");
          auxData[12] =  objrecordset.getInt("MovimientoExento");
          auxData[13] =  objrecordset.getInt("MovimientoIva");
          auxData[14] =  objrecordset.getInt("MovimientoTotalBruto");
          auxData[15] =  objrecordset.getString("bolref");
          auxData[16] = objrecordset.getString("FchRef");
          auxData[17] = objrecordset.getInt("OrdCompraNum");
          auxData[18] = objrecordset.getInt("GuiaDesp");
          auxData[19] = objrecordset.getInt("FacAfecta");
             auxData[22] = objrecordset.getInt("FacExenta");
       
          auxData[20] = objrecordset.getInt("CodSiiRef");
          auxData[21] = objrecordset.getString("ReferenciaGlobal");
      }  
    
    
 return auxData;
}







public ArrayList<Object[]> showDetails(int idmovimiento) throws SQLException{
    
    
    
 ArrayList<Object[]> arraylista = new ArrayList<>();
 String sql = "select Producto.ProductoCod, Producto.ProductoNom, DetalleMovimiento.Cantidad,DetalleMovimiento.ProductoPrecio, \n"+
         "DetalleMovimiento.DescuentoPct, \n"+
         "DetalleMovimiento.TotalDetalle from DetalleMovimiento \n"+
         "inner join Movimiento on DetalleMovimiento.MovimientoId = Movimiento.MovimientoId \n"+
         "inner join Producto on DetalleMovimiento.ProductoId = Producto.ProductoId \n"+
         "where DetalleMovimiento.MovimientoId="+String.valueOf(idmovimiento);
    
    
      Statement stm = objconexion.createStatement();
      ResultSet objrecordset = stm.executeQuery(sql); 
    
     
      while(objrecordset.next()){
          Object[] auxData = new Object[7];
          auxData[0] = objrecordset.getInt("ProductoCod");
          auxData[1] = objrecordset.getString("ProductoNom");
          auxData[2] = objrecordset.getInt("Cantidad");
          auxData[3] = objrecordset.getInt("ProductoPrecio");
          auxData[4] = objrecordset.getInt("DescuentoPct"); 
          auxData[6] = objrecordset.getInt("TotalDetalle");
          arraylista.add(auxData);
      }
     
      return arraylista;
}



public void addXML(int idmovimiento,String empresarut, int numdoc, int docsiicod) throws SQLException, FileNotFoundException, ParserConfigurationException, SAXException, IOException{ 
  /*  
    objconexion.setAutoCommit(false);
    ConfigAppDTE objconfigclass = new ConfigAppDTE();
    String[] arrayrutemisor = empresarut.split("-");
    String auxrut = arrayrutemisor[0];
    String ruta= objconfigclass.getPathdte()+"DTE"+auxrut+"F"+String.valueOf(numdoc)+"T"+String.valueOf(docsiicod)+".xml";
    String sql = "Update Movimiento set BlobXML=?,Archivo=? where MovimientoId="+String.valueOf(idmovimiento);
    String archivo = "DTE"+auxrut+"F"+String.valueOf(numdoc)+"T"+String.valueOf(docsiicod)+".xml";
    FileInputStream fis = null;
    File file = new File(ruta);
    PreparedStatement ps = null;
    fis = new FileInputStream(file);
    ps = objconexion.prepareStatement(sql);
    ps.setBinaryStream(1,fis,(int)file.length());
    ps.setString(2, archivo);
    ps.executeUpdate();
    objconexion.commit();
    */
}

public void getXML(int idmovimiento) throws IOException, SQLException, ParserConfigurationException, SAXException{
    Statement stm = objconexion.createStatement();
    ResultSet rs = stm.executeQuery("SELECT BlobXML,Archivo FROM Movimiento where MovimientoId="+String.valueOf(idmovimiento)); 
/*
    ConfigAppDTE objconfigclass = new ConfigAppDTE();
  */  
/*   
while (rs.next()){
     Blob objblob = rs.getBlob("BlobXML");
      byte[] data = objblob.getBytes(1, (int)objblob.length());  
    try (FileOutputStream fos = new FileOutputStream(objconfigclass.getPathdte()+rs.getString("Archivo"))) {
         fos.write(data);
         fos.close(); 
    }
          
  }  
*/
     
}


public void updateTRACKID(int idmovimiento, String trackid) throws SQLException{

    String sql = "Update Movimiento set MovimientoIdentificadorEnvio="+String.valueOf(Integer.parseInt(trackid))+" where MovimientoId="+String.valueOf(idmovimiento);
    Statement stm = objconexion.createStatement();
    stm.execute(sql);    

}    

public void updateFlagReferencia(int idmovimiento, int referenciaflag) throws SQLException{

    String sql = "Update Movimiento set ReferenciaFlag="+String.valueOf(referenciaflag)+" where MovimientoId="+String.valueOf(idmovimiento);
    Statement stm = objconexion.createStatement();
    stm.execute(sql);    

}    



public void deleteMovimiento(int iddoc) throws SQLException{
    
 Statement stm = objconexion.createStatement();
 stm.execute("Delete from DetalleMovimiento where MovimientoId="+ String.valueOf(iddoc));    
 stm.execute("Delete from BlobDTE where MovimientoId="+ String.valueOf(iddoc));
 stm.execute("Delete from Movimiento where MovimientoId="+ String.valueOf(iddoc));
}




}
