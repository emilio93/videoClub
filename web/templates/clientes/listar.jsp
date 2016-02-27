<%@page import="videoClub.bd.ClientesBD"%>
<%@page import="videoClub.sistema.Cliente"%>
<%@page import="java.util.ArrayList"%>
<h2>
    <span style="cursor: pointer" id="activador-listar">
        <i id="chev-listar" class="fa fa-chevron-circle-right"></i>
        Lista de Clientes
    </span>
</h2>
<div id="ventana-listar">
    <%
    ClientesBD cbd = new ClientesBD();
    ArrayList<Cliente> lc = cbd.obtener();
    if (lc != null && lc.size() != 0) {
    %>
    <div class="row text-center">
        <div class="col-sm-1"><b>Cedula</b></div>
        <div class="col-sm-1"><b>Nombre</b></div>
        <div class="col-sm-1"><b>Apellido1</b></div>
        <div class="col-sm-1"><b>Apellido2</b></div>
        <div class="col-sm-2"><b>Email</b></div>
        <div class="col-sm-1"><b>Telefono</b></div>
        <div class="col-sm-2"><b>Dirección</b></div>
        <div class="col-sm-3"><b>Opciones</b></div>
    </div>
    <%
        for (Cliente c : lc) {
    %>
    <div class="row text-center">
        <div class="col-sm-1"><%=c.getCedula()%></div>
        <div class="col-sm-1"><%=c.getNombre()%></div>
        <div class="col-sm-1"><%=c.getApellido1()%></div>
        <div class="col-sm-1"><%=c.getApellido2()%></div>
        <div class="col-sm-2"><%=c.getEmail()%></div>
        <div class="col-sm-1"><%=c.getTelefono()%></div>
        <div class="col-sm-2"><%=c.getDireccion()%></div>
        <div class="col-sm-1">
            <button class="btn btn-info btn-sm btn-block">Info <i class="fa fa-info-circle"></i></button>
        </div>
        <div class="col-sm-1">
            <button class="btn btn-warning btn-sm btn-block">Editar <i class="fa fa-edit"></i></button>
        </div>
        <div class="col-sm-1">
            <button class="btn btn-danger btn-sm btn-block">Quitar <i class="fa fa-trash"></i></button>
        </div>
    </div>
    <%
        }
    } else {
    %>
    <div class="row text-center">
        <div class="col-sm-12">
            <p>No han obtenido registros. Puede deberse a un problema t�cnico o bien no existen registros.</p>
        </div>
    </div>
    <%
    }
    %>
</div>
