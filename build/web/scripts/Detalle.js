var idFila;
idFila = null;
numFila = 0;

function AgregaDetalle(){
    if(ValidaDetalle()==true){
	var tabla = document.getElementById("TablaDetalle");
    var nro_filas =  eval(document.getElementById("NRO_FILAS").value);
    
// Create an empty <tr> element and add it to the 1st position of the table:
var FilaTabla =  document.createElement("tr");;

	FilaTabla.id = "FilaTabla"+nro_filas;
	
	// creo los elementos con estas etiquetas
    var Columna0 = document.createElement("td");
    var Columna1 = document.createElement("td");
    var Columna2 = document.createElement("td");
    var Columna3 = document.createElement("td");
    var Columna4 = document.createElement("td");
    var Columna5 = document.createElement("td");
	var Columna6 = document.createElement("td");
	
    var Input0 = document.createElement("input");
    var Input1 = document.createElement("input");
    var Input2 = document.createElement("input");
    var Input3 = document.createElement("input");
    var Input4 = document.createElement("input");
    var Input5 = document.createElement("input");
	var Input6 = document.createElement("input")
	var Input7 = document.createElement("input")
	
	
	 // cambio los atributos de tamaño y solo lectura
     Input0.setAttribute('readonly', 'yes');
	 Input0.setAttribute('maxlength','8');
	 Input0.setAttribute('size','5');
	 Input1.setAttribute('size','15');
	 Input2.setAttribute('size','8');
	 Input3.setAttribute('size', '8');
	 Input4.setAttribute('size', '8');
	 Input5.setAttribute('size', '8');
	 Input6.setAttribute('size', '8');
	 
	 
	 Input1.setAttribute('readonly', 'yes');
	 Input2.setAttribute('readonly', 'yes');
	 Input3.setAttribute('readonly', 'yes');
	 Input4.setAttribute('readonly', 'yes');
	 Input5.setAttribute('readonly', 'yes');



	 // cambio los atributos de nombre 
	 Columna0.setAttribute('name', 'Columna0'+nro_filas);
	 Columna1.setAttribute('name', 'Columna1'+nro_filas);
	 Columna2.setAttribute('name', 'Columna2'+nro_filas);
	 Columna3.setAttribute('name', 'Columna3'+nro_filas);
	 Columna4.setAttribute('name', 'Columna4'+nro_filas);
	 Columna5.setAttribute('name', 'Columna5'+nro_filas);
	 Columna6.setAttribute('name', 'Columna6'+nro_filas);

	 Input0.setAttribute('name', 'ProductoCod'+nro_filas);
	 Input1.setAttribute('name', 'ProductoNom'+nro_filas);
	 Input2.setAttribute('name', 'UnidadMedida'+nro_filas);
	 Input3.setAttribute('name', 'ProductoPre'+nro_filas);
	 Input4.setAttribute('name', 'Porcentaje'+nro_filas);
	 Input5.setAttribute('name', 'Cantidad'+nro_filas);
	 Input6.setAttribute('name', 'Total'+nro_filas);
	 Input7.setAttribute('name', 'AfectoExento'+nro_filas);

	 
	 
	 // cambio los atributos de id
	 Columna0.setAttribute('id', 'Columna0'+nro_filas);
	 Columna1.setAttribute('id', 'Columna1'+nro_filas);
	 Columna2.setAttribute('id', 'Columna2'+nro_filas);
	 Columna3.setAttribute('id', 'Columna3'+nro_filas);
	 Columna4.setAttribute('id', 'Columna4'+nro_filas);
	 Columna5.setAttribute('id', 'Columna5'+nro_filas);
	 Columna6.setAttribute('id', 'Columna6'+nro_filas);

	 Input0.setAttribute('id', 'ProductoCod'+nro_filas);
	 Input1.setAttribute('id', 'ProductoNom'+nro_filas);
	 Input2.setAttribute('id', 'UnidadMedida'+nro_filas);
	 Input3.setAttribute('id', 'ProductoPre'+nro_filas);
	 Input4.setAttribute('id', 'Porcentaje'+nro_filas);
	 Input5.setAttribute('id', 'Cantidad'+nro_filas);
	 Input6.setAttribute('id', 'Total'+nro_filas);
	 Input7.setAttribute('id', 'AfectoExento'+nro_filas);
	 Input7.setAttribute('type', 'hidden');

	 Input0.value = document.getElementById('ProductoCod').value;
	 Input1.value = document.getElementById('ProductoNom').value;
	 Input2.value = document.getElementById('UnidadMedidaNom').value;
     Input3.value = document.getElementById('ProductoPre').value;	 
	 Input4.value = document.getElementById('Porcentaje').value;
	 Input5.value = document.getElementById('Cantidad').value;
	 Input6.value = document.getElementById('Total').value;
	 Input7.value = document.getElementById('AfectoExento').value;
	
	document.getElementById('ProductoCod').value = '';
	document.getElementById('ProductoNom').value = '';
	document.getElementById('UnidadMedidaNom').value= '';
	document.getElementById('ProductoPre').value = '';
	document.getElementById('Cantidad').value = '';
	document.getElementById('Total').value = '';
	document.getElementById('AfectoExento').value = '';
	 // ASIGNO LOS CADA TEXT A SU RESPECTIVA COLUMNA
	Columna0.appendChild(Input0);
	Columna1.appendChild(Input1);
	Columna2.appendChild(Input2);
	Columna3.appendChild(Input3);
	Columna4.appendChild(Input4);
	
	Columna5.appendChild(Input5);
	Columna6.appendChild(Input6);
	Columna6.appendChild(Input7);
// ASIGNO LAS COLUMNAS A LA FILA
    FilaTabla.appendChild(Columna0);
    FilaTabla.appendChild(Columna1);
    FilaTabla.appendChild(Columna2);
    FilaTabla.appendChild(Columna3);
    FilaTabla.appendChild(Columna4);
    FilaTabla.appendChild(Columna5);
	FilaTabla.appendChild(Columna6);
	
// cambio los atributo de la fila creada
 FilaTabla.onclick = function(){ idFila = FilaTabla.id; CambiaColor(idFila);  }   
	tabla.appendChild(FilaTabla);
	nro_filas = (nro_filas + 1);
	document.getElementById("NRO_FILAS").value = nro_filas;
	}
	
	CalculaTotal();
}

function CambiaColor(parmId){
// ESTA FUNCION CAMBIA LAS FILAS DE COLORES
	nro_filas = eval(document.getElementById('NRO_FILAS').value);
	var i = 0;
	for(i=0; i<=nro_filas-1; i++){
	     	if(document.getElementById(parmId).id == ('FilaTabla'+i)){
			     idFila = 'FilaTabla' + i;
                document.getElementById('FilaTabla'+i).style.backgroundColor="#66ff33";
			}else{	
                document.getElementById('FilaTabla'+i).style.backgroundColor="#FFFFFF"; 
			}	
		}
	}
	

function EliminaFila(){
	
	if(eval(document.getElementById('NRO_FILAS').value)<1){
		alert('No hay detalle para eliminar');
		
	}else{
	var FilaTabla;
	// creo los elementos con estas etiquetas
    var Columna0;
    var Columna1;
    var Columna2;
    var Columna3;
    var Columna4;
    var Columna5;
    var Input0; 
    var Input1;
    var Input2; 
    var Input3; 
    var Input4; 
    var Input5;
	var Input6;
	// esta funcion renombra los elementos que se mantienen antes de que se eliminen
	
	nro_filas = eval(document.getElementById('NRO_FILAS').value);
	var i = 0;
	var j = 0;
	var auxFilas = nro_filas;

	for(i=0; i<=nro_filas-1; i++){
	// OBTENGO LA REFERENCIA DE LOS ELEMENTOS
	 Columna0 = document.getElementById('Columna0'+i);
	 Columna1 = document.getElementById('Columna1'+i);
	 Columna2 = document.getElementById('Columna2'+i);
	 Columna3 = document.getElementById('Columna3'+i);
	 Columna4 = document.getElementById('Columna4'+i);
	 Columna5 = document.getElementById('Columna5'+i);
	 Columna6 = document.getElementById('Columna6'+i);
							 
	 Input0 = document.getElementById('ProductoCod'+i);
	 Input1 = document.getElementById('ProductoNom'+i);
	 Input2 = document.getElementById('UnidadMedida'+i);
	 Input3 = document.getElementById('ProductoPre'+i);
	 Input4 = document.getElementById('Porcentaje'+i);
					 
	 Input5 = document.getElementById('Cantidad'+i);
	 Input6 = document.getElementById('Total'+i);
	 Input7 = document.getElementById('AfectoExento'+i);
	 FilaTabla = document.getElementById("FilaTabla"+i);
	  var row = document.getElementById(FilaTabla.id);
                            
							 
			 
	if(FilaTabla.id != idFila){
							 
	// cambio los atributos de nombre 
	 Columna0.setAttribute('name', 'Columna0'+j);
	 Columna1.setAttribute('name', 'Columna1'+j);
	 Columna2.setAttribute('name', 'Columna2'+j);
	 Columna3.setAttribute('name', 'Columna3'+j);
	 Columna4.setAttribute('name', 'Columna4'+j);
	 Columna5.setAttribute('name', 'Columna5'+j);
	 Columna6.setAttribute('name', 'Columna6'+j);
									 
	 Input0.setAttribute('name', 'ProductoCod'+j);
	 Input1.setAttribute('name', 'ProductoNom'+j);
	 Input2.setAttribute('name', 'UnidadMedida'+j);
	 Input3.setAttribute('name', 'ProductoPre'+j);
	 Input3.setAttribute('name', 'Porcentaje'+j);
	 Input5.setAttribute('name', 'ProductoCan'+j);
	 Input6.setAttribute('name', 'Total'+j);
	 Input7.setAttribute('name', 'AfectoExento'+j);
									
	 // cambio los atributos de id
	 Columna0.setAttribute('id', 'Columna0'+j);
	 Columna1.setAttribute('id', 'Columna1'+j);
	 Columna2.setAttribute('id', 'Columna2'+j);
	 Columna3.setAttribute('id', 'Columna3'+j);
	 Columna4.setAttribute('id', 'Columna4'+j);
	 Columna5.setAttribute('id', 'Columna5'+j);
	 Columna6.setAttribute('id', 'Columna6'+j);
									 
	 Input0.setAttribute('id', 'ProductoCod'+j);
	 Input1.setAttribute('id', 'ProductoNom'+j);
	 Input2.setAttribute('id', 'UnidadMedida'+j);
	 Input3.setAttribute('id', 'ProductoPre'+j);
	 Input4.setAttribute('id', 'Porcentaje'+j);
	 Input5.setAttribute('id', 'Cantidad'+j);
	 Input6.setAttribute('id', 'Total'+j);
	 Input7.setAttribute('id', 'AfectoExento'+j);
									
	 FilaTabla.setAttribute('id','FilaTabla'+j);
	 FilaTabla.setAttribute('name','FilaTabla'+j);
	 j = (j+1);
							 
	   }else{
		    row.parentNode.removeChild(row);
		 document.getElementById("NRO_FILAS").value = auxFilas - 1;
						   }
		}	
	
		CalculaTotal();
	}
}

function LimpiarGrid(){
 nro_filas = eval(document.getElementById('NRO_FILAS').value);
	for(i=0; i<=nro_filas-1; i++){
     FilaTabla = document.getElementById("FilaTabla"+i);
	 var row = document.getElementById(FilaTabla.id);
     row.parentNode.removeChild(row);
	}
     document.getElementById("NRO_FILAS").value = 0;;
}



function ValidaDetalle(){
	
	if(document.getElementById('CliProvCod').value.length<1){
            
            alert('Debe Validar Cliente/Proveedor');
            document.getElementById('CliProvCod').focus();
	    return false;
	}
	
	
	if(document.getElementById('ProductoCod').value.length<1){
           
		alert('Debe Validar Producto');
                 document.getElementById('ProductoCod').focus();
	    return false;
	}
	
	if(document.getElementById('Cantidad').value.length<1){
		alert('Cantidad debe ser mayor que cero');
                document.getElementById('Cantidad').focus();
	    return false;
	}
	
	
	if(document.getElementById('Porcentaje').value.length<1){
		alert('Si no hay descuento ingrese 0');
                document.getElementById('Porcentaje').focus();
	    return false;
	}
	
	
		return true;	
	
}


