<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Películas</title>
        <style media="screen">
            <%@ include file="/swal/sweetalert.css" %>
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cosmo/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-git2.min.js" charset="utf-8"></script>
    </head>
    <body style="display: none">

        <div class="container">
            <%@ include file="/templates/header.jsp" %>
            <%@ include file="/templates/peliculas/agregar.html" %>
            <hr>
            <%@ include file="/templates/peliculas/listar.html" %>
        </div>
    </body>
<script>
<%@ include file="/swal/sweetalert.min.js" %>
    <%@ include file="/js/windowToggle.js" %>
    <%@ include file="/js/Http.js" %>
    <%@ include file="/js/peliculas/HttpPeliculasAgregar.js" %>
    <%@ include file="/js/peliculas/HttpPeliculasListar.js" %>
    <%@ include file="/js/peliculas/HttpPeliculasActualizar.js" %>
    <%@ include file="/js/peliculas/HttpPeliculasEliminar.js" %>
    <%@ include file="/js/peliculas/Peliculas.js" %>
</script>
</html>
