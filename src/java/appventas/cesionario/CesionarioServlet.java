/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.cesionario;

import appventas.include.comonFunc;
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
import org.xml.sax.SAXException;


public class CesionarioServlet extends HttpServlet {
    
@Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
     

   
    
    try {
        Cesionario objcesionario = new Cesionario();
        CesionarioModel objCesionarioModel = new CesionarioModel();
        String acc = request.getParameter("ACC");
        switch(acc){
            
            
            case "REFRESH": 
                
                
                            ArrayList<Cesionario> arraycesionario;
                            arraycesionario = objCesionarioModel.listCesionario();
        
                            request.getSession().setAttribute("arraylistcesionario",arraycesionario);
                            getServletConfig().getServletContext().getRequestDispatcher("/cesionarioview/divlistacesionario2.jsp").forward(request,response);
 
            
                           break;
            
               
            case "BUSCAR":
                
                System.out.print("buscando");
                String stringcod = (String) request.getParameter("CesionarioCod");
                int cesionariocod = Integer.parseInt(stringcod);
                objcesionario = objCesionarioModel.showCesionario(cesionariocod);
                request.getSession().setAttribute("Cesionario", objcesionario);
                
                request.getSession().setAttribute("CesionarioRzsc",objcesionario.getCesionariorzsc());
                request.getSession().setAttribute("CesionarioDir",objcesionario.getCesionariodir());
                request.getSession().setAttribute("CesionarioEmail",objcesionario.getCesionarioemail());
                request.getSession().setAttribute("CesionarioRut",objcesionario.getCesionariorut());
                request.getSession().setAttribute("CesionarioCod",objcesionario.getCesionariocod());
                request.getSession().setAttribute("ACC", "UPDATE");
                
               
                getServletConfig().getServletContext().getRequestDispatcher("/cesionarioview/formcesionario.jsp").forward(request,response);
                
                /*
                ArrayList<Cesionario> arraycesionario = objCesionarioModel.listCesionario();
                request.getSession().setAttribute("arraylistcesionario",arraycesionario);
                */
                break;
                /*
                case "SELECT":
                break;
                */
            case "GRABAR":
                objcesionario.setCesionariodir((String) request.getParameter("CesionarioDir"));
                objcesionario.setCesionariorzsc((String) request.getParameter("CesionarioRzsc"));
                objcesionario.setCesionariorut((String) request.getParameter("CesionarioRut"));
                objcesionario.setCesionarioemail((String) request.getParameter("CesionarioEmail"));
                
                
               if(comonFunc.validaRut(objcesionario.getCesionariorut())==true){
          
                objCesionarioModel.addCesionario(objcesionario);
                request.getSession().setAttribute("CesionarioRzsc","");
                request.getSession().setAttribute("CesionarioDir","");
                request.getSession().setAttribute("CesionarioEmail","");
                request.getSession().setAttribute("CesionarioRut","");
                request.getSession().setAttribute("CesionarioCod","");
                   request.getSession().setAttribute("estado", "OK");
                   }else{
                       
                        request.getSession().setAttribute("estado", "RUTERROR");
                 
                   }
                getServletConfig().getServletContext().getRequestDispatcher("/cesionarioview/formcesionario.jsp").forward(request,response);
                
                
                
                break;
                
                
            case "UPDATE":
                
                System.out.print("actualizando cesionarios");
                
                String stringcod2 = (String) request.getParameter("CesionarioCod");
                int cesionariocod2 = Integer.parseInt(stringcod2);
                objcesionario.setCesionariocod(cesionariocod2);
                objcesionario.setCesionariodir((String) request.getParameter("CesionarioDir"));
                objcesionario.setCesionariorzsc((String) request.getParameter("CesionarioRzsc"));
                objcesionario.setCesionariorut((String) request.getParameter("CesionarioRut"));
                objcesionario.setCesionarioemail((String) request.getParameter("CesionarioEmail"));
                
                   if(comonFunc.validaRut(objcesionario.getCesionariorut())==true){
          
                objCesionarioModel.updateCesionario(objcesionario);
                
                request.getSession().setAttribute("ACC", "GRABAR");
                  request.getSession().setAttribute("CesionarioRzsc","");
                request.getSession().setAttribute("CesionarioDir","");
                request.getSession().setAttribute("CesionarioEmail","");
                request.getSession().setAttribute("CesionarioRut","");
                request.getSession().setAttribute("CesionarioCod","");
                request.getSession().setAttribute("estado", "OK");
                
                   }else{
                        request.getSession().setAttribute("estado", "RUTERROR");      
                   }
                    
                getServletConfig().getServletContext().getRequestDispatcher("/cesionarioview/formcesionario.jsp").forward(request,response);
                
                
                break;
                
            case "BUSQUEDACOD":
                
                
                System.out.print("BUSCANDO CESIONARIO");
                if(request.getParameter("CesionarioCod").isEmpty()==true){
                    
                }else{
                    
                    ArrayList<Cesionario> arraylistcesionario = objCesionarioModel.searchCod(Integer.parseInt(request.getParameter("CesionarioCod")));
                    request.getSession().setAttribute("arraylistcesionario", arraylistcesionario);
                  
                      request.getSession().setAttribute("CesionarioRzsc","");
                request.getSession().setAttribute("CesionarioDir","");
                request.getSession().setAttribute("CesionarioEmail","");
                request.getSession().setAttribute("CesionarioRut","");
                request.getSession().setAttribute("CesionarioCod","");
             
                    
                    
                    
                    getServletConfig().getServletContext().getRequestDispatcher("/cesionarioview/divlistacesionario2.jsp").forward(request,response);
                }
                
                break;
                
                
            case "BUSQUEDARAZ":
                
                
                System.out.print("BUSCANDO CESIONARIO");
                if(request.getParameter("CesionarioRzsc").isEmpty()==true){
                    
                }else{
                    String cesionariorzsc = request.getParameter("CesionarioRzsc");
                  
                    ArrayList<Cesionario> arraylistcesionario = objCesionarioModel.searchRaz(cesionariorzsc);
                  
                     request.getSession().setAttribute("arraylistcesionario", arraylistcesionario); 
                  
                  
                  getServletConfig().getServletContext().getRequestDispatcher("/cesionarioview/divlistacesionario2.jsp").forward(request,response);
               
                
                }
                
                break;
                
            case "BUSQUEDARUT":
                
                System.out.print("BUSCANDO CESIONARIO");
                if(request.getParameter("CesionarioRut").isEmpty()==true){
                    
                }else{
                    
                    String cesionariorut = request.getParameter("CesionarioRut");
                    
                    if(comonFunc.validaRut(cesionariorut)==true){
                        
                        
                        ArrayList<Cesionario> arraylistcesionario = objCesionarioModel.searchRut(cesionariorut);
                        request.getSession().setAttribute("arraylistcesionario", arraylistcesionario);
                        getServletConfig().getServletContext().getRequestDispatcher("/cesionarioview/divlistacesionario2.jsp").forward(request,response);
                    }else{
                        
                         PrintWriter out = response.getWriter();
                   //ya podemos enviar al navegador
                   String mensaje = "<div class=\"alert alert-danger\"> <strong>ERROR</strong> RUT NO VALIDO</div>";
                   out.println(mensaje);
                    }
                    
                    break;
                    
                    
                    
                    
                }
                
        }  
    } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
        Logger.getLogger(CesionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
}      


@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 if(request.getSession().getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
     
                
                
    try {
        CesionarioModel objCesionarioModel2 = new CesionarioModel();
        ArrayList<Cesionario> arraycesionario;
        
        arraycesionario = objCesionarioModel2.listCesionario();
        
        request.getSession().setAttribute("arraylistcesionario",arraycesionario);
        request.getSession().setAttribute("ACC","GRABAR");
        
        
         request.getSession().setAttribute("Cesionario", "");
                
                request.getSession().setAttribute("CesionarioRzsc","");
                request.getSession().setAttribute("CesionarioDir","");
                request.getSession().setAttribute("CesionarioEmail","");
                request.getSession().setAttribute("CesionarioRut","");
                request.getSession().setAttribute("CesionarioCod","");
               
        
        
        
        
        
        
        
        
        getServletConfig().getServletContext().getRequestDispatcher("/cesionarioview/cesionario.jsp").forward(request,response);
    } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
        Logger.getLogger(CesionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
                
                
            }
}
      