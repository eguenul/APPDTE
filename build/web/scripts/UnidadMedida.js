function BuscarUnidadMedida(){
	var Codigo;
	var parametro;
	 Codigo = document.getElementById('UnidadMedidaCod').value
	 parametro = "UnidadMedidaCod="+Codigo;
	 cargarAjax('busquedaunidadmedida.php',parametro);
}


function GrabarUnidadMedida(){
var Codigo;
var Nombre;
 Codigo = document.getElementById('UnidadMedidaCod').value
 Nombre = document.getElementById('UnidadMedidaNom').value
	parametro = "UnidadMedidaCod="+Codigo;	
	parametro = parametro+"&UnidadMedidaNom="+Nombre 
	 cargarAjax('save',parametro,'contenido');
}


