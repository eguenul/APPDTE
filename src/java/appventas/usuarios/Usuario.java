/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.usuarios;

/**
 *
 * @author esteban
 */
public class Usuario {
    private String login;
    private String password;
    private String rut;
    private String usuarionom;
    private String usuarioap;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getUsuarionom() {
        return usuarionom;
    }

    public void setUsuarionom(String usuarionom) {
        this.usuarionom = usuarionom;
    }

    public String getUsuarioap() {
        return usuarioap;
    }

    public void setUsuarioap(String usuarioap) {
        this.usuarioap = usuarioap;
    }
    
    
}
