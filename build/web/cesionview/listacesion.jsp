       
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<div id ="estadocesion">
    
</div>


<table class="table table-striped table-bordered">
   <tr>
       <th>CESION</th>
     <!-- 
       <th>FECHA CESION</th>
       --> <th>TIPO DOCUMENTO</th>
       <th>NRO DOCUMENTO</th>
       <th>RUT EMISOR</th>
       <th>RUT RECEPTOR</th>
       <th>MONTO CEDIDO</th>
       <th>TRACKID</th>
    <!--   <th>TRACKID</th> -->
        <th>ACCION</th>
   </tr>
   
   
   <%    ArrayList<Object[]> arraylistdoc = (ArrayList<Object[]>)request.getSession(true).getAttribute("arraycesion");
        for(Object[] i:arraylistdoc){ %>

    <tr>
        <td><% out.print(i[0]); %></td>
       <!-- <td><% /* out.print(i[1]); */ %></td> -->
        <td><% out.print(i[3]); %></td>
        <td><% out.print(i[2]); %></td>
        <td><% out.print(i[4]); %></td>
        <td><% out.print(i[5]); %></td>
       <td><% out.print(i[6]); %></td>
      <!-- <td><% /* out.print(i[7]);  */ %></td> -->
        <td><% out.print(i[8]); %></td>
        <td>
<%   String modulo = (String) request.getSession().getAttribute("modulo");
%>
<% if("buscacesion".equals(modulo)){ %>
<a href="pdfservlet" target="_blank"   class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-print"></span>Imprimir
        </a>
<% } else{ %>        
<a onclick="cargarAjax('printcesion','CesionNum='+<% out.print(i[0]); %>,'download');" href="#"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-print"></span>Imprimir
        </a>     
 
      
<% } %> 

<a onclick="cargarAjax('consultacesionsii','TRACKID=+<% out.print(i[8]); %>','estadocesion' );" href="#"   class="btn btn-primary btn-sm">
<span class="glyphicon glyphicon-search"></span> Consulta SII</a>  
 
        </td>
   </tr>
   <% } %>
</table>
<% if((String)request.getSession().getAttribute("botonera")=="yes"){ %>
 <a href="#" onclick="cargarAjax('historialcesion','ACC=ANT&pagina=<% out.print(request.getSession(true).getAttribute("pagina")); %>&FechaDesde=<% out.print(request.getSession(true).getAttribute("FechaDesde")); %>&FechaHasta=<% out.print(request.getSession(true).getAttribute("FechaHasta")); %>','contenido');"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-circle-arrow-left"></span>Anterior
        </a>


<a href="#" onclick="cargarAjax('historialcesion','ACC=SIG&pagina=<% out.print(request.getSession(true).getAttribute("pagina")); %>&FechaDesde=<% out.print(request.getSession(true).getAttribute("FechaDesde")); %>&FechaHasta=<% out.print(request.getSession(true).getAttribute("FechaHasta")); %>','contenido');"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-circle-arrow-right"></span>Siguiente
        </a>

P&aacute;gina <% out.print(request.getSession(true).getAttribute("pagina")); %> de <% out.print(request.getSession(true).getAttribute("nropaginas")); %>
<% } %>