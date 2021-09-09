<%-- 
    Document   : reportjsp
    Created on : 30-oct-2018, 16:40:36
    Author     : esteban
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
                <title><% out.print(request.getSession(true).getAttribute("titulo"));  %></title>
    </head>
    <body>
        <div class="alert alert-success">
  <strong><% out.print(request.getSession(true).getAttribute("titulo"));  %></strong>
</div>
     <embed src="../download/<% out.print(request.getSession(true).getAttribute("reportname"));  %>.pdf" type="application/pdf" width="100%" height="600px" />   
      <div align="center"><a href="../<% out.print(request.getSession(true).getAttribute("link"));  %>"  class="btn btn-primary btn-sm">Volver</a></div>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>
