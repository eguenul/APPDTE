/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.centrocosto;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author esteban
 */
public class CentroCostoView extends JFrame{
    JLabel labelcentrocostocod;
    JTextField centrocostocod;        
    JButton btngrabar;
    JButton btnnuevo;
    JButton btnbuscar;
    JPanel panel;
    
    
    JLabel labelcentrocostonom;
    JTextField centrocostonom;
    public CentroCostoView(){
        
        iniciaDatos();
        
    }
    
    
    
    
    
private void iniciaDatos(){
        

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 350,150);
        
        
	panel.setBorder(BorderFactory.createTitledBorder("DATOS CENTRO COSTO"));
        labelcentrocostocod = new JLabel();
        labelcentrocostocod.setText("CODIGO:");
        labelcentrocostocod.setBounds(30, 30, 80, 10);
        panel.add(labelcentrocostocod);  
      
        centrocostocod = new JTextField();
        centrocostocod.setBounds(140,30,100,20);
        panel.add(centrocostocod);
        
    
        labelcentrocostonom = new JLabel();
        labelcentrocostonom.setText("NOMBRE:");
        labelcentrocostonom.setBounds(30, 60, 80, 10);
        panel.add(labelcentrocostonom);  
      
        centrocostonom = new JTextField();
        centrocostonom.setBounds(140,60,150,20);
        panel.add(centrocostonom);
       
        btngrabar = new JButton();
        btngrabar.setText("Grabar");
        btngrabar.setBounds(370,20,85,20);
        this.add(btngrabar);
        
        
        btnnuevo = new JButton();
        btnnuevo.setText("Nuevo");
        btnnuevo.setBounds(370,50,85,20);
        this.add(btnnuevo);
        
        btnbuscar = new JButton();
        btnbuscar.setText("Buscar");
        btnbuscar.setBounds(260,40,85,20);
        this.add(btnbuscar);
        
        
        this.add(panel);
        
        
        
       
}
    
    
}
