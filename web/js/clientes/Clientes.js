<%@ page language="java" pageEncoding="UTF-8"%>
$(document).ready( function() {
    /*
     * Window Toggle.
     */
    var toggleAgregar = new wToggle('#activador-agregar', '#ventana-agregar', '#chev-agregar');
    var toggleListar = new wToggle('#activador-listar', '#ventana-listar', '#chev-listar');
    $('#activador-agregar').click( function() { toggleAgregar.cambiar(); });
    $('#activador-listar').click( function() { toggleListar.cambiar(); });

    /* HTTP. */
    var httpAgregar = new HttpClientesAgregar('clientes/ejecutor', 'post', {});
    var httpListar = new HttpClientesListar('clientes/ejecutor', 'get', {});
    $('#activador-listar').click( function() {
        if (toggleListar.estado) {
            httpListar.obtener();
        }
    });
    $('#form-agregar').submit( function() {
        httpAgregar.agregar();
    });

    $('#clientes-listado-todos').click( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'todos'
        };
        $(this).attr('class', 'active');
        $('#clientes-listado-morosos').attr('class', '');
        httpListar.obtener();
    });
    $('#clientes-listado-morosos').click( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'morosos'
        };
        $(this).attr('class', 'active');
        $('#clientes-listado-todos').attr('class', '');
        httpListar.obtener();
    });
    $('#clientes-listado-buscar').submit( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'cedula',
            'cedula': $('#buscar-cedula').val()
        };
        $('#clientes-listado-todos').attr('class', '');
        $('#clientes-listado-morosos').attr('class', '');
        httpListar.obtener();
    });

    /*
     * Mostrar Contenido.
     */
    $('body').css({'display': ''});
});
