function ValidaForm(){

if(document.getElementById('FechaDesde').value.length<1){

    alert('DEBE SELECCIONAR FECHA DE INICIO');
    return false;
}


if(document.getElementById('FechaHasta').value.length<1){

    alert('DEBE SELECCIONAR FECHA DE TERMINO');
    return false;
}

 
if(ComparaFecha('FechaDesde','FechaHasta')==false){
    

   
}
 return true;

}

function ListarVentas(){
 if(ValidaForm()==true){ 
     extension = document.getElementById('extension').value;
    parametro = "extension="+extension+"&FechaDesde="+document.getElementById('FechaDesde').value;
    parametro = parametro+"&FechaHasta="+document.getElementById('FechaHasta').value;
    cargarAjax('libroventas',parametro,'contenido');

 }
}


