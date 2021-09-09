function validaForm(){
    
   if(document.getElementById('passwordant').value.length<1){
       alert('DEBE INGRESAR PASSWORD ANTERIOR');
       return false;
   }
    
    
   if(document.getElementById('passwordnueva').value.length<1){
       alert('DEBE INGRESAR NUEVA PASSWORD');
       return false;
   } 
   
    if(document.getElementById('passwordconfirm').value.length<1){
       alert('DEBE CONFIRMAR NUEVA PASSWORD');
       return false;
    } 
   
   auxpassnueva = document.getElementById('passwordnueva').value;
   auxpassconfirm =  document.getElementById('passwordconfirm').value;
   
   if(auxpassnueva != auxpassconfirm){
       alert('PASSWORD NUEVA Y PASSWORD A CONFIRMAR SON DIFERENTES. REVISE DATOS');
       return false;
       
   }
 
 return true;
}

function setPass(){
    if(validaForm()==true){
        document.formsetpass.action='setpass';
        document.formsetpass.submit();
    }
    
}

