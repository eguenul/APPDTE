/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.movimientos;

import appventas.include.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class DespachoModel {
  Connection objconexion;
  
  
  public ArrayList<Despacho> listDespacho() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
        Conexion auxconexion = new Conexion();
      objconexion = auxconexion.obtener();
      ArrayList<Despacho> arraydespacho = new ArrayList<>();
      String sql = "Select * from Despacho";
      Statement stm = objconexion.createStatement();
      ResultSet objrecordset = stm.executeQuery(sql);
     
      while( objrecordset.next()){
          Despacho objdespacho = new Despacho();
          objdespacho.setDespachocod(objrecordset.getInt("DespachoCod"));
          objdespacho.setDespachodes(objrecordset.getString("DespachoDes"));
          objdespacho.setDespachoid(objrecordset.getInt("DespachoId"));
          arraydespacho.add(objdespacho);
      }
      
      return arraydespacho;
  
  }
  
  public Despacho getData(int tipodespachoid) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
      Conexion auxconexion = new Conexion();
      objconexion = auxconexion.obtener();
      String sql = "Select * from Despacho where DespachoId="+String.valueOf(tipodespachoid);
      System.out.print(sql);
      Statement stm = objconexion.createStatement();
      ResultSet objrecordset = stm.executeQuery(sql);
      Despacho objDespacho = new Despacho(); 
    while(objrecordset.next()){
      objDespacho.setDespachoid(objrecordset.getInt("DespachoId"));
      objDespacho.setDespachocod(objrecordset.getInt("DespachoCod"));
      objDespacho.setDespachodes(objrecordset.getString("DespachoDes"));
    }
      
      return objDespacho;
  }
  
  
}
