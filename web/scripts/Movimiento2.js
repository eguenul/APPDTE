
function ValidaFormulario(){
	
if(document.getElementById('CliProvCod').value.length<1){
	document.getElementById('CliProvCod').focus();
        alert('DEBE SELECCIONAR PROVEEDOR');
	return false;
}	

return true;
}