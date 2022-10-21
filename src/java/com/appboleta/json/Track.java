/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.appboleta.json;

/**
 *
 * @author esteban
 */
public class Track {
    
    /*
    
    {
"rut_emisor": "76040308-3",
"rut_envia": "13968481-8",
"trackid": 19658149,
"fecha_recepcion": "2022-10-21 11:37:21",
"estado": "REC",
"file": "ENVDTE76040308F9T39.xml"
}
    
    */
    
private String rut_emisor;
private String rut_envio;
private String trackid;
private String fecha_recepcion;
private String estado;
private String file;

    public String getRut_emisor() {
        return rut_emisor;
    }

    public void setRut_emisor(String rut_emisor) {
        this.rut_emisor = rut_emisor;
    }

    public String getRut_envio() {
        return rut_envio;
    }

    public void setRut_envio(String rut_envio) {
        this.rut_envio = rut_envio;
    }

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public String getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(String fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }



}
