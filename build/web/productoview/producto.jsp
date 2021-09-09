<%
int flagiva = Integer.parseInt((String) request.getSession().getAttribute("flagiva"));
%>
<%
String login = (String)request.getSession().getAttribute("login");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />
<script src="scripts/ajax.js"></script>
<script type="text/javascript" src="scripts/Numeros.js"></script>
<script type="text/javascript" src="scripts/Producto.js"></script>


<title>JSP Page</title>
    </head>
    <body>
        <div align="center">
       
            <h1>MANTENCION PRODUCTO</h1>
            <div id="divresultado"></div>
        <form method="POST" name="formproducto">
            <table  class="table table-bordered table-striped table-highlight">
           <tr> 
           
               <th colspan="2">DATOS GENERALES</th>
           </tr>
           <tr>
                <td align="right" colspan="2">
          
           <button <% if("admin".equals(login)==false){ %> disabled <% } %> onClick="grabarProducto();" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-floppy-disk"></span>Grabar
          </button> 
          
          <button type="button" name="btnLimpiar" onClick="window.location.href='producto';" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-file"></span>Nuevo 
           </button>
                    <button onclick="report();" type="button" name="btnListado"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-print"></span>Listado 
           </button>
                    <button onclick="window.location='index.jsp';" type="button" name="btnHome"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-home"></span>Home 
           </button>
         
                
                
          </td>
       </tr>
           <tr>
                <td>
                    CODIGO PRODUCTO
                </td>
                <td>
                    <input readonly value="<% out.print(request.getSession(true).getAttribute("productocod")); %>" name="ProductoCod" id="ProductoCod"> 
                 <button name="btnListadoCliProv" type="button" id="btnListadoCliProv" data-toggle="modal" data-target="#divproducto"    class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-search"></span>Buscar
        </button>
                
                </td>
            </tr>
            <tr>
                <td>
                    NOMBRE PRODUCTO
                </td>
                <td>
                    <input value="<% out.print(request.getSession(true).getAttribute("productonom")); %>" id="ProductoNom"> 
                </td>
            </tr>
            <tr>
                <td>
                    PRECIO COMPRA
                </td>
                <td>
                    <input onkeypress="return isNumberKey(event);"  value="<% out.print(request.getSession(true).getAttribute("preciocompra")); %>" id="PrecioCompra"> 
                </td>
            </tr>
            
            <tr>
                <td>
                    PRECIO VENTA
                </td>
                <td>
                    <input onkeypress="return isNumberKey(event);"  value="<% out.print(request.getSession(true).getAttribute("precioventa")); %>" id="PrecioVenta"> 
                </td>
            </tr>
            <tr>
                <td>
                    IVA
                </td>
                <td>
                    <input <% if(flagiva==1){ %> checked <% } %>   value="1" type="checkbox" id="Iva"> 
                </td>
            </tr>
        </table>
                <input type="hidden" value="<% out.print(request.getSession(true).getAttribute("acc")); %>" id="ACC" name="ACC">
            </form>
        
            </div>
            
          
        <%@include file="../productoview/divlistaproducto.jsp" %>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>
