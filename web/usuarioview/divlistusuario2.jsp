<%@page import="appventas.usuarios.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<table class="table">
       <thead>    
<tr>
    <th>LOGIN</th>

    <th>RUT</th>
    <th>NOMBRE USUARIO</th>
     <th>APELLIDO</th>
   
  </tr>
       </thead>
       
   <%     List<Usuario> usuarios = (ArrayList<Usuario>)request.getSession(true).getAttribute("arraylistusuario");
 
    for(Usuario usuario : usuarios){
 %>
<tr>
    <td onclick="buscarUsuario('<% out.print(usuario.getLogin()); %>');"><% out.print(usuario.getLogin()); %></td>
    <td><% out.print(usuario.getRut()); %></td>
    <td><% out.print(usuario.getUsuarionom()); %></td>
    <td><% out.print(usuario.getUsuarioap());
     %></td>
     
  </tr>
  <% } %>
</table>