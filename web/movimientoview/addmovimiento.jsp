<%@page import="appventas.fpago.FPago"%>
<%@page import="appventas.movimientos.Despacho"%>
<%@page import="appventas.movimientos.DetalleMovimiento"%>
<%@page import="appventas.movimientos.Movimiento"%>
<%@page import="appventas.documento.Documento"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="appventas.movimientos.Traslado"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%
    
    String login = (String) request.getSession().getAttribute("login");

    Date dNow = new Date();
   SimpleDateFormat ft = 
   new SimpleDateFormat ("YYYY-MM-dd");
   String currentDate = ft.format(dNow);
%>   
<%
int montoexento = 0;
int montoafecto = 0;
int montoiva = 0;
int montototal = 0;
int nrofilas = 0;
String fecha = "";
int numdoc = 0;
String docdes= "";
%>
<%
CliProv objcliprov = (CliProv)request.getSession().getAttribute("objcliprov");       
String referencia = (String) request.getSession().getAttribute("referencia");
if(referencia=="yes"){
Movimiento objMovimiento = (Movimiento) request.getSession().getAttribute("objMovimiento");

 montoexento = objMovimiento.getMontoexento();
 montoafecto = objMovimiento.getMontoafecto();
 montoiva = objMovimiento.getMontoiva();
 montototal = objMovimiento.getMontototal();
 
 // numdoc = objMovimiento.getNumdoc(); 
}
%>
<html>
<head>    
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />
<script src="scripts/ajax.js"></script>
<script type="text/javascript" src="scripts/Numeros.js"></script>
<script src="scripts/Movimiento.js"></script>
<% if(referencia=="no"){ %>
<script src="scripts/Tablalistado.js"></script>
<script src="scripts/Descuento.js"></script>

<script src="scripts/CalculoTotal.js"></script>
<script src="scripts/Detalle.js"></script>
<% } %>
<link rel="stylesheet" type="text/css" href="tcal/tcal.css" />
<script type="text/javascript" src="tcal/tcal.js"></script> 
<title>EMISION DTE</title>

<style>
    
#exTabs2{  border-radius: 4px 4px 0 0;
 -webkit-border-radius: 5px;
}
</style>
</head>
<body> 
   
    <div align="center">
       <% if(referencia=="no"){ %> 
        <h1>EMISION VENTA</h1>
  <% }else{ %>
    
  <h1>EMISION DOCUMENTO CON REFERENCIA</h1>
  <% } %>
  <!--   <div class="w3-bar w3-section w3-border w3-round-xlarge" style="color:whitesmoke; background-color: #008CBA;"> -->
<div id="exTabs2"  class="w3-bar w3-border w3-light-grey  w3-round-xlarge">
  <button class="w3-bar-item w3-button w3-border-right">DATOS GENERALES</button>
   <button <% if(referencia=="yes") {%> disabled <% } %> class="w3-bar-item w3-button w3-border-right" data-toggle="modal" data-target="#ModalDespacho">TIPO DESPACHO</button>
   <button <% if(referencia=="yes"){%> disabled <% } %> class="w3-bar-item w3-button w3-border-right" data-toggle="modal" data-target="#ModalTraslado">TIPO TRASLADO</button>
   <button <% if(referencia=="yes"){%> disabled <% } %>  class="w3-bar-item w3-button w3-border-right" data-toggle="modal" data-target="#ModalDoc">ORDEN DE COMPRA</button>
   <button class="w3-bar-item w3-button w3-border-right" data-toggle="modal" data-target="#ModalReferencia">OBSERVACION</button>   
</div>
           
    <form method="POST" action="<% if(referencia=="no"){ out.print("movimiento"); }else{ out.print("movimiento2"); } %>" name="formmovimiento" id="formmovimiento">     
<div id="London" class="city"> 
     
    <table   id="tabla1" class="table table-bordered table-striped">
  
<tr>
<th colspan="4">DATOS CLIENTE/PROVEEDOR</th>
</tr>
<tr>      
      <td colspan="4" align="right">
       <% if(referencia=="no"){ %>
          <button <% if("admin".equals(login)==true){ %> disabled <% } %> onClick="GrabarMovimiento();" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-floppy-disk"></span>Grabar
          </button> 
       <% }
        if(referencia=="yes"){
       
       %>   
          <button onClick="if(validaObservacion()==true){ ACC.value='GRABAR'; document.formmovimiento.action='addmovimiento'; document.formmovimiento.submit();}" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-floppy-disk"></span>Grabar
          </button> 
      <% } %> 
          <button type="button" name="btnLimpiar" onClick="window.location.href='movimiento';" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-file"></span>Nuevo 
           </button>
                     
              <button onclick="window.location='index.jsp';" type="button" name="btnLimpiar" class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-home"></span>Home 
           </button>
       
      </td>
    </tr>
    <tr>
      <td>CODIGO CLIENTE/PROVEEDOR</td>
      <td><input   readonly="yes" value="<% if(objcliprov.getCliprovcod()==0){out.print(""); }else{   out.print(objcliprov.getCliprovcod());} %>"   name="CliProvCod" type="text" id="CliProvCod"  size="8" maxlength="6">
         <% if(referencia=="no"){ %>
          <button name="btnListadoCliProv" type="button" id="btnListadoCliProv" data-toggle="modal" data-target="#divcliprov"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-search"></span>Buscar
        </button>
          <% } %>
      </td>
      <td>RUT CLIENTE/PROVEEDOR</td>
      <td><input  name="CliProvRut" value="<% out.print(objcliprov.getCliprovrut()); %>"   readonly="yes" type="text" id="CliProvRut" size="8" maxlength="8">
      </td>
    </tr>
    <tr>
      <td>RAZON SOCIAL</td>
      <td><input  readonly="yes" name="CliProvRaz" value="<% out.print(objcliprov.getCliprovraz()); %>"  type="text" id="CliProvRaz"></td>
      <td>GIRO</td>
      <td><input   readonly="yes" name="CliProvGir" value="<% out.print(objcliprov.getCliprovgir()); %>" type="text" id="CliProvGir"></td>
    </tr>
    <tr>
      <td>COMUNA</td>
      <td><input   readonly="yes" name="CliProvCom" value="<% out.print(objcliprov.getCliprovcom()); %>" type="text" id="CliProvCom"></td>
      <td>CIUDAD</td>
      <td><input   readonly="yes" name="CliProvCiu" value="<% out.print(objcliprov.getCliprovciu()); %>" type="text" id="CliProvCiu"></td>
    </tr>
    <tr>
    <td>DIRECCION</td>
    <td><input readonly="yes" name="CliProvdIR" value="<% out.print(objcliprov.getCliprovdir()); %>" type="text" id="CliProvGir"></td>
    <td>FONO</td>
    <td><input  readonly="yes" name="CliProvdIR" value="<% out.print(objcliprov.getCliprovfon()); %>" type="text" id="CliProvGir"></td>
   
    </tr>
    <tr>
        <td>TIPO DOCUMENTO</td>
      <td>          
          
          <% if(referencia=="no"){ %>
          <select class="style-select"  name="TipoDocumento" id="TipoDocumento">
          
          <% 

 List<Documento> documentos = (ArrayList<Documento>)request.getSession(true).getAttribute("servletName");
 
    for(Documento documento : documentos)
    { %>

    <option value="<% out.print(documento.getIddoc()); %>"><% out.print(documento.getNombredoc()); %></option>
   <%   
   }
 
%>
          
          
          
            </select>
     <% }else{ %>
     <% out.print(request.getSession(true).getAttribute("nombredoc")); %>
     <input type="hidden" value="<% out.print(request.getSession(true).getAttribute("TipoDocumento")); %>" id="TipoDocumento" name="TipoDocumento">
      <% } %>    
    
   </td>
       <td>FECHA DOCUMENTO</td>
      <td><input   size="10" type="date" value="<% out.print(currentDate); %>"  id="FechaDoc" name="FechaDoc"></td>
    </tr>
    
    <tr>
        <td>
            FORMA PAGO
        </td>
        
        <td>
            <select class="style-select">
<%        List<FPago> listfpago = (ArrayList<FPago>)request.getSession(true).getAttribute("arrayfpago");
 %>               
                
            <%   for(FPago fpago : listfpago){ %>
             <option value="<% out.print(fpago.getIdfpago()); %>"><% out.print(fpago.getFpagodes()); %></option>
            <% } %>
            </select>
        </td>
        <td>&nbsp;</td>
        
        <td>&nbsp;</td>
        
        
    </tr><!-- comment -->
    </table>
   
 <table  class="table table-bordered" id="TablaDetalle">
  
  <% if(referencia=="no"){ %>
   <tr>
    <td colspan="7" align="right"><button onclick="AgregaDetalle();" class="btn btn-primary btn-sm"  name="btnAgregar" type="button" id="btnAgregar">
       <span class="glyphicon glyphicon-plus"></span>Agregar</button>

      <button name="btnEliminar" class="btn btn-primary btn-sm" onclick="EliminaFila();" type="button" id="btnEliminar">
       <span class="glyphicon glyphicon-minus"></span>Eliminar</button></td>
    </tr><tr>
      <th>CODIGO</th>
      <th>DESCRIPCION PRODUCTO</th>
      <th>UNIDAD MEDIDA</th>
      <th>PRECIO</th>
      <th>%DESC</th>
      <th>CANTIDAD</th>
      <th>TOTAL</th>
    </tr>
    <tr>
      <td>
          <input  size="5" readonly="yes" maxlength="8" name="ProductoCod" type="text" id="ProductoCod">
            <button type="button" name="btnListadoProductos" id="btnListadoProductos" data-toggle="modal" data-target="#divproducto"    class="btn btn-primary btn-sm">
            <span class="glyphicon glyphicon-search"></span>Buscar 
        </button>
    
    </td>

      <td>
      <input size="15" readonly="yes" name="ProductoNom" type="text" id="ProductoNom">      </td>
      <td>
      <input readonly="yes" size="8"   name="UnidadMedidaNom" type="text" id="UnidadMedidaNom">      </td>
      <td>
      <input size="8" readonly="yes"  name="ProductoPre" type="text" id="ProductoPre">     
      </td>
      <td><input onkeypress="return isNumberKey(event);"   onkeyup="if(Cantidad.value>0){ CalculaDescuento();}"     name="Porcentaje"  type="text" id="Porcentaje" value="0" size="3" maxlength="3"></td>
      <td>
      <input  onkeypress="return isNumberKey(event);" onkeyup="if(TipoMovimiento.value==1){ CalculaDescuento();}else{  Total.value = Math.floor((eval(this.value)* eval(ProductoPre.value)));}  if(this.value.length<1){ Total.value=0;}" size="8" name="Cantidad" type="text" id="Cantidad">   </td>
      <td>
      <input size="8" readonly="yes" name="Total" type="text" id="Total">     
      <input name="AfectoExento" type="hidden" id="AfectoExento">     
      <input name="PrecioOriginal" type="hidden" id="PrecioOriginal">     
      </td>
    </tr> 
	
    <% } %>        
    <tr>    
	<th colspan="7">
	DETALLE DOCUMENTO
	</th>
    </tr>
          
          <tr>
            <th>CODIGO</th>
            <th>DESCRIPCION PRODUCTO</th>
            <th>UNIDAD MEDIDA</th>
            <th>PRECIO</th>
            <th>%</th>
            <th>CANTIDAD</th>
            <th>TOTAL</th>
          </tr>
          <% if(referencia=="yes"){ %>
            <% 
              List<DetalleMovimiento> arraydetalle = (ArrayList<DetalleMovimiento>) request.getSession(true).getAttribute("arraydetalle");
            
              for(DetalleMovimiento i:arraydetalle){
             Producto objProducto = i.getObjProducto();     
            %> 
          <tr>
              <td><input size="8" name="ProductoCod<% out.print(nrofilas); %>" value="<% out.print(objProducto.getProductocod()); %>" readonly></td>
              <td><input size="15" name="ProductoNom<% out.print(nrofilas); %>" value="<% out.print(objProducto.getProductonom()); %>" readonly></td>
              <td><input readonly></td>
              <td><input size="8" value="<% out.print(objProducto.getProductoprevent()); %>" readonly></td>
              <td><input size="8" value="<% out.print(i.getDescuentopct()); %>" readonly></td>
              <td><input size="8" name="Cantidad<% out.print(nrofilas); %>" value="<% out.print(i.getCantidad()); %>" readonly></td>
              <td><input size="8" name="Total<% out.print(nrofilas); %>" value="<% out.print(i.getTotal()); %>" readonly></td>
          </tr>    
          <% nrofilas++; } } %>
      
 </table>
    
  <table  class="table table-bordered table-striped table-highlight">
    <tr>
        <td align="right">EXENTO</td>
        <td align="right"><input readonly="yes" value="<% out.print(montoexento); %>" name="Exento" id="Exento" size="8"></td>  
        <td align="right">DESCUENTO</td>
        <td align="right"><input value="0" id="DescuentoGlobalPct" size="8"></td>  
        <td align="right">AFECTO</td>
        <td align="right"><input value="<% out.print(montoafecto); %>" readonly="yes" name="TotalNeto" id="TotalNeto" size="8"></td>  
        <td align="right">IVA</td>
        <td align="right"><input value="<% out.print(montoiva); %>" readonly="yes" id="Iva" name="Iva" size="8"></td>  
        
        <td align="right">TOTAL</td>
        <td align="right"><input value="<% out.print(montototal); %>" readonly="yes" name="TotalBruto" id="TotalBruto" size="8"></td>  
    </tr>
   </table> 
            
<% if(referencia=="yes"){ %>

<%
     docdes = (String) request.getSession(true).getAttribute("docdes");   
%>

  <table  class="table table-bordered table-striped table-highlight" >
      <tr>
      <th colspan="6">DOCUMENTO DE REFERENCIA</th>
      </tr>
      <tr>
        <td align="right">TIPO DE DOCUMENTO</td>
        <td align="right"><% out.print(docdes); %> </td>  
        <td align="right">FECHA</td>
        <td align="right"><input value="<% out.print(request.getSession(true).getAttribute("fechadoc")); %>" name="FchRef" id="FchRef" type="date" size="8"></td>  
        <td align="right">FOLIO</td>
        <td align="right"><input readonly value="<% out.print(request.getSession(true).getAttribute("numdoc")); %>"    name="FolioRef" id="FolioRef"  size="8"></td>  
         </tr>
   </table> 
    
      <% } %>   
     
      
    
   
   <input type="hidden" id="ACC" name="ACC">
<input type="hidden" id="NRO_FILAS" value="<% out.print(nrofilas); %>" name="NRO_FILAS">
<input type="hidden" name="TipoMovimiento" id="TipoMovimiento" value="1">

 <% if(referencia=="no"){ %>
 
<%@include file="../cliprovview/divlistacliprov.jsp" %>
<%@include file="../productoview/divlistaproducto.jsp" %>
<% } %>

<!-- Modal -->
<div id="ModalDespacho" class="modal fade" role="dialog">
  <div class="modal-dialog ">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">DATOS DE DESPACHO</h4>
      </div>
      <div class="modal-body">
          <table class="table table-striped">
              <tr>
              <th>TIPO DE DESPACHO</th>
              </tr>
              <tr>
              
             <td>
                 <select name="TipoDespacho" id="TipoDespacho" class="style-select">
                    <% 
              List<Despacho> arraydespacho = (ArrayList<Despacho>) request.getSession(true).getAttribute("arraydespacho");
             for(Despacho i:arraydespacho){
            
                    %> 
            <option value="<%  out.print(i.getDespachoid());  %>"><%  out.print(i.getDespachodes());  %></option> 
                <% }   %>
                 </select>
             </td>
             
              </tr>
          </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
             
<!-- Modal -->
<div id="ModalTraslado" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">TRASLADO</h4>
      </div>
      <div class="modal-body">
          <table>
            
               <tr>
                 <th>TIPO TRASLADO</th>
                </tr>
             <tr>
                 <td>
                     <select name="TipoTraslado" id="TipoTraslado" class="style-select">
                       <% 
              List<Traslado> arraytraslado = (ArrayList<Traslado>) request.getSession(true).getAttribute("arraytraslado");
             for(Traslado i:arraytraslado){
               %> 
            <option value="<%  out.print(i.getTipotrasladoid());  %>"><%  out.print(i.getTrasladodes());  %></option> 
             <% }   %>
                      </select>         
                 </td>
                </tr>
            
          </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div id="ModalReferencia" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">DATOS DE OBSERVACION</h4>
      </div>
      <div class="modal-body">
          <table>
              <tr>
              <th>OBSERVACION</th>
              </tr>
              <tr>
                  <td><textarea  id="Observacion" name="Observacion" rows="10" cols="40"></textarea></td></td>
              </tr>
          </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<% if(referencia=="no"){ %>


<!-- Modal -->
<div id="ModalDoc" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">DOCUMENTO DE REFERENCIA</h4>
      </div>
      <div class="modal-body">
          <table class="table">
              <tr>
              
          
              <th>NRO ORDEN</th>
              <th>FECHA ORDEN</th>
              </tr>
              <tr>
                  <td><input name="OrdCompraNum" id="OrdCompraNum" value="0"></td>
                
<td><input name="FchRef" value="<% out.print(currentDate); %>" type="date"   id="FchRef"></td>
              
              </tr>
          </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<% } %>
</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>


