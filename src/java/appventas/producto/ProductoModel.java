package appventas.producto;
import appventas.include.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ProductoModel {
    Connection objconexion;
    private final int empresaid;
     
public ProductoModel(int empresaid) throws SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException{
    this.empresaid = empresaid;
    Conexion auxconexion = new Conexion();
    objconexion = auxconexion.obtener();
}
    

public ArrayList<Producto> listProducto(int indice) throws SQLException{
    String sql;
    sql = "Select * from Producto where EmpresaId="+String.valueOf(empresaid)+ " limit "+String.valueOf(indice)+",10";
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    ArrayList<Producto> arrayProducto = new ArrayList();
    
    while(objrecordset.next()){
        Producto objProducto = new Producto();
        objProducto.setProductocod(objrecordset.getInt("ProductoCod"));
        objProducto.setProductonom(objrecordset.getString("ProductoNom"));
        objProducto.setProductoprevent(objrecordset.getInt("ProductoPrecioVenta"));
        objProducto.setProductoiva(objrecordset.getInt("ProductoAfectoExento"));
        arrayProducto.add(objProducto);
    }

        return arrayProducto;

}

public Producto searchProducto(int productocod) throws SQLException{
    
    String sql;
    sql = "Select * from Producto where ProductoCod="+String.valueOf(productocod) +" and EmpresaId="+String.valueOf(empresaid);
    Producto objProducto = new Producto(); 
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
   
    while(objrecordset.next()){ 
        objProducto.setProductocod(objrecordset.getInt("ProductoCod"));
        objProducto.setProductonom(objrecordset.getString("ProductoNom"));
        objProducto.setProductoprevent(objrecordset.getInt("ProductoPrecioVenta"));
        objProducto.setProductoiva(objrecordset.getInt("ProductoAfectoExento"));
        objProducto.setProductoid(objrecordset.getInt("ProductoId"));
   }
    return objProducto;
}
 
public int flagIva(int productocod) throws SQLException{
    String sql;
    sql = "Select ProductoAfectoExento from Producto where ProductoCod="+String.valueOf(productocod)+" and EmpresaId="+String.valueOf(empresaid);
    System.out.print(sql);
    Statement stm = objconexion.createStatement();
    ResultSet objrecordset = stm.executeQuery(sql);
    int flag = 0;
    objrecordset.next();
    flag = objrecordset.getInt("ProductoAfectoExento");
    return flag;
}

public void addProducto(Producto objProducto) throws SQLException{
    String sql; 
    int productocod = objProducto.getProductocod();
    String productonom = objProducto.getProductonom();
    int productoprecioventa = objProducto.getProductoprevent();
    int productoiva = objProducto.getProductoiva();
    sql = "INSERT INTO Producto (ProductoCod,ProductoNom,ProductoPrecioVenta,ProductoAfectoExento,EmpresaId) \n"+
    "values("+String.valueOf(productocod)+",'"+productonom+"',"+ String.valueOf(productoprecioventa)+","+String.valueOf(productoiva)+ ","+String.valueOf(empresaid)+")";
    Statement stm = objconexion.createStatement();
    stm.execute(sql);
    updateCorrelativo();
}



public ArrayList<Producto> searchCod(int productocod) throws SQLException, ClassNotFoundException{
       ArrayList<Producto> arraylista = new ArrayList<>();
       String sql ="Select * from Producto where ProductoCod="+String.valueOf(productocod)+" and EmpresaId="+String.valueOf(empresaid);
       Statement stmt = objconexion.createStatement();
       ResultSet objrecordset = stmt.executeQuery(sql);
       
    while(objrecordset.next()){
       Producto objProducto = new Producto(); 
        objProducto.setProductocod(objrecordset.getInt("ProductoCod"));
        objProducto.setProductonom(objrecordset.getString("ProductoNom"));
        objProducto.setProductoprevent(objrecordset.getInt("ProductoPrecioVenta"));
       objProducto.setProductoiva(objrecordset.getInt("ProductoAfectoExento"));
        objProducto.setProductoid(objrecordset.getInt("ProductoId"));
        
        /*
        objProducto.setCliprovema(objrecordset.getString("CliProvEma"));
        objProducto.setCliprovraz(objrecordset.getString("CliProvRaz"));
        objProducto.setCliprovfon(objrecordset.getString("CliProvFon"));
        objProducto.setCliprovgir(objrecordset.getString("CliProvGir"));
        objProducto.setCliprovrut(objrecordset.getString("CliProvRut"));
       */ 
       arraylista.add(objProducto);
    }
        return arraylista;
}


public ArrayList<Producto> searchNom(String productonom) throws SQLException, ClassNotFoundException{
       ArrayList<Producto> arraylista = new ArrayList<>();
       String sql ="Select * from Producto where ProductoNom LIKE '"+productonom+"%'"+" and EmpresaId="+String.valueOf(empresaid);
       Statement stmt = objconexion.createStatement();
       ResultSet objrecordset = stmt.executeQuery(sql);
    while(objrecordset.next()){
         Producto objProducto = new Producto(); 
        objProducto.setProductocod(objrecordset.getInt("ProductoCod"));
        objProducto.setProductonom(objrecordset.getString("ProductoNom"));
        objProducto.setProductoprevent(objrecordset.getInt("ProductoPrecioVenta"));
        objProducto.setProductoiva(objrecordset.getInt("ProductoAfectoExento"));
        objProducto.setProductoid(objrecordset.getInt("ProductoId"));
      /*
        objProducto.setCliprovema(objrecordset.getString("CliProvEma"));
        objProducto.setCliprovraz(objrecordset.getString("CliProvRaz"));
        objProducto.setCliprovfon(objrecordset.getString("CliProvFon"));
        objProducto.setCliprovgir(objrecordset.getString("CliProvGir"));
        objProducto.setCliprovrut(objrecordset.getString("CliProvRut"));
       */ 
       arraylista.add(objProducto);
        
        
        
    }
        return arraylista;
}


public void updateProducto(Producto objProducto) throws SQLException{
    String sql; 
    int productocod = objProducto.getProductocod();
    String productonom = objProducto.getProductonom();
    int productoprecioventa = objProducto.getProductoprevent();
    int productoiva = objProducto.getProductoiva();
    
    
    sql = "Update Producto  \n"+
    "SET ProductoCod="+String.valueOf(productocod)+",\n"+
    "ProductoNom='"+productonom+"', \n"+
     "ProductoPrecioVenta="+productoprecioventa+",\n"+
    "ProductoAfectoExento="+productoiva+"\n"+
     "where ProductoCod="+String.valueOf(productocod);       
            
    Statement stm = objconexion.createStatement();
    stm.execute(sql);
    
   
    
    
   
}





 public int getProductoCod() throws SQLException{
       
    Statement stm = objconexion.createStatement();
    String sql = "Select ProductoCod from Correlativo where EmpresaId="+ String.valueOf(this.empresaid);
    ResultSet objrecordset = stm.executeQuery(sql);
    int productocod = 0;
    
    while(objrecordset.next()){
        productocod = objrecordset.getInt("ProductoCod");
    }
    return productocod;
    }

 
 private void updateCorrelativo() throws SQLException{
     
   Statement stm = objconexion.createStatement();
    String sql = "Update Correlativo set ProductoCod=ProductoCod+1 where EmpresaId="+ String.valueOf(this.empresaid);
    stm.execute(sql);
   
     
     
 }















}
