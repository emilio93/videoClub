/*
 * Emilio Rojas 2016.
 */
HttpClientes = function(url, metodo, args) {

    this.url = url;
    this.metodo = metodo;
    this.args = args;
    
    this.ejecutar = function(data) {

    };
    
    this.agregar = function() {
        var http = new Http(url, metodo, args);
        swal({
            title: "Agregando Cliente",
            text: "<i class='fa fa-spinner fa-spin fa-2x'></i>",
            html: true,
            showConfirmButton: false
        });
        console.log(this);
        http.enviar();
    };
};
