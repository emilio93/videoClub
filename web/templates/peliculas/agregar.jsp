<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <h2>
        <span style="cursor:hand;">
            <i class="fa fa-chevron-circle-right"></i>
            Agregar Nueva Película
        </span>
    </h2>

    <div>
        <form class="form-horizontal">
            <div class="form-group form-group-sm">
                <label for="titulo" class="col-sm-1 control-label">Titulo:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="titulo">
                </div>
                <label for="direccion" class="col-sm-1 control-label">Dirección:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="direccion">
                </div>
                <label for="produccion" class="col-sm-1 control-label">Producción:</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="produccion">
                </div>
            </div>
            <hr>
            <div class="form-group form-group-sm">

                <label for="ano" class="col-sm-1 control-label">Año:</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" id="ano">
                </div>

                <label for="genero" class="col-sm-1 control-label">Género:</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" id="genero">
                </div>

                <label for="duracion" class="col-sm-1 control-label">Duración:</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" id="duracion">
                </div>

                <label for="cantidad" class="col-sm-1 control-label">Cantidad:</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" id="cantidad">
                </div>
            </div>
            <hr>
            <div class="form-group form-group-sm">
                <label for="direccion" class="col-sm-1 control-label">Sinopsis:</label>
                <div class="col-sm-11">
                    <textarea class="form-control" id="direccion"></textarea>
                </div>
            </div>
            <button class="btn btn-primary btn-block">Agregar</button>
        </form>
    </div>
</div>
