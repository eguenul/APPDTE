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
public class TrasladoModel {
 
    
public ArrayList<Traslado> listTraslado() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
    Conexion auxconexion = new Conexion();   
    Connection objconexion = auxconexion.obtener();
    ArrayList<Traslado> arraylist1 = new  ArrayList<>();
    String sql = "Select * from Traslado";     
    Statement stm = objconexion.createStatement();
  
    ResultSet objrecordset = stm.executeQuery(sql);
    while(objrecordset.next()){
        Traslado objTraslado = new Traslado();
        objTraslado.setTipotrasladoid(objrecordset.getInt("TrasladoId"));
        objTraslado.setTrasladocod(objrecordset.getInt("TrasladoCod"));
        objTraslado.setTrasladodes(objrecordset.getString("TrasladoDes"));
        arraylist1.add(objTraslado);
    }
    
    
    
    return arraylist1;
}
    
    
    
public Traslado getData(int tipotrasladoid) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
      Conexion auxconexion = new Conexion();
      Connection  objconexion = auxconexion.obtener();
      String sql = "Select * from Traslado where TrasladoId="+String.valueOf(tipotrasladoid);
      Statement stm = objconexion.createStatement();
      ResultSet objrecordset = stm.executeQuery(sql);
      Traslado objTraslado = new Traslado(); 
    
    while(objrecordset.next()){
      objTraslado.setTipotrasladoid(objrecordset.getInt("TrasladoId"));
      objTraslado.setTrasladocod(objrecordset.getInt("TrasladoCod"));
      objTraslado.setTrasladodes(objrecordset.getString("TrasladoDes"));
    }
      return objTraslado;
      
}
    
}
