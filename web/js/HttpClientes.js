/*
 * Emilio Rojas 2016.
 */
HttpClientes = function(url, metodo, args) {

    this.url = url;
    this.metodo = metodo;
    this.args = args;

    this.ejecutar = function(data) {
    };

    this.getParams = function() {
        var params = {
            pedido: 'agregarCliente',
            nombre: document.querySelector('#nombre').value,
            apellido1: document.querySelector('#apellido1').value,
            apellido2: document.querySelector('#apellido2').value,
            cedula: document.querySelector('#cedula').value,
            email: document.querySelector('#email').value,
            telefono: document.querySelector('#telefono').value,
            direccion: document.querySelector('#direccion').value
        };
        return params;
    };

    this.validarAgregar = function() {
        var nombre = document.querySelector('#nombre');
        var apellido1 = document.querySelector('#apellido1');
        var apellido2 = document.querySelector('#apellido2');
        var cedula = document.querySelector('#cedula');
        var email = document.querySelector('#email');
        var telefono = document.querySelector('#telefono');
        var direccion = document.querySelector('#direccion');
        var check = nombre.value != '' && apellido1.value != '' && apellido2.value != '';
        check = check && email.value != '' && telefono.value != '' && direccion.value != '';
        check = check && /^\d+$/.test(telefono.value); // telefono es numero.
        return check;
    }

    this.agregar = function() {
        this.args.params = this.getParams();
        var http = new Http(this.url,this. metodo, this.args);
        if (this.validarAgregar()) {
            swal({
                title: "Agregando Cliente",
                text: "<i class='fa fa-spinner fa-spin fa-2x'></i>",
                html: true,
                showConfirmButton: false
            });
            http.enviar();
        }
    };
};
