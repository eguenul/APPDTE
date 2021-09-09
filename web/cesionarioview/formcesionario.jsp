<%      String login = (String) request.getSession().getAttribute("login");
 %>  
<div id="reporte">
<%
           if((String)request.getSession().getAttribute("estado")=="OK"){
       %> 
       
<div class="alert alert-success">
  <strong>OPERACION REALIZADA</strong> REGISTRO DE CESIONARIOS ACTUALIZADO
</div>
      <%  request.getSession().setAttribute("estado",null);             
           }       
       %>
<%
           if((String)request.getSession().getAttribute("estado")=="RUTERROR"){
       %> 
       
<div class="alert alert-danger">
  <strong>OPERACION FALLIDA</strong> RUT ERRONEO.
</div>
      <%  request.getSession().setAttribute("estado",null);             
           }       
       %>      
       <% request.getSession().setAttribute("estado", null); %>
</div>       
       
        <form name="formcesionario" method="POST" id="formcesionario" action="">
        <table class="table table-bordered table-striped table-highlight">
              <tr>
                <th colspan="2">DATOS GENERALES</th>
              </tr>
            <tr>
                <td align="right" colspan="2">
                    <button <% if("admin".equals(login)==false){ %> disabled <% } %> onclick="grabarCesionario();"  type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-floppy-disk"></span>Grabar
          </button> 
         <!--
              <button onclick="cargarAjax('cliprov','ACC=LISTADO','reporte');" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-print"></span>Listado
          </button> 
             -->                 
                    
                    
          <button type="button" onclick="window.location='cesionario';" name="btnLimpiar" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-file"></span>Nuevo 
           </button>
                    
              <button onclick="window.location='index.jsp';" type="button" name="btnLimpiar" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-home"></span>Home 
           </button>
                     
                    
          </td>
          </tr>            
            <tr>
                <td>CODIGO</td>
                <td><input value="<% out.print(request.getSession().getAttribute("CesionarioCod")); %>" readonly id="CesionarioCod" name="CesionarioCod">
                <button   name="btnListadoCesionario" type="button" id="btnListadoCesionario" data-toggle="modal" data-target="#divcesionario"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-search"></span>Buscar 
        </button>
      </td>
            </tr>
            <tr>
                <td>RAZON SOCIAL</td>
                <td> <input value="<% out.print(request.getSession().getAttribute("CesionarioRzsc")); %>" id="CesionarioRzsc" name="CesionarioRzsc"></td>
            </tr>
            <tr>
                <td>RUT</td>
                <td> <input value="<% out.print(request.getSession().getAttribute("CesionarioRut")); %>" id="CliProvRut" name="CesionarioRut"></td>
            </tr>
            <tr>
                <td>DIRECCION</td>
                <td> <input value="<% out.print(request.getSession().getAttribute("CesionarioDir")); %>" id="CesionarioDir" name="CesionarioDir"></td>
            </tr>
            <tr>
                <td>EMAIL</td>
                <td> <input value="<% out.print(request.getSession().getAttribute("CesionarioEmail")); %>" id="CliProvEma" name="CesionarioEmail"></td>
            </tr> 
        </table>
        <% String acc = (String) request.getSession().getAttribute("ACC"); %>
            <input id="ACC" name="ACC" value="<% out.print(acc); %>" type="hidden">
        </form>
