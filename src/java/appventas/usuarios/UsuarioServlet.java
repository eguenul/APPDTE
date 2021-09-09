/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.usuarios;

import appventas.include.comonFunc;
import java.io.IOException;
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

/**
 *
 * @author esteban
 */
public class UsuarioServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if(request.getSession().getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
        try {
            request.getSession().setAttribute("ACC", "GRABAR");
            
            Usuario objUsuario = new Usuario();
            objUsuario.setLogin("");
            objUsuario.setRut("");
            objUsuario.setUsuarionom("");
            objUsuario.setUsuarioap("");
            objUsuario.setEmail("");
            objUsuario.setPassword("");
            
            UsuarioModel objUsuarioModel = new UsuarioModel();
            
            ArrayList<Usuario> arraylistusuario = objUsuarioModel.listUsuario();
            
            request.getSession().setAttribute("arraylistusuario", arraylistusuario);
            
            
            request.getSession().setAttribute("objUsuario", objUsuario);
            getServletConfig().getServletContext().getRequestDispatcher("/usuarioview/addusuario.jsp").forward(request,response);
        } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String acc = request.getParameter("ACC");
            
            Usuario objUsuario = new Usuario();
            UsuarioModel objUsuarioModel = new UsuarioModel();
            
            switch (acc){
                
                
                case "GRABAR":
                    
                 if(comonFunc.validaRut(request.getParameter("UsuarioRut"))==true){   
                    objUsuario.setLogin(request.getParameter("UsuarioLogin"));
                    objUsuario.setRut(request.getParameter("UsuarioRut"));
                    objUsuario.setUsuarionom(request.getParameter("UsuarioNombre"));
                    objUsuario.setUsuarioap(request.getParameter("UsuarioApellido"));
                    objUsuario.setPassword(request.getParameter("UsuarioPass"));
                    objUsuario.setEmail(request.getParameter("UsuarioEmail"));
                    objUsuarioModel.addUsuario(objUsuario);
                    
                    objUsuario.setLogin("");
                    objUsuario.setRut("");
                    objUsuario.setUsuarionom("");
                    objUsuario.setUsuarioap("");
                    objUsuario.setEmail("");
                    objUsuario.setPassword("");
                    request.getSession().setAttribute("objUsuario", objUsuario);
                    request.getSession().setAttribute("estado", "OK");
                    request.getSession().setAttribute("ACC", "GRABAR");
                  
                 }else{
                      
                       request.getSession().setAttribute("estado", "ERROR");
             
                        objUsuario.setLogin(request.getParameter("UsuarioLogin"));
                        objUsuario.setRut(request.getParameter("UsuarioRut"));
                        objUsuario.setUsuarionom(request.getParameter("UsuarioNombre"));
                        objUsuario.setUsuarioap(request.getParameter("UsuarioNombre"));
                        objUsuario.setEmail(request.getParameter("UsuarioEmail"));
                        objUsuario.setPassword(request.getParameter("UsuarioPass"));
                        request.getSession().setAttribute("objUsuario", objUsuario);
                        request.getSession().setAttribute("ACC", "GRABAR");
                  
                        
                 }
                    getServletConfig().getServletContext().getRequestDispatcher("/usuarioview/formusuario.jsp").forward(request,response);
             
                    break;
                
                    
                    
                case "UPDATE":
                    
                 if(comonFunc.validaRut(request.getParameter("UsuarioRut"))==true){   
                    objUsuario.setLogin(request.getParameter("UsuarioLogin"));
                    objUsuario.setRut(request.getParameter("UsuarioRut"));
                    objUsuario.setUsuarionom(request.getParameter("UsuarioNombre"));
                    objUsuario.setUsuarioap(request.getParameter("UsuarioApellido"));
                    objUsuario.setPassword(request.getParameter("UsuarioPass"));
                    objUsuario.setEmail(request.getParameter("UsuarioEmail"));
                    objUsuarioModel.updateUsuario(objUsuario);
                            
                    objUsuario.setLogin("");
                    objUsuario.setRut("");
                    objUsuario.setUsuarionom("");
                    objUsuario.setUsuarioap("");
                    objUsuario.setEmail("");
                    objUsuario.setPassword("");      
                    
                    request.getSession().setAttribute("objUsuario", objUsuario);
                    request.getSession().setAttribute("estado", "OK");
                      request.getSession().setAttribute("ACC", "GRABAR");
                     }else{
                      
                       request.getSession().setAttribute("estado", "ERROR");
             
                        objUsuario.setLogin(request.getParameter("UsuarioLogin"));
                        objUsuario.setRut(request.getParameter("UsuarioRut"));
                        objUsuario.setUsuarionom(request.getParameter("UsuarioNombre"));
                        objUsuario.setUsuarioap(request.getParameter("UsuarioNombre"));
                        objUsuario.setEmail(request.getParameter("UsuarioEmail"));
                        objUsuario.setPassword(request.getParameter("UsuarioPass"));
                        request.getSession().setAttribute("objUsuario", objUsuario);
                         request.getSession().setAttribute("ACC", "UPDATE");
                    
                 }
                        getServletConfig().getServletContext().getRequestDispatcher("/usuarioview/formusuario.jsp").forward(request,response);
             
                    break;
                    
                    
                    
                    
                    
                    
                    
                 case "BUSCAR":
                       objUsuario = objUsuarioModel.getUsuario(request.getParameter("UsuarioLogin"));
                       request.getSession().setAttribute("objUsuario", objUsuario);
                        request.getSession().setAttribute("ACC", "UPDATE");
                       
                       getServletConfig().getServletContext().getRequestDispatcher("/usuarioview/formusuario.jsp").forward(request,response);

                       break;
                
            case "BUSQUEDARUT":
                         if(request.getParameter("UsuarioRut").isEmpty()==true){
                    
                         }else{
                              String usuariologin = request.getParameter("UsuarioRut");
                         ArrayList<Usuario> arraylistusuario = objUsuarioModel.searchRut(usuariologin);
                        request.getSession().setAttribute("arraylistusuario", arraylistusuario);
                        getServletConfig().getServletContext().getRequestDispatcher("/usuarioview/divlistusuario2.jsp").forward(request,response);
                
                              }
                break;
                
            case "BUSQUEDALOGIN":
                
                System.out.print("BUSCANDO USUARIO");
                if(request.getParameter("UsuarioLogin").isEmpty()==true){
                    
                }else{
                    
                    String usuariologin = request.getParameter("UsuarioLogin");
                    
                       
                        
                        ArrayList<Usuario> arraylistusuario = objUsuarioModel.searchLogin(usuariologin);
                        request.getSession().setAttribute("arraylistusuario", arraylistusuario);
                        getServletConfig().getServletContext().getRequestDispatcher("/usuarioview/divlistusuario2.jsp").forward(request,response);
                    }
                  
                    break;
                              
             case "BUSQUEDANOM":
                
                System.out.print("BUSCANDO USUARIO");
                if(request.getParameter("UsuarioNom").isEmpty()==true){
                    
                }else{
                    
                    String usuarionom = request.getParameter("UsuarioNom");
                    
                       
                        
                        ArrayList<Usuario> arraylistusuario = objUsuarioModel.searchNom(usuarionom);
                        request.getSession().setAttribute("arraylistusuario", arraylistusuario);
                        getServletConfig().getServletContext().getRequestDispatcher("/usuarioview/divlistusuario2.jsp").forward(request,response);
                    }
                  
                    break;
                    
                    
                     case "BUSQUEDAAP":
                
                System.out.print("BUSCANDO USUARIO");
                if(request.getParameter("UsuarioApellido").isEmpty()==true){
                    
                }else{
                    
                    String usuarioapellido = request.getParameter("UsuarioApellido");
                    
                       
                        
                        ArrayList<Usuario> arraylistusuario = objUsuarioModel.searchApellido(usuarioapellido);
                        request.getSession().setAttribute("arraylistusuario", arraylistusuario);
                        getServletConfig().getServletContext().getRequestDispatcher("/usuarioview/divlistusuario2.jsp").forward(request,response);
                    }
                  
                    break;
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
            }           
            } catch (SQLException | ClassNotFoundException | ParserConfigurationException | SAXException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
}
