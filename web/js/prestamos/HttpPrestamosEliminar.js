<%@ page language="java" pageEncoding="UTF-8"%>
/* Emilio Rojas 2016. */
HttpPrestamosFinalizar = function(url, metodo, args) {
    this.url = url;
    this.metodo = metodo;
    this.args = args;
    this.args.params = {};
    this.args.params.pedido = 'finalizar';
    this.data = null;

    this.ejecutar = function(data) {
        if (data.success == 'true') {
            swal('¡Éxito!', 'Se finalizó el présatamo a la base de datos.', 'success');
            $('#activador-listar').click();
            $('#activador-listar').click();
        } else {
            swal('Error', 'No se ha finalizado el présatamo en la base de datos.', 'error');
        }
    };

    this.error = function() {
        swal('Error', 'No se ha podido comunicar con el servidor.', 'error');
    }

    this.finalizar = function(id, cedula, titulo) {
        this.args.params.id = id;
        var http = new Http(this.url, this.metodo, this.args);
        var esto = this;
        swal({
            title: "Finalizar Préstamo",
            text: "Se finalizará el présatamo de " + titulo + ", del cliente " + cedula + ".",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Eliminar",
            closeOnConfirm: false },
            function(){
                swal({
                    title: "Finalizando Préstamo",
                    text: "<i class='fa fa-spinner fa-spin fa-2x'></i>",
                    html: true,
                    showConfirmButton: false
                });
                http.enviar(esto);
            });
    };
}
