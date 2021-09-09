/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.cesionario;

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
public class CesionarioModel {
    
    public CesionarioModel(){
        
    }
    
    
   public ArrayList<Cesionario> listCesionario() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
       
        Conexion auxconexion = new Conexion();
        Connection objconexion = auxconexion.obtener();
        
        
        ArrayList<Cesionario> arrayCesionario = new ArrayList<>();
        Statement stm = objconexion.createStatement();
         
        String sql = "select * from Cesionario limit  0,10";
        ResultSet objrecordset = stm.executeQuery(sql);
        
        while(objrecordset.next()){
            Cesionario objCesionario = new Cesionario();
            
            objCesionario.setCesionariocod(objrecordset.getInt("CesionarioCod"));
            objCesionario.setCesionariorut(objrecordset.getString("CesionarioRut"));
            objCesionario.setCesionariorzsc(objrecordset.getString("CesionarioRzsc"));
            objCesionario.setCesionariodir(objrecordset.getString("CesionarioDir"));
            objCesionario.setCesionarioemail(objrecordset.getString("CesionarioEmail"));
            objCesionario.setCesionarioid(objrecordset.getInt("CesionarioId"));
            arrayCesionario.add(objCesionario);
        }
        
        return arrayCesionario;
   
   }
   public Cesionario showCesionario(int cesionariocod) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
       
        Conexion auxconexion = new Conexion();
        Connection objconexion = auxconexion.obtener();
        Statement stm = objconexion.createStatement();
        String sql = "select * from Cesionario where CesionarioCod="+String.valueOf(cesionariocod);
        System.out.print(sql);
        ResultSet objrecordset = stm.executeQuery(sql);
        Cesionario objCesionario = new Cesionario();
          while(objrecordset.next()){
        objCesionario.setCesionariocod(objrecordset.getInt("CesionarioCod"));
        objCesionario.setCesionariorut(objrecordset.getString("CesionarioRut"));
        objCesionario.setCesionariorzsc(objrecordset.getString("CesionarioRzsc"));
        objCesionario.setCesionariodir(objrecordset.getString("CesionarioDir"));
        objCesionario.setCesionarioemail(objrecordset.getString("CesionarioEmail"));
        objCesionario.setCesionarioid(objrecordset.getInt("CesionarioId"));
          }
        return objCesionario;
   }
   
   
public void addCesionario(Cesionario objCesionario) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
   Conexion auxconexion = new Conexion();
        Connection objconexion = auxconexion.obtener();
        Statement stm = objconexion.createStatement();
        String sql = "select CesionarioCod from Correlativo2";
        ResultSet objrecordset = stm.executeQuery(sql);
         int cesionariocod = 0;
        while(objrecordset.next()){
         cesionariocod = objrecordset.getInt("CesionarioCod");
        }
   String cesionariorut  = objCesionario.getCesionariorut();
   String cesionariorzsc = objCesionario.getCesionariorzsc();
   String cesionariodir = objCesionario.getCesionariodir();
   String cesionarioemail = objCesionario.getCesionarioemail();
   
   Statement stm2 = objconexion.createStatement();
   String sql2 = "insert into Cesionario (CesionarioCod,CesionarioRut,CesionarioRzsc,CesionarioDir,CesionarioEmail) \n"+
                 "values("+String.valueOf(cesionariocod)+",'"+cesionariorut+"','"+cesionariorzsc+"','"+cesionariodir+"','"+cesionarioemail+"')";
   
   stm2.execute(sql2);
   
   Statement stm3 = objconexion.createStatement();
   stm3.execute("Update Correlativo2 set CesionarioCod=CesionarioCod+1");
   
   
   System.out.print(sql2);
   
   
 }
   
    public void updateCesionario(Cesionario objCesionario) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
        Conexion auxconexion = new Conexion();
        Connection objconexion = auxconexion.obtener();
        Statement stm = objconexion.createStatement();
        String sql = "Update Cesionario \n"+
                     "SET \n"+
                     "CesionarioRut='"+ objCesionario.getCesionariorut()+"', \n"+
                     "CesionarioDir='"+ objCesionario.getCesionariodir()+"', \n"+
                     "CesionarioRzsc='"+ objCesionario.getCesionariorzsc()+"', \n"+
                     "CesionarioEmail='"+ objCesionario.getCesionarioemail()+"' \n"+
                     "Where CesionarioCod="+objCesionario.getCesionariocod();
       stm.execute(sql);
       
    }
    
    public ArrayList<Cesionario> searchCod(int cesionariocod) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
        
          ArrayList<Cesionario> arraylista = new ArrayList<>();
   
          Conexion auxconexion = new Conexion();
        Connection objconexion = auxconexion.obtener();
        Statement stm = objconexion.createStatement();
        String sql = "select * from Cesionario where CesionarioCod="+String.valueOf(cesionariocod);
          System.out.print(sql);
          ResultSet objrecordset = stm.executeQuery(sql);
          
          
          
          
    while(objrecordset.next()){
        Cesionario objCesionario = new Cesionario();
        objCesionario.setCesionariocod(objrecordset.getInt("CesionarioCod"));
        objCesionario.setCesionariorut(objrecordset.getString("CesionarioRut"));
        objCesionario.setCesionariorzsc(objrecordset.getString("CesionarioRzsc"));
        objCesionario.setCesionariodir(objrecordset.getString("CesionarioDir"));
        objCesionario.setCesionarioemail(objrecordset.getString("CesionarioEmail"));
        objCesionario.setCesionarioid(objrecordset.getInt("CesionarioId"));
        arraylista.add(objCesionario);
          }
    
    
    return arraylista;
    }
    
    
    
    
    public ArrayList<Cesionario> searchRaz(String cesionarioraz) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
          ArrayList<Cesionario> arraylista = new ArrayList<>();
   
          Conexion auxconexion = new Conexion();
        Connection objconexion = auxconexion.obtener();
        Statement stm = objconexion.createStatement();
        String sql = "select * from Cesionario where CesionarioRzsc LIKE '"+cesionarioraz+"%'";
          System.out.print(sql);
          ResultSet objrecordset = stm.executeQuery(sql);
          
          
          
          
    while(objrecordset.next()){
        Cesionario objCesionario = new Cesionario();
        objCesionario.setCesionariocod(objrecordset.getInt("CesionarioCod"));
        objCesionario.setCesionariorut(objrecordset.getString("CesionarioRut"));
        objCesionario.setCesionariorzsc(objrecordset.getString("CesionarioRzsc"));
        objCesionario.setCesionariodir(objrecordset.getString("CesionarioDir"));
        objCesionario.setCesionarioemail(objrecordset.getString("CesionarioEmail"));
        objCesionario.setCesionarioid(objrecordset.getInt("CesionarioId"));
        arraylista.add(objCesionario);
          }
    
    
    return arraylista;
        
        
    }
    
    
    public ArrayList<Cesionario> searchRut(String cesionariorut) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
 
              ArrayList<Cesionario> arraylista = new ArrayList<>();
   
          Conexion auxconexion = new Conexion();
        Connection objconexion = auxconexion.obtener();
        Statement stm = objconexion.createStatement();
        String sql = "select * from Cesionario where CesionarioRut LIKE '"+cesionariorut+"%'";
          System.out.print(sql);
          ResultSet objrecordset = stm.executeQuery(sql);
          
          
          
          
    while(objrecordset.next()){
        Cesionario objCesionario = new Cesionario();
        objCesionario.setCesionariocod(objrecordset.getInt("CesionarioCod"));
        objCesionario.setCesionariorut(objrecordset.getString("CesionarioRut"));
        objCesionario.setCesionariorzsc(objrecordset.getString("CesionarioRzsc"));
        objCesionario.setCesionariodir(objrecordset.getString("CesionarioDir"));
        objCesionario.setCesionarioemail(objrecordset.getString("CesionarioEmail"));
        objCesionario.setCesionarioid(objrecordset.getInt("CesionarioId"));
        arraylista.add(objCesionario);
          }
    
    
    
    return arraylista;
    }
    
}
