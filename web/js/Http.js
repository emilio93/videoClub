/*
 * Emilio Rojas 2016.
 */
var Http = function(url, metodo, args) {

    this.url = url;

    /*
     * Puede ser get, post, put, delete
     */
    this.metodo = metodo.toUpperCase();

    /*
     * expectedStatus   int
     * params           Array
     * 						name       String
     * 		   				value      String
     *
     * Adicionalmente params requiere algunos ciertos pares.
     * pedido El pedido
     *
     */
    this.args = args;


    /*
     * jsonResponse boolean
     * htmlResponse boolean
     * showAlert    boolean
     * content      String
     */
    this.data = null;

    this.httpReq = new XMLHttpRequest();

    this.enviar = function() {
        this.httpReq.onreadystatechange = this.manejar();
        this.httpReq.open(metodo, this.url, true);
        if (metodo === "POST" || metodo === "PUT" || metodo === "DELETE") {
            this.httpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            this.httpReq.setRequestHeader("Content-length", params.length);
            this.httpReq.setRequestHeader("Connection", "close");
        }
        var fd = new FormData();
        if (typeof(args.params) === 'undefined' && Array.isArray(args.params)) {
            for (var param in args.params) {
                fd.append(param.name, param.value);
            }
        }
        this.httpReq.send(fd);
    };

    /**
     * Maneja la respuesta a un llamado http.
     * @param  {Object} ejecutor Implementa la funcion ejecutar con el parámetro
     *                           data que maneja la respuesta del servidor.
     */
    this.manejar = function(ejecutor) {
        if (this.httpReq.readyState === XMLHttpRequest.DONE) {
            if (this.httpReq.status === args.expectedStatus) {
                data = JSON.parse(this.httpReq.responseText);
                swal.close();
            } else {
                console.log("El servidor ha devuelto el estado: " + this.httpReq.status);
            }
        }
    };

    this.actualizarHtml = function(destino, contenido) {
        document.getElementById(destino).innerHTML = contenido;
    };

    this.mostrarAlerta = function(mensaje) {
        
    };
};