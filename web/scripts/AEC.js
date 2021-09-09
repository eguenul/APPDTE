function validaForm(){
    
   if(document.getElementById('FECHACESION').value.length<1){
       alert('DEBE INGRESAR FECHA DE CESION');
       return false;
       
   }
   
   
    if(document.getElementById('ULTIMOVENCIMIENTO').value.length<1){
       alert('DEBE INGRESAR ULTIMO VENCIMIENDO');
       return false;
       
   }
   
    if(document.getElementById('MONTOCEDIDO').value.length<1){
        
        alert('DEBE INGRESAR MONTO A CEDER');
       return false;
       
    }
   
   
   
   
   
  var mnttotal = document.getElementById('MNTTOTAL').value;
  var montocedido = document.getElementById('MONTOCEDIDO').value;
   
   if(montocedido>mnttotal){
       alert('MONTO CEDIDO NO VALIDO');
       return false;
       
   }
   
   if(montocedido==0){
       alert('MONTO CEDIDO NO VALIDO');
       return false;
       
   }
   
   
   
   
   if(document.getElementById('RUTCESIONARIO').value.length<1){
        alert('DEBE SELECCIONAR CESIONARIO');
       return false;
       
       
   }
   
   
   
   return true;
}
