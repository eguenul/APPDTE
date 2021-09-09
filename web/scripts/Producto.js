function grabarProducto(){
	
	var parametro='';
	var ProductoCod;
	var ProductoNom;
	var PrecioVenta;
        var PrecioCompra;
	
    
    
   var ACC;
   var ProductoId;
   if(validaForm()==true){
   ProductoCod = document.getElementById('ProductoCod').value;
   ProductoNom = document.getElementById('ProductoNom').value;
   PrecioCompra = document.getElementById('PrecioCompra').value;
   PrecioVenta = document.getElementById('PrecioVenta').value;
  /*  ProductoId = document.getElementById('ProductoId').value; */

/*   Categoria =  document.getElementById('Categoria').value; */
   ACC = document.getElementById('ACC').value;
/*
  if(document.getElementById('HabilitaStock').checked){
	   
   HabilitaStock = 1;}else{
	   HabilitaStock = 0;
   }
  */ 
   if(document.getElementById('Iva').checked){
	   
   Iva = 1;}else{
	   Iva = 0;
   }
   
   
   
 /*  UnidadMedida = document.getElementById('UnidadMedida').value;	*/
   parametro = parametro + "ProductoNom="+ProductoNom;
   parametro = parametro + "&PrecioCompra="+PrecioCompra;
   parametro = parametro + "&PrecioVenta="+PrecioVenta;
/*  parametro = parametro + "&UnidadMedida="+UnidadMedida; */
/*  parametro = parametro + "&Categoria="+Categoria; */
/*   parametro = parametro + "&HabilitaStock="+HabilitaStock; */
   parametro = parametro + "&Iva="+Iva;
    
   if(ACC=='UPDATE'){ 
    parametro = parametro+"&ProductoCod="+ProductoCod; 
   }
   
   parametro = parametro + "&ACC="+ACC;

   cargarAjax('producto',parametro, 'divresultado');
   }
   limpiarPantalla();
}

function validaForm(){
	
	if(document.getElementById('ProductoNom').value.length<1){
		alert('DEBE INGRESAR NOMBRE DE PRODUCTO');
		return false;
	}
	
	if(document.getElementById('PrecioCompra').value.length<1){
		alert('DEBE INGRESAR PRECIO COMPRA');
		return false;
	}
    if(document.getElementById('PrecioVenta').value.length<1){
	    alert('Debe INGRESAR PRECIO DE VENTA');
	    return false;
	}
	return true;
    
}

function report(){
  var parametro = '';  
   parametro = parametro + "&ACC="+'LISTADO';
   document.formproducto.reset();
   cargarAjax('producto',parametro, 'divresultado');
}



function limpiarPantalla(){
    
 document.getElementById('ProductoCod').value='';
 document.getElementById('ProductoNom').value='';
 document.getElementById('PrecioCompra').value='';
 document.getElementById('PrecioVenta').value='';
 document.getElementById('ACC').value='GRABAR';
 
    
}




