<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <h2>
        <span style="cursor:hand;" id="activador-agregar">
            <i id="chev-agregar" class="fa fa-chevron-circle-right"></i>
            Agregar Nuevo Cliente
        </span>
    </h2>

    <div id="ventana-agregar">
        <form class="form-horizontal">
            <div class="form-group form-group-sm">
                <label for="nombre" class="col-sm-1 control-label">Nombre:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="nombre" required>
                </div>
                <label for="apellido1" class="col-sm-1 control-label">Apellido1:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="apellido1" required>
                </div>
                <label for="apellido2" class="col-sm-1 control-label">Apellido2:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="apellido2" required>
                </div>
            </div>
            <hr>
            <div class="form-group form-group-sm">
                <label for="email" class="col-sm-1 control-label">Email:</label>
                <div class="col-sm-5">
                    <input type="email" class="form-control" id="email" required>
                </div>
                <label for="telefono" class="col-sm-1 control-label">Telefono:</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="telefono" required>
                </div>
            </div>
            <hr>
            <div class="form-group form-group-sm">
                <label for="direccion" class="col-sm-1 control-label">Dirección:</label>
                <div class="col-sm-11">
                    <textarea class="form-control" id="direccion" required></textarea>
                </div>
            </div>
            <button class="btn btn-primary btn-block">Agregar</button>
        </form>
    </div>
    
</div>
