<%-- 
    Document   : select
    Created on : 27-nov-2018, 12:35:06
    Author     : esteban
--%>
<% 
    String codsii = (String) request.getSession().getAttribute("codsii");
    int auxcodsii = 0;
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
    <link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />  
    <script src="scripts/ajax.js"></script>
     <script src="scripts/Numeros.js"></script>
   <title>SELECCION DOCUMENTO</title>
    </head>
    <body>
        <div align="center">
        <h1>SELECCION DOCUMENTO</h1>
            </div>
            <table class="table table-striped table-bordered">
            
            <tr>
                <td align="right">   
                    
                  <select name="CodSii" id="CodSii">
     
                            
           <% if("61".equals(codsii)==true){ %>
           <% auxcodsii = 33; %>
           <option value="<% out.print(auxcodsii); %>">FACTURA ELECTRONICA</option>
            <% auxcodsii = 34; %>
           <option value="<% out.print(auxcodsii); %>">FACTURA EXENTA ELECTRONICA</option>
           
          
           
           
           <% } %>
           
                        
           
          <% if("33".equals(codsii)==true) { %>
          <% auxcodsii = 52; %>
            <option value="<% out.print(auxcodsii); %>">GUIA DE DESPACHO ELECTRONICA</option>
         <% } %>
          
            
            
     
             </select>
         <input onkeypress="return isNumberKey(event);" name="NumDoc" id="NumDoc">
            <button onclick="if(document.getElementById('NumDoc').value.length<1){alert('Debe ingresar nro de documento');}else{  cargarAjax('busquedadoc','ACC=BUSQUEDADOC'+'&CodSii='+CodSii.value+'&NumDoc='+NumDoc.value,'listadoc'); }" type="button" name="btnBuscar" class="btn btn-primary btn-sm">
                 <span class="glyphicon glyphicon-search"></span>Buscar</button>
            <button onclick="window.location='index.jsp';" type="button" name="btnLimpiar" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-home"></span>Home 
           </button></td>
          </tr>
          </table>
        <div id="listadoc">
            
            
        </div>
       
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>
