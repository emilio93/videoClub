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

    this.velocidad = 50;

    this.activador = $(activador);
    this.ventana = $(ventana);
    this.chev = $(chev);

    this.estado = null;

    // Asignar rotación del icono, segun ethis.estado
    this.setChev = function() {
        var faClass = 'fa fa-chevron-circle-';
        faClass += this.estado? 'down': 'right';
        this.chev.attr('class', faClass);
    };

    // Asignar el estado de la ventana.
    this.setEstado = function(mostrar) {
        this.estado = mostrar;
        if (this.estado) {
            this.ventana.show(this.velocidad);
        } else {
            this.ventana.hide(this.velocidad);
        }
        this.setChev(this.estado);
    };

    // Cambiar el estado de la ventana.
    this.cambiar = function() {
        this.setEstado(!this.estado);
    };

    // Inicialmente se muestran las ventanas cerradas.
    this.setEstado(false);
};
