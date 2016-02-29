<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Préstamos</title>
        <style media="screen">
            <%@ include file="/swal/sweetalert.css" %>
            span { /* Evita sleccion en los spans */
                -webkit-user-select: none; /* webkit (safari, chrome) browsers */
                -moz-user-select: none; /* mozilla browsers */
                -khtml-user-select: none; /* webkit (konqueror) browsers */
                -ms-user-select: none; /* IE10+ */
            }
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cosmo/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-git2.min.js" charset="utf-8"></script>
    </head>
    <body style="display: none">

        <div class="container">
            <%@ include file="/templates/header.jsp" %>
            <%@ include file="/templates/prestamos/agregar.html" %>
            <hr>
            <%@ include file="/templates/prestamos/listar.html" %>
            <%@ include file="/templates/footer.jsp" %>
        </div>
    </body>
<script>
<%@ include file="/swal/sweetalert.min.js" %>
    <%@ include file="/js/windowToggle.js" %>
    <%@ include file="/js/Http.js" %>
    <%@ include file="/js/prestamos/HttpPrestamosAgregar.js" %>
    <%@ include file="/js/prestamos/HttpPrestamosListar.js" %>
    <%@ include file="/js/prestamos/HttpPrestamosFinalizar.js" %>
    <%@ include file="/js/prestamos/Prestamos.js" %>
</script>
</html>
