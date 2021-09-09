<%@page import="appventas.parametros.Correlativo"%>
<%
 Correlativo objCorrelativo = (Correlativo) request.getSession().getAttribute("objCorrelativo");
 String login = (String) request.getSession().getAttribute("login");
 String estado = (String) request.getSession().getAttribute("ESTADO");
 %>
<html>
    <head>
        <title> MANTENCION CORRELATIVOS</title>
        <meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />
<script src="scripts/setcorrelativo.js"></script>  
<script src="scripts/Numeros.js"></script>  
    </head>
    <body>
        <h1>MANTENCION CORRELATIVOS</h1>   
     <% if("ok".equals(estado)==true){ %>
        
       
        <div class="alert alert-success">
  <strong>OPERACION REALIZADA: </strong>CORRELATIVOS ACTUALIZADOS.
</div>
     <%    request.getSession().setAttribute("ESTADO","null");   %>
     <% } %>   
        <form name="formcorrelativo" method="POST">
        <table  class="table table-bordered table-striped">
            <tr>
            <th colspan="2">
            CORRELATIVOS FOLIOS
            </th>
            </tr>
            <tr>
            <td align="right" colspan="2">
                  <button <% if("admin".equals(login)==false){ %> disabled <% } %> onClick="Grabar();" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-floppy-disk"></span>Grabar
          </button> 
         <button  onClick="window.location='index.jsp';" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-home"></span>Home
          </button> 
            </td>
            </tr>
            <tr>
            <td>
                  FACTURA ELECTRONICA          
            </td>
            <td>
                <input name="FacVentaAfecta" id="FacVentaAfecta" onkeypress="return isNumberKey(event);" value="<% out.print(objCorrelativo.getFacventaafecta()); %>">
            </td>
            </tr>
            <tr>          
            <td>
                  NOTA DE CREDITO ELECTRONICA        
            </td>        
          <td>
                <input name="NotaCredito" id="NotaCredito" onkeypress="return isNumberKey(event);" value="<% out.print(objCorrelativo.getNotacredito()); %>">       
            </td>   
        </tr>
        
         <tr>          
            <td>
                 FACTURA EXENTA ELECTRONICA    
            </td>        
          <td>
                <input name="FacVentaExenta" id="FacVentaExenta" onkeypress="return isNumberKey(event);" value="<% out.print(objCorrelativo.getFacventaexenta()); %>">       
            </td>   
        </tr>
        
        
        
        
        
        
        
        
        </table>
        </form>
            
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    </body>
</html>
