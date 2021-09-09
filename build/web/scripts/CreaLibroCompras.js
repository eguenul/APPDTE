
function ValidaForm(){
  if(document.getElementById('ANO').length<1){
        alert('Debe ingresar Libro');
        return false;
  }
    return true;
}


function CreaLibro(){
    Ano = document.getElementById('Ano').value;
    Mes = document.getElementById('Mes').value;
    parametro = 'Ano='+Ano+'&Mes='+Mes;   
    cargarAjax('CreaLibroCompra.php',parametro,'contenido');
    document.formperiodolibro.reset();
}