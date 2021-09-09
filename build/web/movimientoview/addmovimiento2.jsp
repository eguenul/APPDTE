<%@page import="java.util.List"%>
<%@page import="appventas.documento.Documento"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
 CliProv objcliprov = (CliProv)request.getSession().getAttribute("objcliprov");   
String login = (String ) request.getSession().getAttribute("login");   
%>
 <html>
 <head>           
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />
<script src="scripts/ajax.js"></script>
<script type="text/javascript" src="scripts/Numeros.js"></script>
<script type="text/javascript" src="scripts/Movimiento2.js"></script>
<title>JSP Page</title>
</head>
        <body>
            <div align="center">
            <h1>EMISION DOCUMENTO CON REFERENCIA</h1>
            </div>
           
               
<form id="formmovimiento" action="movimiento2" name="formmovimiento" method="POST">

            <table id="tabla1" class="table table-bordered table-striped table-highlight">
     
<tr>
<th colspan="5">DATOS CLIENTE/PROVEEDOR</th>
</tr>
   <tr>
      <td colspan="5" align="right">
          <button <% if("admin".equals(login)==true){ %> disabled <% } %> onClick="if(ValidaFormulario()==true){ ACC.value='LISTAR'; document.formmovimiento.submit(); }" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-floppy-disk"></span>Grabar
          </button> 
          
          <button type="button" name="btnLimpiar" onClick="window.location.href='movimiento2';" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-file"></span>Nuevo 
           </button>            
              <button onclick="window.location='index.jsp';" type="button" name="btnLimpiar" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-home"></span>Home 
           </button>
        </td>
    </tr>
    <tr>
      <td>CODIGO CLIENTE/PROVEEDOR</td>
      <td><input <% if(objcliprov.getCliprovcod()==0){} else{  %> value="<% out.print(objcliprov.getCliprovcod()); } %>"  name="CliProvCod" id="CliProvCod" readonly>
          <button name="btnListadoCliProv" type="button" id="btnListadoCliProv" data-toggle="modal" data-target="#divcliprov"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-search"></span>Buscar
        </button>
      </td>
      <td colspan="2">RUT CLIENTE/PROVEEDOR</td>
      <td><input value="<% out.print(objcliprov.getCliprovrut()); %>"  readonly>
      </td>
    </tr>
    <tr>
      <td>RAZON SOCIAL</td>
      <td><input value="<% out.print(objcliprov.getCliprovraz()); %>"  readonly></td>
      <td colspan="2">GIRO</td>
      <td><input value="<% out.print(objcliprov.getCliprovgir()); %>"  readonly></td>
    </tr>
    <tr>
      <td>COMUNA</td>
      <td><input value="<% out.print(objcliprov.getCliprovcom()); %>"   readonly></td>
      <td colspan="2">CIUDAD</td>
      <td><input value="<% out.print(objcliprov.getCliprovciu()); %>"   readonly></td>
    </tr>
    <tr>
    <td>DIRECCION</td>
    <td><input value="<% out.print(objcliprov.getCliprovdir()); %>"  readonly></td>
    <td colspan="2">FONO</td>
    <td><input value="<% out.print(objcliprov.getCliprovfon()); %>"   readonly></td>
   </tr>
   <tr>
      <td>TIPO DOCUMENTO</td>
           
      <td>
          
          
          <select class="style-select"  name="TipoDocumento" id="TipoDocumento">
          
          <% 

 List<Documento> documentos = (ArrayList<Documento>)request.getSession(true).getAttribute("servletName");
 
    for(Documento documento : documentos)
    { %>

    <option value="<% out.print(documento.getIddoc()); %>"><% out.print(documento.getNombredoc()); %></option>
   <%   
   }
 
%>
         
          
          
            </select>
      <td colspan="2">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
   </table>
<input type="hidden" id="ACC" name="ACC">
   </form>
              
            
<%@include file="../cliprovview/divlistacliprov.jsp" %>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        </body>
    </html>
