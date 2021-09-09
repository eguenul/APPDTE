<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />
<script src="scripts/ajax.js"></script>
<script src="scripts/Fecha.js"></script>
<script src="scripts/ajax.js"></script>
<script src="scripts/LibroVenta.js"></script>
</head>
<body> 
    <div align="center">
        <h1>
        GENERACION LIBRO DE VENTAS
        </h1>
    </div>
  
<div class="container">
    <form class="navbar-form" action="" method="post">
        <fieldset>
		<legend>RANGO DE BUSQUEDA:</legend>
               	<label for="FechaDesde">Desde:</label>
                <input   type="date" name="FechaDesde" id="FechaDesde">
                <label for="FechaHasta">Hasta:</label>
		<input  type="date" name="FechaHasta" id="FechaHasta">
                Formato:
                <select id="extension" name="extension" class="style-select">
                    <option value="pdf">PDF</option>
                    <option value="xls">EXCEL</option>
                </select>
                <button class="btn btn-primary btn-sm" type="Button" onclick="ListarVentas();">
                 <span class="glyphicon glyphicon-search"></span>
                    Buscar</button>
               <button class="btn btn-primary btn-sm" type="Button" onclick="window.location='libroventas';">
                 <span class="glyphicon glyphicon-file"></span>
                    Nuevo</button>
                
                <button class="btn btn-primary btn-sm" type="Button" onclick="window.location='index.jsp';">
                 <span class="glyphicon glyphicon-home"></span>
                    Home</button>  
                
        </fieldset>  
                
</form>
    <div id="contenido">
    </div>
</div>
  
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
    
</html>
