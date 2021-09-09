function validaForm(){
    
    if (document.formcesionario.CesionarioRzsc.value.length<1){
        alert('DEBE INGRESAR RAZON SOCIAL DE CESIONARIO');
        return false;
    }
    
    if (document.formcesionario.CesionarioRut.value.length<1){
        alert('DEBE INGRESAR RUT DE CESIONARIO');
        return false;
    }
    
    
    
    if (document.formcesionario.CesionarioDir.value.length<1){
        alert('DEBE INGRESAR DIRECCION DE CESIONARIO');
        return false;
    }
    
     if (document.formcesionario.CesionarioEmail.value.length<1){
        alert('DEBE INGRESAR EMAIL DE CESIONARIO');
        return false;
    }
    
    return true;
}

function grabarCesionario(){
    var parm1 ='';
    
if(validaForm()==true){
    parm1  = "CesionarioRzsc="+document.formcesionario.CesionarioRzsc.value;
    parm1 = parm1+"&CesionarioRut="+document.formcesionario.CesionarioRut.value;
    parm1 = parm1+"&CesionarioDir="+document.formcesionario.CesionarioDir.value;
    parm1 = parm1+"&CesionarioEmail="+document.formcesionario.CesionarioEmail.value;

        
 if(document.getElementById('ACC').value=='UPDATE'){
   parm1 = parm1+"&CesionarioCod="+document.formcesionario.CesionarioCod.value;
   
 }
 parm1 = parm1+"&ACC="+document.getElementById('ACC').value;
 cargarAjax('cesionario',parm1,'formcesionario');   
}
    
}