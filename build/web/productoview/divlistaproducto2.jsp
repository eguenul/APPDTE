<%-- 
    Document   : divlistaproducto2
    Created on : 07-nov-2018, 15:49:28
    Author     : esteban
--%>

<%@page import="java.util.List"%>
<%@page import="appventas.producto.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table"> 
<tr>
    <th>CODIGO</th>
    <th>NOMBRE</th>
    <th>PRECIO VENTA</th>
  </tr>

    <% 

 List<Producto> productos = (ArrayList<Producto>)request.getSession().getAttribute("arrayproducto");
 
    for(Producto producto : productos)
    { %>
    <% if((String) request.getSession().getAttribute("modulo")=="movimiento"){ %>
    <tr>
        <td onclick="asignadetalle(<% out.print(producto.getProductocod()); %>,'<%  out.print(producto.getProductonom()); %>',<% out.print(producto.getProductoprecom()); %>,<% out.print(producto.getProductoprevent()); %>,<% out.print(producto.getProductoiva()); %>);" ><% out.print(producto.getProductocod()); %></td>
    
    <td><% out.print(producto.getProductonom()); %></td>
 
    <td><% out.print(producto.getProductoprevent()); %></td>
    
    </tr>
    <% }else{ %>
        <tr>
        <td onclick="document.formproducto.ProductoCod.value=<% out.print(producto.getProductocod()); %>; document.formproducto.ACC.value='BUSCAR';  document.formproducto.submit();"><% out.print(producto.getProductocod()); %></td>
        <td><% out.print(producto.getProductonom()); %></td>
        <td><% out.print(producto.getProductoprevent()); %></td>
        </tr>
   
    <%   
   }
}
 %> 
</table>