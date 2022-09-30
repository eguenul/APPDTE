/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appventas.fpago;

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
public class FPagoModel {
    Connection objconexion;
    public FPagoModel() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
        Conexion auxconexion = new Conexion();
        this.objconexion = auxconexion.obtener();
    }
    
    
    
public ArrayList<FPago> listFpago() throws SQLException {
    
    ArrayList<FPago> arraylist1 = new  ArrayList<>();
    String sql = "Select * from FPago";     
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    while(objrecordset.next()){
        FPago objFPago = new FPago();
        objFPago.setIdfpago(objrecordset.getInt("idFPago"));
        objFPago.setFpagodes(objrecordset.getString("FPagoDes"));
        objFPago.setFpagosii(objrecordset.getString("FPagoSII"));
        arraylist1.add(objFPago);
    }
    
      return arraylist1;
    
    
}
}
