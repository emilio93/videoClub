<%@ page language="java" pageEncoding="UTF-8"%>
$(document).ready( function() {
    /*  Window Toggle. */
    var toggleAgregar = new wToggle('#activador-agregar', '#ventana-agregar', '#chev-agregar');
    var toggleListar = new wToggle('#activador-listar', '#ventana-listar', '#chev-listar');
    $('#activador-agregar').click( function() { toggleAgregar.cambiar(); });
    $('#activador-listar').click( function() { toggleListar.cambiar(); });

    /* HTTP. */
    var httpAgregar = new HttpPrestamosAgregar('prestamos/ejecutor', 'post', {});
    var httpListar = new HttpPrestamosListar('prestamos/ejecutor', 'get', {});
    $('#activador-listar').click( function() {
        if (toggleListar.estado) {
            httpListar.obtener();
            $('#prestamos-listado-todos').attr('class', 'active');
        }
    });
    $('#form-agregar').submit( function() {
        httpAgregar.agregar();
        if (toggleListar.estado) {
            $('#activador-listar').click();
            $('#activador-listar').click();
        }
    });

    $('#prestamos-listado-todos').click( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'todos'
        };
        $(this).attr('class', 'active');
        $('#prestamos-listado-moras').attr('class', '');
        httpListar.obtener();
    });
    $('#prestamos-listado-moras').click( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'moras'
        };
        $(this).attr('class', 'active');
        $('#peliculas-listado-todos').attr('class', '');
        httpListar.obtener();
    });
    $('#prestamos-listado-buscar-cedula').submit( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'cedula',
            'cedula': $('#buscar-cedula').val()
        };
        $('#peliculas-listado-todas').attr('class', '');
        $('#peliculas-listado-moras').attr('class', '');
        httpListar.obtener();
    });
    $('#prestamos-listado-buscar-titulo').submit( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'titulo',
            'titulo': $('#buscar-titulo').val()
        };
        $('#peliculas-listado-todas').attr('class', '');
        $('#peliculas-listado-moras').attr('class', '');
        httpListar.obtener();
    });

    /* Mostrar Contenido. */
    $('body').css({'display': ''});
});
