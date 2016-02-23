/*
 * Emilio Rojas 2016.
 */
var httpCliente = function(url, metodo, args) {

    this.prototype = new http(url, metodo, args);

    this.ejecutar = function(data) {

    };

};
