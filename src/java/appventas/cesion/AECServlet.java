/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.cesion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author esteban
 */
public class AECServlet extends HttpServlet {
    @Override
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
      if(request.getSession().getAttribute("loginauth")==null){
         request.getRequestDispatcher("login").forward(request, response); 
       }
        request.getSession().setAttribute("TIPOCESION","AEC");
    
         
         
         
         request.getSession().setAttribute("modulo","cesion");
     getServletConfig().getServletContext().getRequestDispatcher("/cesionview/uploadfile.jsp").forward(request,response);
                 
    
}



}