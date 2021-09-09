<%
    String estado = (String) request.getSession().getAttribute("ESTADO");
    request.getSession().setAttribute("ESTADO",null);
 %>   
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />
<title>CAMBIO DE PASSWORD</title>
 <script src="scripts/setpass.js"></script>
    </head>
    <body>
        <h1>CAMBIO DE PASSWORD ADMINISTRADOR</h1>
      <% if("ok".equals(estado)){ %>  
        <div class="alert alert-success">
  <strong>OPERACION REALIZADA: </strong> CAMBIO DE PASSWORD ADMINISTRADOR.
</div>
   <% } %>     
        
        
        <form name="formsetpass" method="POST">
            <table class="table table-bordered table-striped">
                <tr>
                <th colspan="2">
                    CAMBIO DE PASSWORD
                 </th>
                </tr>
                <tr>
                <td align="right" colspan="2">
                    <button type="button" class="btn btn-primary btn-sm" onclick="setPass();"><span class="glyphicon glyphicon-floppy-disk"></span>Grabar</button>
                  <button type="button" class="btn btn-primary btn-sm" onclick="window.location='index.jsp';"><span class="glyphicon glyphicon-home"></span>Home</button>
                
                
                </td>
                </tr>
                
                
                
                <tr>
                  <td>
                        PASSWORD ANTERIOR
                    </td>
                    
                    <td>
                        <input id="passwordant" name="passwordant">
                     </td>                 
                </tr>
                   <tr>
                  <td>
                        PASSWORD NUEVA
                    </td>
                    
                    <td>
                        <input id="passwordnueva" name="passwordnueva">
                     </td>                 
                </tr>
                 <tr>
                  <td>
                        CONFIRMAR PASSWORD NUEVA
                    </td>
                    
                    <td>
                        <input id="passwordconfirm" name="passwordconfirm">
                     </td>                 
                </tr>
                
                
                
                
            </table>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
            
            
            
        </form>
    </body>
</html>
