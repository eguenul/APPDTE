function ValidaForm(){

if(document.getElementById('ProductoCod').value.length<1){
	alert('Debe seleccionar un producto');
	return false;
}

if(document.getElementById('FechaDesde').value.length<1){
	alert('Debe seleccionar fecha inicial');
	return false;
}


if(document.getElementById('FechaHasta').value.length<1){
	alert('Debe seleccionar fecha final');
	return false;
}

if(ComparaFecha()==false){
	
	return false;
}

return true;
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
	 alert('Rango de Fechas no válido');
	 return false;
	 
 }
 
	return true;
}





function CargaReporte(){
	if(ValidaForm()==true){
	  stringFechaInicial = document.getElementById('FechaDesde').value;
      stringFechaFinal = document.getElementById('FechaHasta').value;
	
      ProductoId = document.getElementById('ProductoId').value;	
	  CentroCostoId = document.getElementById('CentroCostoId').value;	
	  parmFechaDesde = FormateaFecha(stringFechaInicial);
	  parmFechaHasta = FormateaFecha(stringFechaFinal);
	  
       parametro = "ProductoId="+ProductoId;
       parametro = parametro+"&CentroCostoId="+CentroCostoId;
	   parametro = parametro+"&FechaDesde="+parmFechaDesde;
	   parametro = parametro+"&FechaHasta="+parmFechaHasta;	
	   parametro = parametro+"&ProductoCod="+document.getElementById('ProductoCod').value;	
	   parametro = parametro+"&TipoReporte="+document.getElementById('TipoReporte').value;
		
		
		
	cargarAjax('cargareportemovimiento.php',parametro)
	
	}
	
}