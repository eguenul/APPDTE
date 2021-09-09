function buscaCesion(){
    if (validaForm()==true){
       
        parametro = "CesionNum="+document.getElementById('CesionNum').value;
        parametro = parametro + "&ACC=" + "BUSCAR";
        cargarAjax('buscacesion',parametro,'listacesion');
        
    }
    
    
}


function validaForm(){
    if(document.getElementById('CesionNum').value.length<1){
        
        alert('DEBE INGRESAR CESION');
        return false;
    }
    return true;
}