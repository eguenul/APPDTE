
package appventas.centrocosto;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class CentroCostoModel{
int empresaid;
Connection objconexion;

public CentroCostoModel(int empresaid,Connection objconnection) throws SQLException, ClassNotFoundException{

   objconexion = objconnection;
    this.empresaid = empresaid; 
}

public void addCentroCosto(int empresaid,int centrocostocod, String centrocostonom) throws SQLException{
    Statement stmt = objconexion.createStatement(); 
    String sql;
    sql ="INSERT into CentroCosto (EmpresaId, CentroCostoCod,CentroCostoNom)\n"+
    "values ("+String.valueOf(empresaid)+","+String.valueOf(centrocostocod)+",'"+centrocostonom+ "')"; 
    stmt.execute(sql);
  
}

public ArrayList<CentroCosto> listCentroCosto() throws SQLException{
    Statement stmt = objconexion.createStatement(); 
    ResultSet objlistado = stmt.executeQuery("Select * from CentroCosto");
  
     ArrayList<CentroCosto> centrocostoList = new ArrayList();
    
    while (objlistado.next()){
        CentroCosto objcentro = new CentroCosto();
        objcentro.setCentrocostocod(objlistado.getString("CentroCostoCod"));
        objcentro.setCentrocostonom(objlistado.getString("CentroCostoNom"));
        centrocostoList.add(objcentro);
    }
    return centrocostoList;
}




 
 public int serachId(String centronom) throws SQLException{
   int id;
   id = 0;
   String sql;
   
  sql = "Select * from CentroCosto where EmpresaId="+String.valueOf(empresaid)+"\n"+
  "and CentroCostoNom='"+centronom+"'";       
   
     Statement stmt = objconexion.createStatement(); 
    ResultSet objrecordset = stmt.executeQuery(sql); 
      while(objrecordset.next()){
       id = objrecordset.getInt("CentroCostoId");
       }
     return id;
 }




public void updateCorrelativo(){
    
    
    
}


public ArrayList<CentroCosto> listPage() throws SQLException{
    
  String sql = "Select * from CentroCosto where EmpresaId="+String.valueOf(empresaid)+" limit 0,10";
  Statement stmt = objconexion.createStatement(); 
  ResultSet objrecordset = stmt.executeQuery(sql); 
  ArrayList<CentroCosto>  listaCentro = new ArrayList();
  
while (objrecordset.next()){
    CentroCosto objcentro = new CentroCosto();    
    objcentro.setCentrocostocod(objrecordset.getString("CentroCostoCod"));
    objcentro.setCentrocostonom(objrecordset.getString("CentroCostoNom"));         
    listaCentro.add(objcentro);
}
  
  
  
 
  return listaCentro;


}



public DefaultTableModel searchNom(String centrocostonom) throws SQLException, ClassNotFoundException{
  
      ResultSet res;
         Object[] tableHeaders = {"CODIGO", "NOMBRE"};
         DefaultTableModel dtm = new DefaultTableModel();
        
       
        dtm.setDataVector(null, tableHeaders);
        
        
        Statement stmt = objconexion.createStatement(); 
        res = stmt.executeQuery("Select CentroCostoCod,CentroCostoNom from CentroCosto where CentroCostoNom LIKE '"+centrocostonom+"%'");
        ResultSetMetaData meta = res.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            while (res.next())
            {
                Object [] rowData = new Object[numberOfColumns];
                for (int i = 0; i < rowData.length; ++i)
                {
                    rowData[i] = res.getObject(i+1);
                    System.out.print(res.getObject(i+1));
                }
                dtm.addRow(rowData);
                System.out.print(dtm);
            }
            return dtm;

}




 public CentroCosto searchCentro(String centrocod) throws SQLException{
      Statement stmt = objconexion.createStatement(); 
      ResultSet res = stmt.executeQuery("Select * from CentroCosto where CentroCostoCod ="+centrocod);
      CentroCosto obj = new CentroCosto(); 
      while(res.next()){
           
           obj.setCentrocostocod(res.getString("CentroCostoCod"));
           obj.setCentrocostonom(res.getString("CentroCostoNom"));
       }
     
      return obj;
 }





}