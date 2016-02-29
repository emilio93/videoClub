<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
<<<<<<< HEAD
        <link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cosmo/bootstrap.min.css" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/swal/sweetalert.min.js"></script>
        <!--
        <style>
            <%@include file="/swal/sweetalert.css" %>
        </style>
        -->
=======
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
>>>>>>> 541722e25626a4a96ed21b3452f972b63242b7a8
    </head>
    <body style="display: none">

        <div class="container">
            <%@ include file="/templates/header.jsp" %>
            <%@ include file="/templates/clientes/agregar.html" %>
            <hr>
            <%@ include file="/templates/clientes/listar.html" %>
            <%@ include file="/templates/footer.jsp" %>
        </div>
    </body>
<script>
<<<<<<< HEAD
<%@ include file="/js/Http.js"%>
<%@ include file="/js/HttpClientes.js"%>
<%@ include file="/js/windowToggle.js"%>

/*
 * Window Toggle.
 */
var toggleAgregar = new wToggle("activador-agregar", "ventana-agregar", "chev-agregar");
var toggleListar = new wToggle("activador-listar", "ventana-listar", "chev-listar");
document.getElementById("activador-agregar").addEventListener("click", function() { toggleAgregar.cambiar(); });
document.getElementById("activador-listar").addEventListener("click", function() { toggleListar.cambiar(); });

/*
 * HTTP.
 */
var httpAgregar = new HttpClientes("ejecutor", "post", {
    expectedStatus: 200,
    params: { pedido: "agregarCliente" }
});
document.getElementById("boton-agregar").addEventListener("click", function() { httpAgregar.agregar(); });

/*
 * Mostrar Contenido.
 */
document.getElementsByTagName("body")[0].style.display = "";
=======
<%@ include file="/swal/sweetalert.min.js" %>
    <%@ include file="/js/windowToggle.js" %>
    <%@ include file="/js/Http.js" %>
    <%@ include file="/js/clientes/HttpClientesAgregar.js" %>
    <%@ include file="/js/clientes/HttpClientesListar.js" %>
    <%@ include file="/js/clientes/HttpClientesActualizar.js" %>
    <%@ include file="/js/clientes/HttpClientesEliminar.js" %>
    <%@ include file="/js/clientes/Clientes.js" %>
>>>>>>> 541722e25626a4a96ed21b3452f972b63242b7a8
</script>
</html>
