function Grabar(){

var parametro;
if (validaform()==true){
parametro = "CentroCostoCod="+document.getElementById('CentroCostoCod').value;
parametro = parametro+"&CentroCostoNom="+document.getElementById('CentroCostoNom').value;
parametro = parametro+"&ACC="+document.getElementById('ACC').value;
cargarAjax('grabarcentrocosto.php',parametro);
}
}
function BuscarCentroCosto(){
	
var parametro;
parametro = "CentroCostoCod="+document.getElementById('CentroCostoCod').value;
cargarAjax('buscacentrocosto.php',parametro);

}

function validaform(){
	if(document.getElementById('CentroCostoNom').value.length<1){
		alert('Debe ingresar nombre de centro de costo');
		return false;
		
	}
	return true;
}