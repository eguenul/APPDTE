function FormateaFecha(parmFecha){
 var FechaFormateada=''; 
 arrayFecha = parmFecha.split("/");
  
  diaFecha = arrayFecha[0];
  mesFecha = arrayFecha[1];
  anoFecha = arrayFecha[2];
 
  FechaFormateada = anoFecha+"-"+mesFecha+"-"+diaFecha;
  
  return FechaFormateada;
 }
 
 
 
 
function ComparaFecha(fechadesde, fechahasta){

 stringFechaInicial = document.getElementById(fechadesde).value;
 stringFechaFinal = document.getElementById(fechahasta).value;

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