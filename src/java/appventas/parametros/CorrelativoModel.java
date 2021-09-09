/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.parametros;

import appventas.include.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class CorrelativoModel {
      
public Correlativo showCorrelativo(int empresaid) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
        Correlativo objCorrelativo = new Correlativo();
        Conexion auxconexion = new Conexion();
        Connection objconexion = auxconexion.obtener();
        
  String sql = "Select * from Correlativo where EmpresaId=" + String.valueOf(empresaid);
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    while( objrecordset.next()){
        
        objCorrelativo.setFacventaafecta(objrecordset.getInt("FacVentaAfecta"));
        objCorrelativo.setNotacredito(objrecordset.getInt("NotaCredito"));
        objCorrelativo.setFacventaexenta(objrecordset.getInt("FacVentaExenta"));
    }
        return objCorrelativo;
    }

public  void setCorrelativos(Correlativo objCorrelativo, int empresaid) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
      Conexion auxconexion = new Conexion();
      Connection objconexion = auxconexion.obtener();
      
    String sql = "Update Correlativo set FacVentaAfecta=" + String.valueOf(objCorrelativo.getFacventaafecta()) + ",\n"+
                  "NotaCredito=" + String.valueOf(objCorrelativo.getNotacredito()) + ",\n"+
                  "FacVentaExenta=" + String.valueOf(objCorrelativo.getFacventaexenta()) + "\n"+
                  "Where EmpresaId=" + String.valueOf(empresaid);
    Statement stm = objconexion.createStatement();
    stm.execute(sql);
    
    
}





}
