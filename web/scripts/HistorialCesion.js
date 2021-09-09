/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function ValidaForm(){


if(document.getElementById('FechaDesde').value.length<1){
	alert('DEBE SELECCIONAR FECHA INICIAL');
	return false;
}


if(document.getElementById('FechaHasta').value.length<1){
	alert('DEBE SELECIONAR FECHA FINAL');
	return false;
}



return true;	
}

function Buscar(){
    if(ValidaForm()==true){
        fechadesde = document.getElementById('FechaDesde').value;
        fechahasta = document.getElementById('FechaHasta').value;
        
        document.getElementById('FechaDesde').disabled=true;
        document.getElementById('FechaHasta').disabled=true;
        
        
        parmvalor = 'ACC=BUSCAR&FechaDesde='+fechadesde+'&FechaHasta='+fechahasta;
        cargarAjax('historialcesion',parmvalor,'contenido');
       
    }
}
