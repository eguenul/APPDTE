/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appventas.centrocosto;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CentroCostoView2 extends JDialog {
    
    JTable tabla;
    JTextField objcodigo;
    JTextField objnombre;
    JButton btnlimpiar;
public CentroCostoView2(Frame padre){
    super(padre,true);
    configuraVentana();
    inicializaComponente();
}    
    
      
private void configuraVentana(){
        this.setTitle("LISTADO CENTRO COSTO");                   // colocamos titulo a la ventana
        this.setResizable(false);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
       this.setSize(600, 300); 
}  

private void inicializaComponente(){
 tabla = new JTable();
 /*
 tabla.setModel(dtm);
*/ 
 
 JLabel labelcodigo = new JLabel("CODIGO:");
 labelcodigo.setBounds(30,30,80,10);
 this.add(labelcodigo);
 
 
 objcodigo = new JTextField();
 objcodigo.setBounds(90,30,80,20);
 this.add(objcodigo);
 
 
 
 
 JLabel labelnombre = new JLabel("NOMBRE:");
 labelnombre.setBounds(180,30,80,10);
 this.add(labelnombre);
 
 
 objnombre = new JTextField();
 objnombre.setBounds(250,30,150,20);
 this.add(objnombre);
 
 btnlimpiar = new JButton();
 btnlimpiar.setText("Limpiar");
 btnlimpiar.setBounds(450,60,90,20);
 
 this.add(btnlimpiar);
 
 
 
 JScrollPane jsp = new JScrollPane(tabla);
 jsp.setBounds(30, 60, 400, 150);
 this.add(jsp);
 
 
tabla.addMouseListener(new java.awt.event.MouseAdapter(){
    
@Override
public void mouseClicked(java.awt.event.MouseEvent e)
{
 

  int filas =  tabla.getRowCount();
 int i;
 

 
  for( i = 0; i< filas;i++){    
  if(i == tabla.getSelectedRow()){
      
  }
  }
   dispose();
  
}
});
   
 
 
 
}    






    
}
