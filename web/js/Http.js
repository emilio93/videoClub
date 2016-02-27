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
        this.httpReq.open(this.metodo, this.url, true);
        var requestString = "";
        for (var param in args.params) {
            requestString += param + "=" + args.params[param] + "&";
        }
        requestString = requestString.slice(0, -1);

        if (this.metodo === "POST" || this.metodo === "PUT" || this.metodo === "DELETE") {
            this.httpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        }
        this.httpReq.onreadystatechange = function(evento) {
            console.log(this.httpReq);
            if (this.httpReq.readyState == 4) {
                console.log("El servidor contesto");
                if (this.httpReq.status == this.args.expectedStatus) {
                    this.data = JSON.parse(this.httpReq.responseText);
                    swal.close();
                    console.log("El servidor contesto bien");
                } else {
                    console.log("El servidor ha devuelto el estado: " + this.httpReq.status);
                }
            }
            else {
                console.log("El servidor no contesto" + this.httpReq.readyState);
            }
        };
        this.httpReq.send(requestString);
    };



    this.actualizarHtml = function(destino, contenido) {
        document.getElementById(destino).innerHTML = contenido;
    };
};
