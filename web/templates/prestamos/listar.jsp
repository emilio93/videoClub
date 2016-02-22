<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2>
    <span style="cursor:hand;">
        <i class="fa fa-chevron-circle-right"></i>
        Mostrar Préstamos
    </span>
</h2>
<div>
    <div class="row">
        <div class="col-sm-6">
            <h3>Filtrar por Cliente</h3>
            <form class="form-horizontal">
                <div class="form-group form-group-sm">
                    <label for="cedula" class="col-sm-4 control-label">Cédula cliente:</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="cedula">
                    </div>
                </div>
                <button class="btn btn-primary btn-block">Filtrar</button>
            </form>
        </div>
        <div class="col-sm-6">
            <h3>Filtrar por Película</h3>
            <form class="form-horizontal">
                <div class="form-group form-group-sm">
                    <label for="película" class="col-sm-4 control-label">Nombre película:</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="película">
                    </div>
                </div>
                <button class="btn btn-primary btn-block">Filtrar</button>
            </form>
        </div>
    </div>

    <hr>

    <div class="row text-center">
        <div class="col-sm-2"></div>
        <div class="col-sm-1"><b>Cliente</b></div>
        <div class="col-sm-1"><b>Cédula</b></div>
        <div class="col-sm-1"><b>Película</b></div>
        <div class="col-sm-1"><b>Salida</b></div>
        <div class="col-sm-1"><b>Devolución</b></div>
        <div class="col-sm-1"><b>En Mora</b></div>
        <div class="col-sm-1"><b>Cobro</b></div>
        <div class="col-sm-1"><b>Devolución</b></div>
        <div class="col-sm-2"></div>
    </div>
    <div class="row text-center">
        <div class="col-sm-2"></div>
        <div class="col-sm-1">Cliente</div>
        <div class="col-sm-1">Cédula</div>
        <div class="col-sm-1">Película</div>
        <div class="col-sm-1">Salida</div>
        <div class="col-sm-1">Devolución</div>
        <div class="col-sm-1">En Mora</div>
        <div class="col-sm-1">Cobro</div>
        <div class="col-sm-1">
            <button class="btn btn-primary btn-sm btn-block">Finalizar</button>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>
