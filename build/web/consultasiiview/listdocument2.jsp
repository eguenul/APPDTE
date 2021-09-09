<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<form name="formcesion" method="POST" action="cesion" id="formcesion">
<table class="table table-striped table-bordered">

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
            <td><% out.print(i[8]); %></td>
            <td><% out.print(i[3]); %></td>
            <td><% out.print(i[4]); %></td>
<td>
<button onclick="MovimientoId.value=<% out.print(i[6]); %>; cargarAjax('consultadte','MovimientoId=<% out.print(i[6]); %>&ACC=CONSULTADTE','estadodocumento' );"  class="btn btn-primary btn-sm" type="Button">
<span class="glyphicon glyphicon-search"></span>Consulta SII</button>
</td>
</tr>
<% }  %>
</tbody>
</table>
<input type="hidden" name="MovimientoId" name="MovimientoId">
</form>

<div id="estadodocumento">
    
</div>