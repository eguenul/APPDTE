<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />

<script src="scripts/ajax.js"></script>
<script src="scripts/Numeros.js"></script>
<script src="scripts/Cesion.js"></script>

        <title>CESION DE DOCUMENTO</title>
        
    </head>
    <body>
        <h1>CESION DE DOCUMENTOS</h1>
    
    <input type="hidden" name="MovimientoId">
<table id="example" class="table table-striped table-bordered">
     <tr>
        <th>  
          DATOS DE DOCUMENTO  
        </th>
    </tr>
    <tr>
        <td align="left">
            <input onkeypress="return isNumberKey(event);" name="NumDoc" id="NumDoc">
            <button onclick="buscaDoc();" type="button" name="btnBuscar" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-search"></span>Buscar
           </button>
            <button onclick="window.location='index.jsp';" type="button" name="btnLimpiar" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-home"></span>Home 
           </button>
        </td>
    </tr>
</table>
    
    <div id="listadocumento">
       
    </div>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    </body>
</html>
