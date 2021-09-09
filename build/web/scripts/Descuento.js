var PrecioFinal;
function CalculaDescuento(){

var Porcentaje = eval(document.getElementById('Porcentaje').value);
var PrecioProducto = eval(document.getElementById('ProductoPre').value);
var auxDescuento = 0;
var auxPorcentaje = 0;
var resta = 0;

auxPorcentaje = (Porcentaje/100);
auxDescuento = (PrecioProducto * auxPorcentaje);
PrecioFinal = (PrecioProducto - auxDescuento);
var Cantidad = document.getElementById('Cantidad').value;

if(Porcentaje>0){
Total = (PrecioFinal * Cantidad);
	
	stringTotal = Total.toString();
    arrayDecimal = stringTotal.split(".");
	auxDecimalstring = arrayDecimal[1];
	auxDecimal = parseInt(auxDecimalstring);
	
	if(auxDecimal<9){

		Total = Math.floor(Total);
	}else{
			Total = Math.round(Total);
	}


}else{
	
	Total = Math.round(PrecioProducto * Cantidad);
}

document.getElementById('Total').value = Total;
}

