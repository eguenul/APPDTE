/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.movimientos;

import appventas.producto.Producto;


/**
 *
 * @author esteban
 */
public class DetalleMovimiento {
int cantidad; 
int descuentopct;
int descuentomonto;
int total;
Producto objProducto;

    public Producto getObjProducto() {
        return objProducto;
    }

    public void setObjProducto(Producto objProducto) {
        this.objProducto = objProducto;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDescuentopct() {
        return descuentopct;
    }

    public void setDescuentopct(int descuentopct) {
        this.descuentopct = descuentopct;
    }

    public int getDescuentomonto() {
        return descuentomonto;
    }

    public void setDescuentomonto(int descuentomonto) {
        this.descuentomonto = descuentomonto;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }





}
