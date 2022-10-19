/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.movimientos;

import appventas.cliprov.CliProv;
import java.util.ArrayList;

/**
 *
 * @author esteban
 */
public class Movimiento {
   private int tipodoc;   
   private int montoafecto;
   private int montoexento;
   private int montoiva;
   private int montototal;
   private int tasaiva;
   private String fechamov;
   private int numdoc;
   private int ordcompranum;
   private CliProv objcliprov;
   private Despacho tipodespacho;
   private Traslado tipotraslado;
   private boolean bolref;
   private String fchref;
   private int tpoventa;
   private int fpago;
   
   
   private int guia = 0;
   private int facafecta = 0;
   private int notacredito = 0;
   private int facexenta = 0;
   private int notadebito = 0;
   private int guiadesp = 0;
   
   private int codsiiref = 0;
   
   private String ReferenciaGlobal;
   
   
    public Traslado getTipotraslado() {
        return tipotraslado;
    }

    public void setTipotraslado(Traslado tipotraslado) {
        this.tipotraslado = tipotraslado;
    }
   

    public Despacho getTipodespacho() {
        return tipodespacho;
    }

    public void setTipodespacho(Despacho tipodespacho) {
        this.tipodespacho = tipodespacho;
    }
   
   
   
   private ArrayList<DetalleMovimiento> objdetalle;

    public ArrayList<DetalleMovimiento> getObjdetalle() {
        return objdetalle;
    }

    public void setObjdetalle(ArrayList<DetalleMovimiento> objdetalle) {
        this.objdetalle = objdetalle;
    }

    public CliProv getObjcliprov() {
        return objcliprov;
    }

    public void setObjcliprov(CliProv objcliprov) {
        this.objcliprov = objcliprov;
    }

    public int getNumdoc() {
        return numdoc;
    }

    public void setNumdoc(int numdoc) {
        this.numdoc = numdoc;
    }

    public int getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(int tipodoc) {
        this.tipodoc = tipodoc;
    }
   
   public int getMontoafecto() {
        return montoafecto;
    }

    public void setMontoafecto(int montoafecto) {
        this.montoafecto = montoafecto;
    }

    public int getMontoexento() {
        return montoexento;
    }

    public void setMontoexento(int montoexento) {
        this.montoexento = montoexento;
    }

    public int getMontoiva() {
        return montoiva;
    }

    public void setMontoiva(int montoiva) {
        this.montoiva = montoiva;
    }

    public int getMontototal() {
        return montototal;
    }

    public void setMontototal(int montototal) {
        this.montototal = montototal;
    }

    public int getTasaiva() {
        return tasaiva;
    }

    public void setTasaiva(int tasaiva) {
        this.tasaiva = tasaiva;
    }

    public String getFechamov() {
        return fechamov;
    }

    public void setFechamov(String fechamov) {
        this.fechamov = fechamov;
    }

   

    public String getReferenciaGlobal() {
        return ReferenciaGlobal;
    }

    public void setReferenciaGlobal(String ReferenciaGlobal) {
        this.ReferenciaGlobal = ReferenciaGlobal;
    }

    public boolean getBolref() {
        return bolref;
    }

    public void setBolref(boolean bolref) {
        this.bolref = bolref;
    }

    public int getOrdcompranum() {
        return ordcompranum;
    }

    public void setOrdcompranum(int ordcompranum) {
        this.ordcompranum = ordcompranum;
    }

    public String getFchref() {
        return fchref;
    }

    public void setFchref(String fchref) {
        this.fchref = fchref;
    }

    public int getGuia() {
        return guia;
    }

    public void setGuia(int guia) {
        this.guia = guia;
    }


    public int getNotacredito() {
        return notacredito;
    }

    public void setNotacredito(int notacredito) {
        this.notacredito = notacredito;
    }


    public int getNotadebito() {
        return notadebito;
    }

    public void setNotadebito(int notadebito) {
        this.notadebito = notadebito;
    }

    public int getGuiadesp() {
        return guiadesp;
    }

    public void setGuiadesp(int guiadesp) {
        this.guiadesp = guiadesp;
    }

    public int getFacafecta() {
        return facafecta;
    }

    public void setFacafecta(int facafecta) {
        this.facafecta = facafecta;
    }

    public int getFacexenta() {
        return facexenta;
    }

    public void setFacexenta(int facexenta) {
        this.facexenta = facexenta;
    }

    public int getCodsiiref() {
        return codsiiref;
    }

    public void setCodsiiref(int codsiiref) {
        this.codsiiref = codsiiref;
    }

    public int getTpoventa() {
        return tpoventa;
    }

    public void setTpoventa(int tpoventa) {
        this.tpoventa = tpoventa;
    }

    public int getFpago() {
        return fpago;
    }

    public void setFpago(int fpago) {
        this.fpago = fpago;
    }

    
    
    
    
}
