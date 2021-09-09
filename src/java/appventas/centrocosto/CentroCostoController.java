package appventas.centrocosto;

import java.sql.Connection;
import java.sql.SQLException;
import static javax.swing.JOptionPane.showMessageDialog;

public class CentroCostoController  {
    CentroCostoView objCentroCostoView;
    int empresaid; 
    Connection objconexion;
    CentroCostoModel objmodel;
  /*
    CentroCostoView2  objView2;
   
*/     public void addCentroCosto(){
    
    
    
    }



private void addCentro() throws SQLException{
    
    int centrocostocod = Integer.parseInt(objCentroCostoView.centrocostocod.getText());
    String centrocostonom = objCentroCostoView.centrocostonom.getText();
    
    objmodel.addCentroCosto(empresaid, centrocostocod, centrocostonom);
    showMessageDialog(null,"REGISTRO DE CENTRO DE COSTO ACTUALIZADO");
}


private void searchCentro() throws SQLException{
    CentroCosto objcentro = new CentroCosto();
    objcentro = objmodel.searchCentro(objCentroCostoView.centrocostocod.getText());
    objCentroCostoView.centrocostonom.setText(objcentro.getCentrocostonom());
    
}

private void reportCentro(){
    

}

/*
private void searchCod() throws SQLException, ClassNotFoundException{
    DefaultTableModel dtm = objmodel.searchCod(objView2.objcodigo.getText());
    objView2.tabla.setModel(dtm);
    
}

private void searchNom() throws SQLException, ClassNotFoundException{
    DefaultTableModel dtm = objmodel.searchNom(objView2.objnombre.getText());
    objView2.tabla.setModel(dtm);
    
}
*/




  
 
 


}