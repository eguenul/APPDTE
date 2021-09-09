<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<% String referencia = (String) request.getSession().getAttribute("referencia"); %>  

<div id="estadodocumento">
    
</div>

<form method="POST" action="printdte" name="formlista" id="formlista">
    

<table id="example" class="table table-striped table-bordered">
 
<thead>
    
    
    
    
<tr>
<th>TIPO DOCUMENTO</th>
<th>NRO DOCUMENTO</th>
<th>FECHA DOCUMENTO</th>
<th>RUT RECEPTOR</th>
<th>TOTAL</th>
<th>TRACK ID</th>   
<th>ACCION</th>
</tr>
</thead>
<tbody>
<%     List<Object[]> arraylistdoc = (ArrayList<Object[]>)request.getSession(true).getAttribute("arraylistdoc");
        for(Object[] i:arraylistdoc){ %>
        <tr>
            <td><% out.print(i[0]); %></td>
            <td><% out.print(i[1]); %></td>
            <td><% out.print(i[2]); %></td>
            <td><% out.print(i[5]); %></td>
       <td><% out.print(i[3]); %></td>
<td><% out.print(i[4]); %></td>
<td>
    
    
    
    
    
    
    <button onclick="document.formlista.action='printdte'; document.getElementById('Id').value=<% out.print(i[6]);%>; document.formlista.submit();" class="btn btn-primary btn-sm" type="Button">
    <span class="glyphicon glyphicon-print"></span>Imprimir</button>
    <button onclick="cargarAjax('consultadte','MovimientoId=<% out.print(i[6]); %>&ACC=CONSULTADTE','estadodocumento' );"  class="btn btn-primary btn-sm" type="Button">
<span class="glyphicon glyphicon-search"></span>Consulta SII</button>
    
   <% if(referencia=="yes"){ %>  
<input type="hidden"  value="<% out.print(request.getSession(true).getAttribute("CliProvCod")); %>" name="CliProvCod" id="CliProvCod">
<% } %>
   <% if(referencia=="yes"){ %>
  <% String codsii = (String) request.getSession(true).getAttribute("codsii");  %>
<% if("61".equals(codsii)){ %>
<button onclick="document.formlista.action='movimiento2'; ACC.value='SELECT'; document.getElementById('Id').value=<% out.print(i[6]); %>; document.formlista.submit();" class="btn btn-primary btn-sm" type="Button">
<span class="glyphicon glyphicon-remove"></span>Anular</button>
<% } %>
<% if("33".equals(codsii)){ %>
<button onclick="document.getElementById('Id').value=<% out.print(i[6]);%>; document.formlista.submit();" class="btn btn-primary btn-sm" type="Button">
<span class="glyphicon glyphicon-file"></span>Nuevo</button>    

<% } %>

<% } %>

</td>
        </tr>
<%  } %>



</tbody>
</table>
<% if((String)request.getSession().getAttribute("botonera")=="yes"){ %>
 <a href="#" onclick="cargarAjax('busquedafecha','ACC=ANT&pagina=<% out.print(request.getSession(true).getAttribute("pagina")); %>&FechaDesde=<% out.print(request.getSession(true).getAttribute("FechaDesde")); %>&FechaHasta=<% out.print(request.getSession(true).getAttribute("FechaHasta")); %>','contenido');"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-circle-arrow-left"></span>Anterior
        </a>


<a href="#" onclick="cargarAjax('busquedafecha','ACC=SIG&pagina=<% out.print(request.getSession(true).getAttribute("pagina")); %>&FechaDesde=<% out.print(request.getSession(true).getAttribute("FechaDesde")); %>&FechaHasta=<% out.print(request.getSession(true).getAttribute("FechaHasta")); %>','contenido');"  class="btn btn-primary btn-sm">
          <span class="glyphicon glyphicon-circle-arrow-right"></span>Siguiente
        </a>

P&aacute;gina <% out.print(request.getSession(true).getAttribute("pagina")); %> de <% out.print(request.getSession(true).getAttribute("nropaginas")); %>
<% } %>
<input type="hidden" name="ACC" id="ACC">

<input type="hidden" name="Id" id="Id">

</form>
