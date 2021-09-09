function asignadetalle(parmProductoCod,parmNombre,parmPrecioCompra,parmPrecioVenta,parmAfectoExento){
	document.getElementById('ProductoCod').value = parmProductoCod;
	document.getElementById('ProductoNom').value = parmNombre;
	document.getElementById('ProductoPre').value = parmPrecioVenta;
	document.getElementById('PrecioOriginal').value = parmPrecioVenta; 
	document.getElementById('AfectoExento').value = parmAfectoExento; 	
        CierraModal();
}

function CierraModal(){
   $("#divproducto").modal('hide');
}