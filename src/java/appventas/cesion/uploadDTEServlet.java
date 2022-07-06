/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.cesion;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.appdte.sii.utilidades.ConfigAppDTE;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class uploadDTEServlet extends HttpServlet {
   
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            String login=  (String) request.getSession().getAttribute("login");
            
            ConfigAppDTE objconfig = new ConfigAppDTE();
             String  pathdata = objconfig.getPathdata();
             String name="";
            if(ServletFileUpload.isMultipartContent(request)){
                try {
                    List<FileItem> multiparts = new ServletFileUpload(
                            new DiskFileItemFactory()).parseRequest(request);
                    
                    for(FileItem item : multiparts){
                        if(!item.isFormField()){
                            name = new File(item.getName()).getName();
                            item.write( new File(pathdata+login + name));
                             
                            System.out.print(name);
                        }
                    }
                    
                    //File uploaded successfully
                      System.out.print("dte subido");
                } catch (Exception ex) {
                    request.setAttribute("message", "File Upload Failed due to " + ex);
                }
                
            }else{
                request.setAttribute("message","Sorry this Servlet only handles file upload request");
            }
            
              
           request.getSession().setAttribute("pathdte",pathdata+login+name);
         response.sendRedirect("datadte");
            
            
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(uploadDTEServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
     
        
*/  
     
    }
    
    
    }
    

      

