function Grabar(){
    
    if(validaForm()==true){
        document.formcorrelativo.action='correlativo';
        document.formcorrelativo.submit();
        
    }
}


function validaForm(){
    
    if(document.getElementById('FacVentaAfecta').value.length<1){
      alert("DEBE INGRESAR CORRELATIVO DE FACTURA VENTA AFECTA ELECTRONICA");
        return false;
    }
    
    
     if(document.getElementById('NotaCredito').value.length<1){
      alert("DEBE INGRESAR CORRELATIVO DE NOTA DE CREDITO");
        return false;
    }
    
    if(document.getElementById('FacVentaExenta').value.length<1){
      alert("DEBE INGRESAR CORRELATIVO FACTURA EXENTA ELECTRONICA");
        return false;
    }
    
    
    
    return true;
}
