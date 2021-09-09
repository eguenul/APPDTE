
 


 


function CalculaTotal(){
    var Iva = 0.19;	
    var TotalNeto = 0;
    var auxTotal = 0;
    var IvaCalculado = 0;
    var TotalBruto = 0;
    var TotalExento = 0;
	document.getElementById('TotalNeto').value = 0;
	nro_filas = document.getElementById('NRO_FILAS').value;
	
 /*	DescuentoGlobal = document.getElementById('Descuento').value;
		
*/	
	
	for(i=0; i<=nro_filas-1; i++){
		
		if(eval(document.getElementById('AfectoExento'+i).value)==1){
				auxTotal = eval(document.getElementById('Total'+i).value);
				TotalNeto = TotalNeto + auxTotal;
		}else{
			     	
		if(eval(document.getElementById('AfectoExento'+i).value)==0){
				auxTotal = eval(document.getElementById('Total'+i).value);
				TotalExento = TotalExento+ auxTotal;
			
		}
		}
		
	}
	 
            IvaCalculado = TotalNeto * Iva;
            document.getElementById('TotalNeto').value = TotalNeto;
            document.getElementById('Exento').value = TotalExento;
	    document.getElementById('Iva').value = Math.round(IvaCalculado);
            TotalBruto = TotalNeto+TotalExento+Math.round(IvaCalculado);
            document.getElementById('TotalBruto').value = TotalBruto;
	}	
		
		
 
 
 
 
 
 
