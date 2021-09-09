/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.parametros;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author esteban
 */
public class ParametroModel {
  Connection  objconexion;

public ParametroModel(Connection objconexion){
    this.objconexion = objconexion;
    
}

public float getIva() throws SQLException{
    String sql;
    sql = "Select Iva from Parametros ";
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    objrecordset.next();
    float iva = objrecordset.getFloat("Iva");
    return iva;
    
}






}
