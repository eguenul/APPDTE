
<%@page import="appventas.documento.DocumentoModel"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="appventas.include.comonFunc"%>
<%@page import="com.appdte.json.IdDteCesionjson"%>
<%@page import="com.appdte.json.CesionarioJson"%>
<%@page import="com.appdte.json.RutAutorizadojson"%>
<%@page import="com.appdte.json.CedenteJson"%>
<%

 CedenteJson objCedente = (CedenteJson) request.getSession().getAttribute("objCedente");
 RutAutorizadojson objrutautorizado = objCedente.getRutautorizado();
 CesionarioJson objCesionario = (CesionarioJson) request.getSession().getAttribute("objCesionario");
 
 IdDteCesionjson objiddtecesion = (IdDteCesionjson) request.getSession().getAttribute("objiddtecesion");
 comonFunc objfunciones = new comonFunc();
 

String login = (String) request.getSession().getAttribute("login");

DocumentoModel objDocumentoModel = new DocumentoModel();

 
%>
<html>
<head> 
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> <!?Con esto garantizamos que se vea bien en dispositivos móviles?> 
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" />
<script src="scripts/ajax.js"></script>
<script src="scripts/AEC.js"></script>

</head>
    <body>
        <h3> CESION DE DOCUMENTOS</h3>
        <form method="POST" name="formcesion" id="formcesion" action="uploadcesion">
        <table class="table table-bordered table-striped">
           
          <tr>
                <th colspan="4">DATOS CESION</th>
              </tr>
            <tr>
                <td align="right" colspan="4"><button <% if("admin".equals(login)==true){ %> disabled <% } %> onClick="if(validaForm()==true){ document.formcesion.submit(); }" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-upload"></span>Enviar Cesión
          </button> 
                
                <button onClick="window.location='index.jsp';" type="button" class="btn btn-primary btn-sm">
              <span class="glyphicon glyphicon-home"></span>Home
          </button> 
                
                </td>
              </tr>
            
              <tr>
                  <td>FECHA CESION</td>
                  <td><input name="FECHACESION" id="FECHACESION" type="date"></td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
              </tr>
              
              
              
            <tr>
                <th colspan="4">DATOS CEDENTE</th>
              </tr>
                <tr>
                <td>RUT CEDENTE</td>
                <td><input name="RUTCEDENTE" id="RUTCEDENTE" readonly="yes" value="<% out.print(objCedente.getRut()); %>"></td>
                 <td>RAZON SOCIAL CEDENTE
                 
                 
                 </td>
                <td><input name="RZSCCEDENTE" id="RZSCCEDENTE" readonly="yes" value="<% out.print(objCedente.getRazonsocial()); %>"></td>
                </tr>
                <tr>
                    <td>DIRECCION</td>
                    <td><input name="DIRCEDENTE" id="DIRCEDENTE" value="<% out.print(objCedente.getDireccion()); %>"></td>
                    <td>&nbsp;</td>
                     <td>&nbsp;</td>
                </tr>
                
                
             <tr>
                <th colspan="4">RUT AUTORIZADO</th>
              </tr>
                <tr>
                <td>RUT</td>
                <td><input name="RUTAUTORIZADO" id="RUTAUTORIZADO" readonly="yes" value="<% out.print(objrutautorizado.getRut()); %>"></td>
                 <td>NOMBRE</td>
                <td><input name="NOMBREAUTORIZADO" id="NOMBREAUTORIZADO" readonly="yes" value="<% out.print(objrutautorizado.getNombre()); %>"></td>
            </tr>
            <tr>
                <td>EMAIL</td>
                <td><input name="EMAILAUTORIZADO" id="EMAILAUTORIZADO" readonly="yes" value="<% out.print(objCedente.getEmail()); %>"></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            
            
            
            
            
            
            <tr>
                <th colspan="4">DATOS DEL CESIONARIO</th>
              </tr>
                <tr>
                <td>RUT</td>
                <td><input name="RUTCESIONARIO" id="RUTCESIONARIO" readonly="yes" value="<% out.print(objCesionario.getRut()); %>">
                <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#divcesionario">
              <span class="glyphicon glyphicon-search"></span>Buscar
          </button>           
                </td>
                 <td>NOMBRE</td>
                <td><input name="RZSCCESIONARIO" id="RZSCCESIONARIO"  readonly="yes" value="<% out.print(objCesionario.getRazonsocial()); %>"></td>
                </tr>
            
            
             <tr>
                <td>DIRECCION</td>
                <td><input name="DIRCESIONARIO" id="DIRCESIONARIO" value="<% out.print(objCesionario.getDireccion()); %>" readonly="yes"></td>
                 <td>EMAIL</td>
                 <td><input readonly="yes" name="EMAILCESIONARIO" id="EMAILCESIONARIO"></td>
            </tr>
            
            
            
            
            
            
            <tr>
                <th colspan="4">DATOS DEL DOCUMENTO A CESIONAR</th>
              </tr>
                <tr>
                <td>RUT EMISOR</td>
                <td><input name="RUTEMISOR" id="RUTEMISOR" readonly="yes" value="<% out.print(objiddtecesion.getRutemisor()); %>"></td>
                 <td>RUT RECEPTOR</td>
                <td><input name="RUTRECEPTOR" id="RUTRECEPTOR" value="<% out.print(objiddtecesion.getRutreceptor()); %>" readonly="yes"></td>
               <input type="hidden" name="RSRECEPTOR" id="RUTRECEPTOR" value="<% out.print(objiddtecesion.getRsreceptor()); %>" readonly="yes"></td>
               
                </tr>
            
                <tr>
                <td>TIPO DOCUMENTO</td>
                <td><input value="<% out.print(objDocumentoModel.getNombreDocCodSii(objiddtecesion.getTipodte())); %>" readonly="yes">
                    <input name="TIPODTE" id="TIPODTE" type="hidden" value="<% out.print(objiddtecesion.getTipodte()); %>">
                </td>
                 <td>FECHA EMISION</td>
                <td><input name="FECHAEMIS" id="FECHAEMIS" value="<% out.print(objiddtecesion.getFchemis()); %>" type="date" readonly="yes"></td>
            </tr>
            
                <tr>
                <td>FOLIO</td>
                <td><input name="FOLIO" id="FOLIO" value="<% out.print(objiddtecesion.getFolio()); %>" readonly="yes" ></td>
                 <td>TOTAL</td>
                <td><input name="MNTTOTAL" id="MNTTOTAL" value="<% out.print(objiddtecesion.getMnttotal()); %>" readonly="yes"></td>
                </tr>
                
                <tr>
                <td>ULTIMO VENCIMIENTO</td>
                <td><input name="ULTIMOVENCIMIENTO" id="ULTIMOVENCIMIENTO" type="date"></td>
                 <td>MONTO CEDIDO</td>
                <td><input readonly="yes" name="MONTOCEDIDO" id="MONTOCEDIDO" value="<% out.print(objiddtecesion.getMnttotal()); %>"></td>
                </tr>
        </table>   
                <input name="SECUENCIA" id="SECUENCIA" type="hidden" value="<% out.print(request.getSession().getAttribute("secuencia")); %>">
                 <input name="CesionarioId" id="CesionarioId" type="hidden">
                 <input value="<% out.print(request.getSession().getAttribute("empresaid")); %>" name="EmpresaId" id="EmpresaId" type="hidden">

        </form>
                
 <%@include file="../cesionarioview/divlistacesionario.jsp" %>        
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    </body>
</html>
