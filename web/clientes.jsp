<%@page import="java.sql.*"%>
<%@page import="videoClub.bd.BD"%>
<%@page import="videoClub.bd.ClientesBD"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="videoClub.sistema.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cosmo/bootstrap.min.css" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
        <style>
            <%@include file="/swal/sweetalert.css" %>
        </style>
    </head>
    <body style="display: none">

        <div class="container">
            <%@ include file="/templates/header.jsp" %>
            <%@ include file="/templates/clientes/agregar.jsp" %>
            <hr>
            <%@ include file="/templates/clientes/listar.jsp" %>
        </div>
    </body>
<script>
<%@ include file="/swal/sweetalert.min.js" %>
<%@ include file="/js/Http.js" %>
<%@ include file="/js/HttpClientes.js" %>
<%@ include file="/js/windowToggle.js" %>

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
var httpAgregar = new HttpClientes("/ejecutor", "post", {
    expectedStatus: 200,
    params: { pedido: "agregarCliente" }
});
document.getElementById("boton-agregar").addEventListener("click", function() { httpAgregar.agregar() });

/*
 * Mostrar Contenido.
 */
document.getElementsByTagName("body")[0].style.display = "";
</script>
</html>
