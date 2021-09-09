function ValidaForm(){


if(document.getElementById('FechaDesde').value.length<1){
	alert('DEBE SELECCIONAR FECHA INICIAL');
	return false;
}


if(document.getElementById('FechaHasta').value.length<1){
	alert('DEBE SELECCIONAR FECHA FINAL');
	return false;
}

if(ComparaFecha()==false){
	
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
        cargarAjax('busquedafecha',parmvalor,'contenido');
       
    }
}



function ComparaFecha(){

 stringFechaInicial = document.getElementById('FechaDesde').value;
 stringFechaFinal = document.getElementById('FechaHasta').value;

 arrayFechaInicial = stringFechaInicial.split("/");
 diaFechaInicial = arrayFechaInicial[0];
 mesFechaInicial = arrayFechaInicial[1];
 anoFechaInicial = arrayFechaInicial[2];
 
 
 
 arrayFechaFinal = stringFechaFinal.split("/");
 diaFechaFinal = arrayFechaFinal[0];
 mesFechaFinal = arrayFechaFinal[1];
 anoFechaFinal = arrayFechaFinal[2];

 var f1 = new Date(anoFechaInicial, (mesFechaInicial-1), diaFechaInicial); 
 var f2 = new Date(anoFechaFinal, (mesFechaFinal-1),diaFechaFinal);

 if(f1>f2){
	 alert('RANGO DE FECHAS NO VALIDO');
	 return false;
	 
 }
 
	return true;
}