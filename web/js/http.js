/*
 * Emilio Rojas 2016.
 */
var http = function(url, metodo, args) {

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

    this.http = new XMLHttpRequest();

    this.enviar = function() {
        http.onreadystatechange = manejar;
        http.open(etodo, this.url, true);
        if (metodo === "POST" || metodo === "PUT" || metodo === "DELETE") {
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            http.setRequestHeader("Content-length", params.length);
            http.setRequestHeader("Connection", "close");
        }
        var fd = new FormData();
        if (typeof(args.params) === 'undefined' && Array.isArray(args.params)) {
            for (var param in args.params) {
                fd.append(param.name, param.value);
            }
        }
        http.send(fd);
    }

    /**
     * Maneja la respuesta a un llamado http.
     * @param  {Object} ejecutor Implementa la funcion ejecutar con el parámetro
     *                           data que maneja la respuesta del servidor.
     */
    this.manejar = function(ejecutor) {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === args.expectedStatus) {
                data = JSON.parse(http.responseText);
                ejecutor.ejecutar(data);
            } else {
                console.log("El servidor ha devuelto el estado: " + http.status);
            }
        }
    }

    this.actualizarHtml = function(destino, contenido) {
        document.getElementById(destino).innerHTML = contenido;
    }

    this.mostrarAlerta = function(mensaje) {
        
    }
}
