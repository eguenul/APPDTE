package appventas.movimientos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import appventas.include.Conexion;
import appventas.producto.Producto;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
public class MovimientoModel2 {
Connection objconexion;

public MovimientoModel2() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
   Conexion auxconexion = new Conexion();
   objconexion = auxconexion.obtener();
}    
    

public ArrayList<Object[]> listaFactura(int codigosii, int cliprovid) throws SQLException{
String sql;    
sql ="Select Movimiento.MovimientoFecha,Movimiento.ReferenciaFlag,\n"+
"TipoDocumentos.TipoDocumentoDes, \n"+
"TipoDocumentos.CodigoSii, \n"+
"CliProv.CliProvRut, \n"+
"Movimiento.MovimientoId, \n"+        
"Movimiento.NumDoc,Movimiento.MovimientoTotalBruto, \n"+
"Movimiento.MovimientoIdentificadorEnvio \n"+
"from Movimiento \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Movimiento.TipoDocumentoId \n"+
"inner join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n"+
        
"where TipoDocumentos.CodigoSii="+String.valueOf(codigosii) +" and Movimiento.CliProvId="+String.valueOf(cliprovid)+" and Movimiento.ReferenciaFlag=0";
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


public ArrayList<Object[]> buscaDoc(int numdoc, int codigosii, int cliprovid) throws SQLException{
String sql;    
sql ="Select Movimiento.MovimientoFecha,Movimiento.ReferenciaFlag,\n"+
"TipoDocumentos.TipoDocumentoDes, \n"+
"TipoDocumentos.CodigoSii, \n"+
"CliProv.CliProvRut, \n"+
"Movimiento.MovimientoId, \n"+        
"Movimiento.NumDoc,Movimiento.MovimientoTotalBruto, \n"+
"Movimiento.MovimientoIdentificadorEnvio \n"+
"from Movimiento \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Movimiento.TipoDocumentoId \n"+
"inner join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n"+
        
"where TipoDocumentos.CodigoSii="+String.valueOf(codigosii) +" and Movimiento.CliProvId="+String.valueOf(cliprovid)+" and Movimiento.ReferenciaFlag=0  and Movimiento.Cesion=0\n"+
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













public ArrayList<DetalleMovimiento> listDetalle(int idmovimiento) throws SQLException{
    
String sql = "Select Producto.ProductoCod,Producto.ProductoNom, \n"+
"Producto.ProductoPrecioVenta,DetalleMovimiento.Cantidad,\n"+
"DetalleMovimiento.DescuentoPct, \n"+
"DetalleMovimiento.TotalDetalle \n"+
"from \n"+ 
"DetalleMovimiento \n"+
"inner Join Producto on Producto.ProductoId = DetalleMovimiento.ProductoId \n"+
"where DetalleMovimiento.MovimientoId="+String.valueOf(idmovimiento);
   
System.out.print(sql);
Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    ArrayList<DetalleMovimiento> arraydetalle = new ArrayList<>();
    while(objrecordset.next()){
             DetalleMovimiento objdetalle = new DetalleMovimiento();
             Producto objProducto = new Producto();   
             objProducto.setProductocod(objrecordset.getInt("ProductoCod"));
             objProducto.setProductonom(objrecordset.getString("ProductoNom"));
             objProducto.setProductoprevent(objrecordset.getInt("ProductoPrecioVenta"));
             objdetalle.setObjProducto(objProducto);
             objdetalle.setCantidad(objrecordset.getInt("Cantidad"));
             objdetalle.setDescuentopct(objrecordset.getInt("DescuentoPct"));
             objdetalle.setTotal(objrecordset.getInt("TotalDetalle"));
             arraydetalle.add(objdetalle);
        }
        return arraydetalle;
}


public Movimiento getData(int idmovimiento) throws SQLException{
   Movimiento objMovimiento = new Movimiento();
   String sql ="Select * from Movimiento where MovimientoId="+String.valueOf(idmovimiento);
   Statement stm = objconexion.createStatement();
   ResultSet objrecordset = stm.executeQuery(sql);
   
   while(objrecordset.next()){
       objMovimiento.setMontoafecto(objrecordset.getInt("MovimientoValorNeto"));
       objMovimiento.setMontoexento(objrecordset.getInt("MovimientoExento"));
       objMovimiento.setMontoiva(objrecordset.getInt("MovimientoIva"));
       objMovimiento.setFechamov(objrecordset.getString("MovimientoFecha"));
       objMovimiento.setMontototal(objrecordset.getInt("MovimientoTotalBruto"));
    
   
   }
   
   return objMovimiento;
   
}
   
   public Object[]  getReferencia(int movimientoid) throws SQLException{     
      String sql;
      sql="Select Movimiento.MovimientoFecha,"+"\n"+
      "Movimiento.NumDoc, \n"+
      "TipoDocumentos.TipoDocumentoDes,TipoDocumentos.CodigoSii \n"+
      "from Movimiento \n"+     
      "inner join TipoDocumentos on Movimiento.TipoDocumentoId = TipoDocumentos.TipoDocumentoId\n"+
       "where Movimiento.MovimientoId="+String.valueOf(movimientoid);
        Statement stm = objconexion.createStatement();
        ResultSet objrecordset = stm.executeQuery(sql);

        String fecha = new String();
        int numdoc = 0;
        String docdes = new String();
        int codsii = 0;
        Object[] auxData = new Object[4];
        while(objrecordset.next()){
            fecha = objrecordset.getString("MovimientoFecha");
            numdoc = objrecordset.getInt("NumDoc");
            docdes = objrecordset.getString("TipoDocumentoDes");
            codsii = objrecordset.getInt("CodigoSii");
        }
        auxData[0] = fecha;
        auxData[1] = numdoc;
        auxData[2] = docdes;
        auxData[3] = codsii;
        return auxData;    
   }

   
   
   
   

public ArrayList<Object[]> listFecha(String fechadesde, String fechahasta,int empresaid,int indice) throws SQLException{
String sql;    


 String fecha1 =fechadesde;


 
 
 String fecha2 = fechahasta;



sql ="Select DATE_FORMAT(Movimiento.MovimientoFecha,'%d-%m-%Y') as MovimientoFecha  ,\n"+
"TipoDocumentos.TipoDocumentoDes,TipoDocumentos.CodigoSii, \n"+
"Movimiento.NumDoc,Movimiento.MovimientoTotalBruto,Movimiento.MovimientoId, \n"+
"Movimiento.MovimientoIdentificadorEnvio,\n"+
" CliProv.CliProvRut \n"+
"from Movimiento \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Movimiento.TipoDocumentoId \n"+
"inner join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n";

if(fecha1.equals(fecha2)){
    sql= sql+"where MovimientoFecha='"+fecha1+"' limit "+String.valueOf(indice)+",10";
}else{

sql= sql+"where MovimientoFecha between DATE_FORMAT('"+fecha1+"','%Y-%m-%d') and" +  " DATE_FORMAT('"+fecha2+"','%Y-%m-%d') and CliProv.EmpresaId="+String.valueOf(empresaid)+" limit "+String.valueOf(indice)+",10";   
}

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

   
   
 

public int  conteoMovimiento(String fechadesde, String fechahasta,int empresaid) throws SQLException{
    
    
    
    

 String fecha1 = fechadesde;


 

 String fecha2 = fechahasta;


String sql ="select count(*) as NroRegistros  \n"+
"from Movimiento \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Movimiento.TipoDocumentoId \n"+
"inner join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n";

if(fecha1.equals(fecha2)){
    sql= sql+"where CliProv.EmpresaId="+String.valueOf(empresaid) + " and MovimientoFecha='"+fecha1+"'";
}else{

sql= sql+"where MovimientoFecha between DATE_FORMAT('"+fecha1+"','%Y-%m-%d') and" +  " DATE_FORMAT('"+fecha2+"','%Y-%m-%d') and CliProv.EmpresaId="+String.valueOf(empresaid);   
}
   System.out.print(sql);
      Statement stm = objconexion.createStatement();
      ResultSet objrecordset = stm.executeQuery(sql);
      int nroregistro = 0;
      while(objrecordset.next()){
       nroregistro =  objrecordset.getInt("NroRegistros");
      }  
    return nroregistro;
    

}  
   
public int conteoVenta(String fechadesde, String fechahasta,int empresaid) throws SQLException{
    
    
    

 String fecha1 = fechadesde;


 
 
 String fecha2 = fechahasta;

    
    
 String sql;
 sql ="select count(*) as NroRegistros \n"+
"from Movimiento \n"+
"inner Join TipoDocumentos on Movimiento.TipoDocumentoId = TipoDocumentos.TipoDocumentoId \n"+
"inner Join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n"+
"inner Join Empresa on Empresa.EmpresaId = CliProv.EmpresaId \n"+
"where CliProv.EmpresaId="+String.valueOf(empresaid);
if(fecha1.equals(fecha2)){
   sql = sql+"and Movimiento.MovientoFecha='"+fecha1+"'"; 
}else{
         
    sql = sql+" and Movimiento.MovimientoFecha between DATE_FORMAT("+fecha1+",'%Y-%m-%d') and DATE_FORMAT("+fecha2+",'%Y-%m-%d') \n";
}        
sql = sql+ "and TipoDocumentos.CodigoSii=33 or  TipoDocumentos.CodigoSii=61 \n"+
"order By TipoDocumentos.CodigoSii asc \n";
Statement stm = objconexion.createStatement();
ResultSet objrecordset = stm.executeQuery(sql);  
int nroregistros = objrecordset.getInt("NroRegistros");
return nroregistros;

}


public ArrayList<Object[]> buscaDoc2(int numdoc, int tipodoc, int empresaid) throws SQLException{
String sql;    
sql ="Select Movimiento.MovimientoFecha,Movimiento.ReferenciaFlag,\n"+
"TipoDocumentos.TipoDocumentoDes, \n"+
"TipoDocumentos.CodigoSii, \n"+
"Movimiento.MovimientoId, \n"+        
"Movimiento.NumDoc,Movimiento.MovimientoTotalBruto, \n"+
"Movimiento.MovimientoIdentificadorEnvio, \n"+
 "CliProv.CliProvRut \n"+
"from Movimiento \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Movimiento.TipoDocumentoId \n"+
"inner join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n"+
"where CliProv.EmpresaId="+ String.valueOf(empresaid)+ " and Movimiento.NumDoc=" + String.valueOf(numdoc) + " and Movimiento.TipoDocumentoId=" + String.valueOf(tipodoc);

   
System.out.print(sql);
Statement stm = objconexion.createStatement();
ResultSet objrecordset = stm.executeQuery(sql);
ArrayList<Object[]> arraymovimientos = new ArrayList<>();
while(objrecordset.next()){
    Object[] objMovimiento = new Object[9];
    objMovimiento[0] = objrecordset.getString("TipoDocumentoDes");  
    objMovimiento[1] = objrecordset.getInt("NumDoc");
    objMovimiento[2] =   objrecordset.getString("MovimientoFecha");
    objMovimiento[3]  =  objrecordset.getInt("MovimientoTotalBruto");
    objMovimiento[4]  =  objrecordset.getInt("MovimientoIdentificadorEnvio");
    objMovimiento[6]  =  objrecordset.getString("MovimientoId");
    objMovimiento[7]  =  objrecordset.getString("CodigoSii");
    objMovimiento[8]  =  objrecordset.getString("CliProvRut");
    arraymovimientos.add(objMovimiento);
}
System.out.print(sql);
return arraymovimientos;
}




public boolean flagbuscaDoc2(int numdoc, int tipodoc, int empresaid) throws SQLException{
String sql;    
sql ="Select Movimiento.MovimientoFecha,Movimiento.ReferenciaFlag,\n"+
"TipoDocumentos.TipoDocumentoDes, \n"+
"TipoDocumentos.CodigoSii, \n"+
"CliProv.CliProvRut, \n"+
"Movimiento.MovimientoId, \n"+        
"Movimiento.NumDoc,Movimiento.MovimientoTotalBruto, \n"+
"Movimiento.MovimientoIdentificadorEnvio \n"+
"from Movimiento \n"+
"inner join TipoDocumentos on TipoDocumentos.TipoDocumentoId = Movimiento.TipoDocumentoId \n"+
"inner join CliProv on CliProv.CliProvId = Movimiento.CliProvId \n"+
"where CliProv.EmpresaId="+ String.valueOf(empresaid)+ " and Movimiento.NumDoc=" + String.valueOf(numdoc) + " and Movimiento.TipoDocumentoId=" + String.valueOf(tipodoc);

System.out.print(sql);
Statement stm = objconexion.createStatement();
ResultSet objrecordset = stm.executeQuery(sql);
return objrecordset.next();

}









   
   

}
  

  
