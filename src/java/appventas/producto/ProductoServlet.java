/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.producto;
import com.appdte.sii.utilidades.ConfigClass;
import appventas.report.Report;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JRException;
import org.xml.sax.SAXException;

/**
 *
 * @author esteban
 */
public class ProductoServlet  extends HttpServlet  {
     @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
         int empresaid = (int) request.getSession().getAttribute("empresaid");
      
             String acc = request.getParameter("ACC");
      try {
             if("GRABAR".equals(acc)){
              
                        
                 
                     ProductoModel objProductoModel = new ProductoModel(empresaid);
                     Producto objProducto = new Producto();
                     
                     
                    /* */
                     objProducto.setProductocod(objProductoModel.getProductoCod());
                     
                     objProducto.setProductonom(request.getParameter("ProductoNom"));
                     objProducto.setProductoprecom(Integer.parseInt(request.getParameter("PrecioCompra")));
                     objProducto.setProductoprevent(Integer.parseInt(request.getParameter("PrecioVenta")));
                     objProducto.setProductoiva(Integer.parseInt(request.getParameter("Iva")));
                     objProductoModel.addProducto(objProducto);
                  
                     PrintWriter out = response.getWriter();
                     out.print("<div class=\"alert alert-success\" role=\"alert\"" +">");
                out.print("REGISTRO DE PRODUCTOS ACTUALIZADO");
                out.print("</div>");
              
                    
                    
             }
             
             
              if("LISTADO".equals(acc)){
                     
                  
                ConfigClass objconfig = new ConfigClass();
                Report objReport = new Report("productos",objconfig.getPathdownload(),"PRODUCTOS"+String.valueOf(31) +"."+"pdf");
                request.getSession().setAttribute("nombredocumento", "PRODUCTOS"+String.valueOf(31));
                objReport.setParameters("parmEmpresaId", String.valueOf(empresaid));
                objReport.showReport();
                
            PrintWriter out = response.getWriter();
            out.print("<div class=\"container\">");
            out.print("<div class=\"alert alert-success\">");
            out.print("<strong>Iniciar descarga</strong>");
            out.print ("<a target=\"blank\" href=\"pdfservlet\"  class=\"btn btn-primary btn-sm\">");
            out.print("<span class=\"glyphicon glyphicon-download\"></span>Descargar"); 
            out.print("</a>");
            out.print("</div>");
            out.print("</div>");
              }
             
          
             if("UPDATE".equals(acc)){
                     ProductoModel objProductoModel = new ProductoModel(empresaid);
                     Producto objProducto = new Producto();
                     objProducto.setProductocod(Integer.parseInt(request.getParameter("ProductoCod")));
                     
                     objProducto.setProductonom(request.getParameter("ProductoNom"));
                     objProducto.setProductoprecom(Integer.parseInt(request.getParameter("PrecioCompra")));
                     objProducto.setProductoprevent(Integer.parseInt(request.getParameter("PrecioVenta")));
                     objProducto.setProductoiva(Integer.parseInt(request.getParameter("Iva")));
                      objProductoModel.updateProducto(objProducto);
                       request.getSession().setAttribute("productocod", "");
                 request.getSession().setAttribute("productonom","");
                 request.getSession().setAttribute("preciocompra","");
                 request.getSession().setAttribute("precioventa", "");
                 request.getSession().setAttribute("flagiva", "1");
                 
                      request.getSession().setAttribute("acc","GRABAR");
                      PrintWriter out = response.getWriter();
                   
               out.print("<div class=\"alert alert-success\" role=\"alert\"" +">");
                out.print("REGISTRO DE PRODUCTOS ACTUALIZADO");
                out.print("</div>");
                   
                     
              }
             
             
             
             if("BUSCAR".equals(acc)){
                 ProductoModel  objProductoModel = new ProductoModel(empresaid);
                 ArrayList<Producto>  arrayproducto = objProductoModel.listProducto(0);
                 request.getSession().setAttribute("arrayproducto",arrayproducto);
                 request.getSession().setAttribute("acc","UPDATE");
                 
                Producto objProducto = objProductoModel.searchProducto(Integer.parseInt(request.getParameter("ProductoCod")));
                 
                 
                 
                 request.getSession().setAttribute("productocod", objProducto.getProductocod());
                 request.getSession().setAttribute("productonom", objProducto.getProductonom());
                 request.getSession().setAttribute("preciocompra", objProducto.getProductoprecom());
                 request.getSession().setAttribute("precioventa", objProducto.getProductoprevent());
                 request.getSession().setAttribute("flagiva", String.valueOf(objProducto.getProductoiva()));
                 
                 
                 getServletConfig().getServletContext().getRequestDispatcher("/productoview/producto.jsp").forward(request,response);
       
                 
             }
             
          
             if("BUSQUEDACOD".equals(acc)){
                 int productocod = Integer.parseInt(request.getParameter("ProductoCod"));
                 ProductoModel objProductoModel = new ProductoModel(empresaid);
                 ArrayList<Producto> arrayproducto = objProductoModel.searchCod(productocod);
                 request.getSession().setAttribute("arrayproducto",arrayproducto);
                 getServletConfig().getServletContext().getRequestDispatcher("/productoview/divlistaproducto2.jsp").forward(request,response);
         
             
             }
             
             if("BUSQUEDANOM".equals(acc)){
                 String productonom = request.getParameter("ProductoNom");
                 ProductoModel objProductoModel = new ProductoModel(empresaid);
                 ArrayList<Producto> arrayproducto = objProductoModel.searchNom(productonom);
                 request.getSession().setAttribute("arrayproducto",arrayproducto);
                 getServletConfig().getServletContext().getRequestDispatcher("/productoview/divlistaproducto2.jsp").forward(request,response);
         
             }
             
              if("REFRESH".equals(acc)){
                 ProductoModel objProductoModel = new ProductoModel(empresaid);
                 ArrayList<Producto> arrayproducto = objProductoModel.listProducto(0);
                 request.getSession().setAttribute("arrayproducto",arrayproducto);
                 getServletConfig().getServletContext().getRequestDispatcher("/productoview/divlistaproducto2.jsp").forward(request,response);     
             }
             
             
             
             

             

             
             
           } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException | JRException ex) {
                     Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }
     
     
    

     }
     
     

@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
      if(request.getSession(true).getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
       
    try {
        int empresaid = (int) request.getSession().getAttribute("empresaid"); 
        ProductoModel  objProductoModel = new ProductoModel(empresaid);
         ArrayList<Producto>  arrayproducto = objProductoModel.listProducto(0);
         request.getSession().setAttribute("arrayproducto",arrayproducto);
         request.getSession().setAttribute("acc","GRABAR");
        
         request.getSession().setAttribute("modulo", "producto");
         
                request.getSession().setAttribute("productocod", "");
                 request.getSession().setAttribute("productonom","");
                 request.getSession().setAttribute("preciocompra","");
                 request.getSession().setAttribute("precioventa", "");
                 request.getSession().setAttribute("flagiva", "1");
                 
         getServletConfig().getServletContext().getRequestDispatcher("/productoview/producto.jsp").forward(request,response);
            
         } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
             Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
                
    
}

}
