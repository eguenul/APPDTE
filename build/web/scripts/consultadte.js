function buscaDoc(){
    var numdoc = document.getElementById('NumDoc').value;
    var tipodoc = document.getElementById('TipoDocumento').value;;
    
    
    var parametro = "ACC=BUSCAR"+ "&NumDoc="+numdoc+"&TipoDocumento="+tipodoc; 
    
    if(validaForm()==true){
        
        
        cargarAjax('consultadte',parametro,'listadocumento');
    }
}




function validaForm(){
   if(document.getElementById('NumDoc').value.length<1){
       alert('DEBE INGRESAR NUMERO DE DOCUMENTO');
       return false;
   }
   return true;
}

function consultaEstado(){
    
    
}


