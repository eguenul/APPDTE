<%
  
String login = (String) request.getSession().getAttribute("login");
%>
<html>
    <head>
        
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 

        
    </head>
    
    <body>
        <div align="center">
        <h1>
       CARGA DE ARCHIVO DE CESION
        </h1>
        <div class="container">

            <form role="form" enctype="multipart/form-data" name="formupload" method="POST" action="uploaddte">
  <div class="form-group">
    <label for="file">SELECCIONE DTE O ARCHIVO ELECTRONICO DE CESION</label>
    <input type="file" name="file" id="file">
    
  </div>
 <button <% if("admin".equals(login)==true){ %> disabled <% } %> onClick="document.formupload.submit();" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-upload"></span>Enviar Cesión
 </button> 
</form>
        </div>
    </div>
        
        
          
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    </body>
</html>