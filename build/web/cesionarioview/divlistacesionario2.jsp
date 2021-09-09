<%@page import="java.util.List"%>
<%@page import="appventas.cesionario.Cesionario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table class="table"> 
    <thead>  
                <tr>  
                    <th>CODIGO CLIENTE PROVEEDOR</th>  
                    <th>RAZON SOCIAL</th>  
                    <th>RUT</th>                  
    </tr>  
    </thead>  
    <% 

 List<Cesionario> cesionarios = (ArrayList<Cesionario>)request.getSession(true).getAttribute("arraylistcesionario");
 
    for(Cesionario cesionario : cesionarios)
    { %>
    <% 
    if(   request.getSession().getAttribute("modulo")=="cesion"){
    %>
    <tr>
        <td onclick="RUTCESIONARIO.value='<% out.print(cesionario.getCesionariorut()); %>'; RZSCCESIONARIO.value='<% out.print(cesionario.getCesionariorzsc()); %>'; DIRCESIONARIO.value='<% out.print(cesionario.getCesionariodir()); %>'; EMAILCESIONARIO.value='<% out.print(cesionario.getCesionarioemail()); %>'; CesionarioId.value='<% out.print(cesionario.getCesionarioid()); %>'; $(function () { $('#divcesionario').modal('toggle');});"><% out.print(cesionario.getCesionariocod()); %></td>   
    <td><% out.print(cesionario.getCesionariorzsc()); %></td>
 
    <td><% out.print(cesionario.getCesionariorut()); %></td>
    </tr>
   <% }else{ %>
    <tr>
        <td onclick="cargarAjax('cesionario','ACC=BUSCAR&CesionarioCod=<% out.print(cesionario.getCesionariocod()); %>' ,'formcesionario'); $(function () { $('#divcesionario').modal('toggle');});"><% out.print(cesionario.getCesionariocod()); %></td>   
    <td><% out.print(cesionario.getCesionariorzsc());  %></td>
 
    <td><%  out.print(cesionario.getCesionariorut());  %></td>
    </tr>
   <% } %> 
  <%   
   }
 %> 
</table>
