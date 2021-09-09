
<div id="divlistusuario" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">LISTADO DE USUARIO</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">   
          <table>
       
<tr> 
    <th>LOGIN</th>
    <th>RUT</th>
    <th>NOMBRE USUARIO</th>
     <th>APELLIDO</th>
   </tr>
       
  <tr>
      <td><input id="UsuarioLogin2" name="UsuarioLogin2"  onkeypress="return isNumberKey(event);" onkeyup="if(this.value.length>0){ cargarAjax('usuario','ACC=BUSQUEDALOGIN&UsuarioLogin='+this.value,'contenido');}"></td>
      <td><input id="UsuarioRut2" name="UsuarioRut2" onkeyup="if(this.value.length>0){ cargarAjax('usuario','ACC=BUSQUEDARUT&UsuarioRut='+this.value,'contenido');}"></td>
      <td><input id="UsuarioNom" name="UsuarioNom2" onkeyup="if(this.value.length>0){ cargarAjax('usuario','ACC=BUSQUEDANOM&UsuarioNom='+this.value,'contenido');}"></td>
      <td><input id="UsuarioApellido" name="UsuarioApellido2" onkeyup="if(this.value.length>0){ cargarAjax('usuario','ACC=BUSQUEDAAP&UsuarioApellido='+this.value,'contenido');}"></td>
  
  </tr>
</table>    
<div id="divlistusuario2">  
    <br>
    <div id="contenido">
     <%@include file="../usuarioview/divlistusuario2.jsp" %>    
    </div>
</div>
<input type="hidden" id="pagina" name="pagina" value="">
</div>
</div>    
</div>
</div>    