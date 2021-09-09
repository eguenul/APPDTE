<html>
    <head>
        <meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 

    </head>
    <body>
        

<div class="alert alert-success" role="alert">
        TRACK ID DE ENVIO DE CESION: <% out.print(request.getSession().getAttribute("TRACKID")); %>
</div>     
       <embed src="../pdfservlet" type="application/pdf" width="100%" height="600px" />       
       <div align="center">
       <button onclick="window.location='../index.jsp';" type="button" name="btnLimpiar" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-home"></span>Home 
           </button>
       </div>
       
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    </body>
</html>
