package appventas.report;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import appventas.include.Conexion;
import appventas.include.ConfigClass;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.xml.sax.SAXException;
public class Report {
Map hm;
String reportName;   
String title;
String pathdownload;
String archivosalida;

public Report(String reportName,String pathdownload,String archivosalida){
    this.reportName = reportName;
    this.pathdownload = pathdownload;
    this.archivosalida = archivosalida;
    hm = new HashMap();
    
}
 
public void  showReport() throws JRException, SQLException, ClassNotFoundException, ParserConfigurationException, SAXException, IOException {
    /* genera el documento en pdf */ 
    ConfigClass objconfig = new ConfigClass();
        Conexion cbd = new Conexion();
        Connection auxConexion = cbd.obtener();
        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(objconfig.getPathreports()+reportName+".jasper");
        
                
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, hm, auxConexion);
       
        
        OutputStream output = new FileOutputStream(new File(pathdownload+archivosalida)); 
        JasperExportManager.exportReportToPdfStream(jasperPrint, output);  
        System.out.print(pathdownload+archivosalida);

}
    
    public void setParameters(String parameterName, String parameterValue){
        hm.put(parameterName, parameterValue);
        
    }
    
public void showExcel() throws ParserConfigurationException, SAXException, IOException, SQLException, ClassNotFoundException, JRException{
     ConfigClass objconfig = new ConfigClass();
     Conexion cbd = new Conexion();
     Connection auxConexion = cbd.obtener();
     JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(objconfig.getPathreports()+reportName+".jasper");
     JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, hm, auxConexion);
     JRXlsExporter exporter = new JRXlsExporter();
	
     exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
     exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pathdownload+archivosalida));
     SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
     configuration.setOnePagePerSheet(true);
     exporter.setConfiguration(configuration);
     exporter.exportReport();
    
     /*
     OutputStream output = new FileOutputStream(new File(pathdownload+archivosalida)); 
    JasperExportManager.exportReportToPdfStream(jasperPrint, output);   
     */
}    
    
 
}
