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
    var httpAgregar = new HttpPeliculasAgregar('peliculas/ejecutor', 'post', {});
    var httpListar = new HttpPeliculasListar('peliculas/ejecutor', 'get', {});
    $('#activador-listar').click( function() {
        if (toggleListar.estado) {
            httpListar.obtener();
            $('#peliculas-listado-todas').attr('class', 'active');
        }
    });
    $('#form-agregar').submit( function() {
        httpAgregar.agregar();
        if (toggleListar.estado) {
            $('#activador-listar').click();
            $('#activador-listar').click();
        }
    });

    $('#peliculas-listado-todas').click( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'todas'
        };
        $(this).attr('class', 'active');
        $('#peliculas-listado-moras').attr('class', '');
        httpListar.obtener();
    });
    $('#peliculas-listado-morosos').click( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'moras'
        };
        $(this).attr('class', 'active');
        $('#peliculas-listado-todos').attr('class', '');
        httpListar.obtener();
    });
    $('#peliculas-listado-buscar').submit( function() {
        httpListar.args.params = {
            'pedido': 'obtener',
            'conjunto': 'titulo',
            'titulo': $('#buscar-titulo').val()
        };
        $('#peliculas-listado-todas').attr('class', '');
        $('#peliculas-listado-moras').attr('class', '');
        httpListar.obtener();
    });


    /*
     * Mostrar Contenido.
     */
    $('body').css({'display': ''});
});
