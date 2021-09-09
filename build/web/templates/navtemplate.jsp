
<nav class="navbar navbar-default" role="navigation">
<div  class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target=".navbar-ex1-collapse">
      <span class="sr-only">Desplegar navegación</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#"><img src="../static/img/logo2.jpg" style="margin-top:-15px; margin-left: -15px;"></a>
  </div>
 
  
  <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav">
   
       <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          MANTENCION <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li><a href="ingresoempresa.php">EMPRESAS</a></li>
          <li><a href="/centrocosto">CENTROS DE COSTO</a></li>
          <li><a href="/unidadmedida">UNIDADES DE MEDIDA</a></li>
          <li><a href="/categoria">CATEGORIAS</a></li>
          <li><a href="ingresoproducto.php">PRODUCTOS</a></li>
          <li><a href="/cliprov">CLIENTE/PROVEEDOR</a></li>
          
        </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                PROCESOS <b class="caret"></b>
          </a>
            <ul class="dropdown-menu">
                <li><a href="ingresoentrada.php">INGRESO DE PRODUCTOS</a></li>
                <li><a href="movimiento">EMISION VENTA</a></li>
                <li><a href="ingresosalida.php">FACTURACION GUIAS</a></li>
                <li><a href="ingresosalida.php">ANULAR FACTURAS</a></li>
            </ul>
       </li>

       <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                PROCESOS DTE <b class="caret"></b>
          </a>
            <ul class="dropdown-menu">
                <li><a href="enviopendiente.php">ENVIAR DTE PENDIENTE</a></li>
                <li><a href="controllerlibroventa.php">LIBRO DE DE VENTA</a></li> 
                 <li class="divider"></li>
              <li class="dropdown-submenu">
                <a tabindex="-1" href="#">LIBRO DE COMPRAS</a>
                <ul class="dropdown-menu">
                  <li><a tabindex="-1" href="creacionlibrocompra.php">CREACION LIBRO DE COMPRAS</a></li>
                  <li><a tabindex="-1" href="selLibroCompraController.php">ASIGNACION DE DOCUMENTOS</a></li>
                
            </ul> 
        </li>         
    </ul>
  </div>
  </nav>