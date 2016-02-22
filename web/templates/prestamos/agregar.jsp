<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <h2>
        <span style="cursor:hand;">
            <i class="fa fa-chevron-circle-right"></i>
            Realizar Prestamos
        </span>
    </h2>

    <div>
        <form class="form-horizontal">
            <div class="form-group form-group-sm">
                <label for="cedula" class="col-sm-2 control-label">Cédula cliente:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="cedula">
                </div>
                <label for="pelicula" class="col-sm-2 control-label">Nombre Película:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="pelicula">
                </div>
            </div>
            <button class="btn btn-primary btn-block">Prestar</button>
        </form>
    </div>
</div>
