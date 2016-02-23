/*
 * Emilio Rojas 2016.
 */

/**
 * Permite cambiar el estado de abierto a cerrado o viceversa de una dada
 * ventana.
 * @param {String} activador El id del elemento que activa el toggle.
 * @param {String} ventana El id de la ventana que se quiere abrir/cerrar.
 * @param {String} chev Id del icono que indica el estado de la ventana.
 */
var wToggle = function(activador, ventana, chev) {
    this.activador = document.getElementById(activador);
    this.ventana = document.getElementById(ventana);
    this.chev = document.getElementById(chev);

    // Asignar rotación del icono.
    this.setChev = function() {
        var faBase = "fa fa-chevron-circle-";
        this.chev.className = this.mostrado()? faBase + "down": faBase + "right";
    };
    
    // Indicar estado de la ventana.
    this.mostrado = function() {
        return this.ventana.style.display === "none"? false: true;
    };

    // Asignar el estado de la ventana.
    this.setEstado = function(mostrar) {
        this.ventana.style.display = mostrar? "": "none";
        this.setChev();
    };

    // Cambiar el estado de la ventana.
    this.cambiar = function() {
        if (this.mostrado()) this.setEstado(false);
        else this.setEstado(true);
    };
    
    // Inicialmente se muestran las ventanas cerradas.
    this.setEstado(false);
};
