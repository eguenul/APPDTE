<div class="container">
<div class="alert alert-success">
<strong>Iniciar descarga</strong>

<% String extension = (String) request.getSession().getAttribute("extension"); 
  if("pdf".equals(extension)){
%>
<a target="blank" href="pdfservlet"  class="btn btn-primary btn-sm">
<span class="glyphicon glyphicon-download"></span>Descargar 
</a>
<% }else{ %>
<a target="blank" href="xlsservlet"  class="btn btn-primary btn-sm">
<span class="glyphicon glyphicon-download"></span>Descargar 
</a>
<% } %>

</div>
</div>
       
