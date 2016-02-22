<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

String[] uri = request.getRequestURI().split("/");
String pagina = uri[uri.length-1];
String clientes = pagina.equals("clientes.jsp")? "class=\"active\"": "";
String peliculas = pagina.equals("peliculas.jsp")? "class=\"active\"": "";
String prestamos = pagina.equals("prestamos.jsp")? "class=\"active\"": "";

%>
<header>
    <h1><b>Video Club</b></h1>
</header>
<ul class="nav nav-tabs">
  <li role="presentation" <%=clientes%>><a href="clientes">Clientes</a></li>
  <li role="presentation" <%=peliculas%>><a href="peliculas">Películas</a></li>
  <li role="presentation" <%=prestamos%>><a href="prestamos">Préstamos</a></li>
</ul>
