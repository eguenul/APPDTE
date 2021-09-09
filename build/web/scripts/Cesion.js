/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validaForm(){
    
    if(document.getElementById('NumDoc').value<1){
        alert('DEBE INGRESAR NUMERO DE DOCUMENTO'); 
        return false;
    }
    return true;
}


function buscaDoc(){
    if(validaForm()==true){
     parametro = "ACC="+"BUSQUEDADOC"+"&NumDoc="+document.getElementById('NumDoc').value;
     parametro = parametro +"&TipoDocumento="+document.getElementById('TipoDocumento').value;
     cargarAjax('cesion',parametro,'listadocumento');
         
     }
        
    }
    




