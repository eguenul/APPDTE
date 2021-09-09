

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String tipovista = (String) request.getSession().getAttribute("tipovista"); %>
<!DOCTYPE html>
<html>
    <head>
         
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if("emision".equals(tipovista)){ %>
         <div class="container"> 
        <div class="alert alert-success">
  <strong>EMISION DE DTE EXITOSA </strong> TRACKID SII: <% out.print(request.getSession(true).getAttribute("trackid"));  %>.
</div>
<% } %>
 <% if("impresion".equals(tipovista)){ %>
 <div align="center"><h1>IMPRESION DE DTE</h1></div>
 <% } %>
 
      
  <embed src="../pdfservlet" type="application/pdf" width="100%" height="600px" />   

  
         
         </div>
  <div align="center">
          <a href="../movimiento"  class="btn btn-primary btn-sm">Emitir Documento Nuevo</a>
          <% if("impresion".equals(tipovista)){ %>
          <a href="#" onclick="window.history.back();" class="btn btn-primary btn-sm">Volver</a>
          <% } %>
          <a href="#" onclick="window.location='../index.jsp';" class="btn btn-primary btn-sm"> <span class="glyphicon glyphicon-home"></span>Home</a>
          
      </div>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>
