<div id="divcesionario" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">LISTADO CESIONARIO</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

              
<table class="table">
       <thead>  
<tr>
    <th>CODIGO CESIONARIO</th>
    <th>RAZON SOCIAL </th>
    <th>RUT</th>
  </tr>
       </thead>
  <tr>
      <td><input id="CesionarioCod2" name="CesionarioCod2"  onkeypress="return isNumberKey(event);" onkeyup="if(this.value.length>0){ cargarAjax('cesionario','ACC=BUSQUEDACOD&CesionarioCod='+this.value,'contenido');}"></td>
      <td><input id="CesionarioRzsc2" name="CesionarioRzsc2" onkeyup="if(this.value.length>0){ cargarAjax('cesionario','ACC=BUSQUEDARAZ&CesionarioRzsc='+this.value,'contenido');}"></td>
      <td><input id="CesionarioRut2" name="CesionarioRut2" onkeyup="if(this.value.length>0){ cargarAjax('cesionario','ACC=BUSQUEDARUT&CesionarioRut='+this.value,'contenido');}">
         <button onclick="CesionarioCod2.value=''; CesionarioRzsc2.value=''; CesionarioRut2.value=''; cargarAjax('cesionario','ACC=REFRESH','contenido');" type="button" name="btnRefresh" id="btnRefresh" class="btn btn-primary btn-sm">
            <span class="glyphicon glyphicon-refresh"></span> 
        </button>
      </td>
   
  </tr>
</table>    
    <br>
    <div id="contenido">
     <%@include file="../cesionarioview/divlistacesionario2.jsp" %>    
    </div>


    
</div>
</div>    
</div>
</div>    