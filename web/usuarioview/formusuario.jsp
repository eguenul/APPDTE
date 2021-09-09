
<%@page import="appventas.usuarios.Usuario"%>
<%
    Usuario objUsuario = (Usuario) request.getSession().getAttribute("objUsuario");  

String login = (String) request.getSession().getAttribute("login");


%>


<%
           if((String)request.getSession().getAttribute("estado")=="ERROR"){
       %> 
       
<div class="alert alert-danger">
  <strong>OPERACION FALLIDA</strong> RUT ERRONEO.
</div>
      <%  request.getSession().setAttribute("estado",null);             
           }       
       %> 

<%
           if((String)request.getSession().getAttribute("estado")=="OK"){
       %> 
       
<div class="alert alert-success">
  <strong>OPERACION REALIZADA</strong> REGISTRO DE USUARIOS ACTUALIZADO.
</div>
      <%  request.getSession().setAttribute("estado",null);             
           }       
       %>        
       
       
       
       
       
<form name="formusuario" id="formusuario" method="POST">
<table class="table table-bordered table-striped">
    <tr>
    <th colspan="2">
        DATOS DE USUARIO
    </th>
    </tr>
    <tr>
    <td align="right" colspan="2">
        <button <% if("admin".equals(login)==false){ %> disabled <% } %> type="button" onclick="grabarUsuario();" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-floppy-disk"></span>Grabar
          </button> 
    
          <button type="button" name="btnLimpiar" onClick="window.location.href='usuario';" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-file"></span>Nuevo 
           </button>
                     
              <button onclick="window.location='index.jsp';" type="button" name="btnLimpiar" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-home"></span>Home 
           </button>
        </td>
    </tr>
    
    <tr>
        <td>LOGIN</td>
        <td><input value="<% out.print(objUsuario.getLogin()); %>" name="UsuarioLogin" id="UsuarioLogin">
            <button   name="btnListadoCliProv" type="button" id="btnListadoCliProv" data-toggle="modal" data-target="#divlistusuario"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-search"></span>Buscar 
        </button>
        </td>
    </tr>
    <tr>
        <td>RUT</td>
        <td><input name="UsuarioRut" value="<% out.print(objUsuario.getRut()); %>" id="UsuarioRut"></td>
    </tr>
    <tr>
        <td>NOMBRES</td>
        <td><input name="UsuarioNombre" value="<% out.print(objUsuario.getUsuarionom()); %>" id="UsuarioNombre"></td>
    </tr>
     <tr>
        <td>APELLIDOS</td>
        <td><input name="UsuarioApellido" value="<% out.print(objUsuario.getUsuarioap()); %>" id="UsuarioApellido"></td>
    </tr>
    <tr>
        <td>PASSWORD</td>
        <td><input name="UsuarioPass" value="<% out.print(objUsuario.getPassword()); %>" id="UsuarioPass"></td>
    </tr>
    <tr>
        <td>EMAIL</td>
        <td><input name="UsuarioEmail" value="<% out.print(objUsuario.getEmail()); %>" id="UsuarioEmail"></td>
    </tr>
    
    
    
</table>
    <input type="hidden" value="<% out.print(request.getSession().getAttribute("ACC")); %>" name="ACC" id="ACC">
</form>