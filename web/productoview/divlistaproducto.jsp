<div id="divproducto" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">LISTADO PRODUCTO</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

              
<table class="table" width="100%" align="center">
<tr>
    <th>CODIGO  </th>
    <th>NOMBRE</th>
    <th>PRECIO VENTA</th>
  </tr>
  <tr>
      <td><input id="ProductoCod2" name="ProductoCod2" onkeyup="if(this.value.length>0){ cargarAjax('producto','ACC=BUSQUEDACOD&ProductoCod='+this.value,'listaproducto2');}" onkeypress="return isNumberKey(event);"></td>
      <td><input id="ProductoNom2" name="ProductoNom2" onkeyup="if(this.value.length>0){ cargarAjax('producto','ACC=BUSQUEDANOM&ProductoNom='+this.value,'listaproducto2');}"></td>
      <td><input>
      <button onclick="ProductoCod2.value='';ProductoNom2.value='';  cargarAjax('producto','ACC=REFRESH','listaproducto2');" type="button" name="btnRefresh" id="btnRefresh" class="btn btn-primary btn-sm">
            <span class="glyphicon glyphicon-refresh"></span> 
        </button>
      </td>
  </tr>
</table>    
<div id="listaproducto">  
    <br>   
<div id="listaproducto2">    
<%@include file="../productoview/divlistaproducto2.jsp" %>
</div>
</div>
<input type="hidden" id="pagina" name="pagina" value="">
</div>
</div>    
</div>
</div>