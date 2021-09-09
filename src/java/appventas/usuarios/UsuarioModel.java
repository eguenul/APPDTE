
package appventas.usuarios;

import appventas.include.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class UsuarioModel {
  
   
    
public void addUsuario(Usuario objusuario) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
      Conexion auxConexion = new Conexion();
      Connection    objconexion = auxConexion.obtener();
       
      String login = objusuario.getLogin();
      String password = objusuario.getPassword();
      String email = objusuario.getEmail();
      String rut = objusuario.getRut();
      String nombre = objusuario.getUsuarionom();
      String apellido = objusuario.getUsuarioap();
      String sql = "insert into Usuario (UsuarioLogin,UsuarioPass,UsuarioRut,UsuarioEmail,UsuarioNom,UsuarioApell) \n"
                    +"values ("+"'"+login+"','"+password+"','"+rut+"',"+"'"+email+"',"+"'"+nombre+"','"+apellido+"')";
      
      Statement smt = objconexion.createStatement();
      smt.execute(sql);    
   }
    
   
   public void updateUsuario(Usuario objusuario) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
        String login = objusuario.getLogin();
      String password = objusuario.getPassword();
      String email = objusuario.getEmail();
      String rut = objusuario.getRut();
      String nombre = objusuario.getUsuarionom();
      String apellido = objusuario.getUsuarioap();
     
       String sql = "update Usuario set "+ 
                     "UsuarioPass='"+password+"', \n"+
                     "UsuarioRut='"+rut+"', \n"+
                     "UsuarioNom='"+nombre+"',\n"+
                     "UsuarioApell='"+apellido+"',\n"+
                     "UsuarioEmail ='"+email+"' \n"+
                     "where UsuarioLogin='"+login+"'\n";
   System.out.print(sql);
       Conexion auxConexion = new Conexion();
      Connection    objconexion = auxConexion.obtener();
     
      
      
      Statement smt = objconexion.createStatement();
      smt.execute(sql);     
      
   }
   
   
   public Usuario getUsuario(String usuariologin) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
        Conexion auxConexion = new Conexion();
      Connection    objconexion = auxConexion.obtener();
     
      
       String sql = "Select * from Usuario where UsuarioLogin='"+usuariologin +"'";
       System.out.print(sql);
       Usuario objusuario = new Usuario();
       Statement smt = objconexion.createStatement();
       ResultSet objrecordset = smt.executeQuery(sql);  
       while(objrecordset.next()){
            objusuario.setEmail(objrecordset.getString("UsuarioEmail"));
            objusuario.setRut(objrecordset.getString("UsuarioRut"));
            objusuario.setUsuarioap(objrecordset.getString("UsuarioApell"));
            objusuario.setUsuarionom(objrecordset.getString("UsuarioNom"));
            objusuario.setLogin(objrecordset.getString("UsuarioLogin"));
            objusuario.setPassword(objrecordset.getString("UsuarioPass"));
        }
       return objusuario;
   }
   
   public ArrayList<Usuario> listUsuario() throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
        Conexion auxConexion = new Conexion();
        Connection    objconexion = auxConexion.obtener();
     
      
       ArrayList<Usuario> arraylista = new ArrayList<>();
       String sql = "Select * from Usuario where UsuarioLogin<>'admin' limit 0,10";
       Statement smt = objconexion.createStatement();
       ResultSet objrecordset = smt.executeQuery(sql);  
       
       while(objrecordset.next()){
            Usuario objusuario = new Usuario();
            objusuario.setEmail(objrecordset.getString("UsuarioEmail"));
            objusuario.setRut(objrecordset.getString("UsuarioRut"));
            objusuario.setUsuarioap(objrecordset.getString("UsuarioApell"));
            objusuario.setUsuarionom(objrecordset.getString("UsuarioNom"));
            objusuario.setLogin(objrecordset.getString("UsuarioLogin"));
            objusuario.setPassword(objrecordset.getString("UsuarioPass"));
            arraylista.add(objusuario);
       }
       
       return arraylista;
   }
   
    public ArrayList<Usuario> searchLogin (String usuariologin) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
         Conexion auxConexion = new Conexion();
        Connection    objconexion = auxConexion.obtener();
     
        
       ArrayList<Usuario> arraylista = new ArrayList<>();
       
      String sql = "select * from Usuario where UsuarioLogin LIKE '"+usuariologin+"%' and UsuarioLogin<>'admin'";
      
       Statement smt = objconexion.createStatement();
       ResultSet objrecordset = smt.executeQuery(sql);  
       
       while(objrecordset.next()){
            Usuario objusuario = new Usuario();
            objusuario.setEmail(objrecordset.getString("UsuarioEmail"));
            objusuario.setRut(objrecordset.getString("UsuarioRut"));
            objusuario.setUsuarioap(objrecordset.getString("UsuarioApell"));
            objusuario.setUsuarionom(objrecordset.getString("UsuarioNom"));
            objusuario.setLogin(objrecordset.getString("UsuarioLogin"));
            objusuario.setPassword(objrecordset.getString("UsuarioPass"));
            arraylista.add(objusuario);
       }
       
        return arraylista;
    }
    
    
     public ArrayList<Usuario> searchRut (String usuariorut) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
         Conexion auxConexion = new Conexion();
        Connection    objconexion = auxConexion.obtener();
     
        
       ArrayList<Usuario> arraylista = new ArrayList<>();
       
      String sql = "select * from Usuario where UsuarioRut LIKE '"+usuariorut+"%' and UsuarioLogin<>'admin'";
      
       Statement smt = objconexion.createStatement();
       ResultSet objrecordset = smt.executeQuery(sql);  
       
       while(objrecordset.next()){
            Usuario objusuario = new Usuario();
            objusuario.setEmail(objrecordset.getString("UsuarioEmail"));
            objusuario.setRut(objrecordset.getString("UsuarioRut"));
            objusuario.setUsuarioap(objrecordset.getString("UsuarioApell"));
            objusuario.setUsuarionom(objrecordset.getString("UsuarioNom"));
            objusuario.setLogin(objrecordset.getString("UsuarioLogin"));
            objusuario.setPassword(objrecordset.getString("UsuarioPass"));
            arraylista.add(objusuario);
       }
       
        return arraylista;
    }
    
    
     public ArrayList<Usuario> searchNom (String usuarionom) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
         Conexion auxConexion = new Conexion();
        Connection    objconexion = auxConexion.obtener();
     
        
       ArrayList<Usuario> arraylista = new ArrayList<>();
       
      String sql = "select * from Usuario where UsuarioNom LIKE '"+usuarionom+"%' and UsuarioLogin<>'admin'";
      
       Statement smt = objconexion.createStatement();
       ResultSet objrecordset = smt.executeQuery(sql);  
       
       while(objrecordset.next()){
            Usuario objusuario = new Usuario();
            objusuario.setEmail(objrecordset.getString("UsuarioEmail"));
            objusuario.setRut(objrecordset.getString("UsuarioRut"));
            objusuario.setUsuarioap(objrecordset.getString("UsuarioApell"));
            objusuario.setUsuarionom(objrecordset.getString("UsuarioNom"));
            objusuario.setLogin(objrecordset.getString("UsuarioLogin"));
            objusuario.setPassword(objrecordset.getString("UsuarioPass"));
            arraylista.add(objusuario);
       }
       
        return arraylista;
    }
    
    
    
     public ArrayList<Usuario> searchApellido (String usuarioapellido) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
         Conexion auxConexion = new Conexion();
        Connection    objconexion = auxConexion.obtener();
     
        
       ArrayList<Usuario> arraylista = new ArrayList<>();
       
      String sql = "select * from Usuario where UsuarioApell LIKE '"+usuarioapellido+"%' and  UsuarioLogin<>'admin'";
      
       Statement smt = objconexion.createStatement();
       ResultSet objrecordset = smt.executeQuery(sql);  
       
       while(objrecordset.next()){
            Usuario objusuario = new Usuario();
            objusuario.setEmail(objrecordset.getString("UsuarioEmail"));
            objusuario.setRut(objrecordset.getString("UsuarioRut"));
            objusuario.setUsuarioap(objrecordset.getString("UsuarioApell"));
            objusuario.setUsuarionom(objrecordset.getString("UsuarioNom"));
            objusuario.setLogin(objrecordset.getString("UsuarioLogin"));
            objusuario.setPassword(objrecordset.getString("UsuarioPass"));
            arraylista.add(objusuario);
       }
       
        return arraylista;
    }
    
     
     
     
     
     
     
     
     
    
    
    
    
}
