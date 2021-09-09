function grabarUsuario(){
    if(validaForm()==true){
  parametro = "UsuarioLogin="+document.getElementById('UsuarioLogin').value;
  parametro = parametro+"&UsuarioRut="+document.getElementById('UsuarioRut').value;
  parametro = parametro+"&UsuarioNombre="+document.getElementById('UsuarioNombre').value;
  parametro = parametro+"&UsuarioApellido="+document.getElementById('UsuarioApellido').value;
  parametro = parametro+"&UsuarioPass="+document.getElementById('UsuarioPass').value;
  parametro = parametro+"&UsuarioEmail="+document.getElementById('UsuarioEmail').value;
  parametro = parametro+"&ACC="+document.getElementById('ACC').value;
    cargarAjax('usuario',parametro,'divusuario');
    }
}

function validaForm(){
    
    if(document.getElementById('UsuarioLogin').value=='admin'){
        alert('LOGIN NO VALIDO');
        return false;
    }
    
    if(document.getElementById('UsuarioLogin').value=='ADMIN'){
        alert('LOGIN NO VALIDO');
        return false;
    }
    
    
    if(document.getElementById('UsuarioLogin').value=='Admin'){
        alert('LOGIN NO VALIDO');
        return false;
    }
    
    if(document.getElementById('UsuarioLogin').value.length<1){
        alert('DEBE INGRESAR LOGIN DE USUARIO');
        return false;
    }
    
    if(document.getElementById('UsuarioRut').value.length<1){
        alert('DEBE INGRESAR RUT DE USUARIO');
        return false;
    }
    
    
    if(document.getElementById('UsuarioNombre').value.length<1){
        alert('DEBE INGRESAR NOMBRE DE USUARIO');
        return false;
    }
    
    
     if(document.getElementById('UsuarioApellido').value.length<1){
        alert('DEBE INGRESAR APELLIDOS DE USUARIO');
        return false;
    }
    
    
      if(document.getElementById('UsuarioPass').value.length<1){
        alert('DEBE INGRESAR PASSWORD DE USUARIO');
        return false;
    }
    
    
    return true;
}



function buscarUsuario(parmlogin){
    
    parametro = "UsuarioLogin="+parmlogin;
    parametro = parametro+"&ACC="+'BUSCAR';
    cargarAjax('usuario',parametro,'divusuario');
    $(function () { $('#divlistusuario').modal('toggle');});
}