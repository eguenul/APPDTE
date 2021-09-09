

function ValidaFormulario(){
	
if(document.getElementById('CliProvCod').value.length<1){
	document.getElementById('CliProvCod').focus();
        alert('DEBE SELECCIONAR CLIENTE/PROVEEDOR');
	return false;
}	

if(document.getElementById('FechaDoc').value.length<1){
	alert('DEBE INGRESAR FECHA DE DOCUMENTO');
	document.getElementById('FechaDoc').focus();
        return false;
		
}

if(eval(document.getElementById('NRO_FILAS').value)<1){
	alert('DEBE INGRESAR AL MENOS UN DETALLE');
        
	return false;
			
}


if(eval(document.getElementById('Observacion').value.length)<1){
	alert('DEBE INGRESAR OBSERVACIONES');
         $('#ModalReferencia').modal('show');
	return false;
			
}


if(eval(document.getElementById('OrdCompraNum').value.length)<1){
	alert('INGRESE NUMERO DE ORDEN DE COMPRA');
         $('#ModalDoc').modal('show');
	return false;
			
}



//if(document.getElementById('Descuento').value.length<1){
//	alert('Si no hay descuento debe ser valor 0');
//	return false;
//}	
	return true;
}

function GrabarMovimiento(){

//abre modal para ingresar observacion
//$("#ModalReferencia").modal("show");


if(ValidaFormulario()==true){
        document.formmovimiento.action='addmovimiento';
        document.formmovimiento.submit();
        
    }
}

function validaObservacion(){
    

if(eval(document.getElementById('Observacion').value.length)<1){
	alert('Debe Ingresar Observaciones');
         $('#ModalReferencia').modal('show');
	return false;
			
}
    return true;
    
}


