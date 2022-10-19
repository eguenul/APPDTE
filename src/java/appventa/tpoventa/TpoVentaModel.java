/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appventa.tpoventa;

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
public class TpoVentaModel {
     Connection objconexion;
    
     
     public TpoVentaModel() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
         
           Conexion auxconexion = new Conexion();
        this.objconexion = auxconexion.obtener();
     }
    
    public ArrayList<TpoVenta> listTpoVenta() throws SQLException{
          ArrayList<TpoVenta> arraylist1 = new  ArrayList<>();
        String sql = "Select * from TpoVenta";     
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
        
        while(objrecordset.next()){
        TpoVenta objTpoVenta = new TpoVenta();
        objTpoVenta.setIdtpoventa(objrecordset.getInt("IdTpoVenta"));
        objTpoVenta.setCodSii(objrecordset.getString("CodSii"));
        objTpoVenta.setDescripcion(objrecordset.getString("Descripcion"));
        arraylist1.add(objTpoVenta);
    }
        return arraylist1;
    }
    
    
}
