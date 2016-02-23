package videoClub.bd;

import java.sql.*;
import java.util.logging.Level;
import java.util.ArrayList;
import videoClub.sistema.Cliente;

import videoClub.log.Log;

public class ClientesBD extends Consultor{

    public ClientesBD() {
        Log.start(log);
    }

    private ArrayList<Cliente> rsToListaClientes(ResultSet rs) {
        ArrayList<Cliente> lc = null;
        try {
            lc = new ArrayList<>();
            while (rs.next()) {
                lc.add(new Cliente(
                        rs.getInt("id"),
                        rs.getInt("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getInt("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                ));
            }
        } catch (Exception e) {
            log.warning(setError("No se logró crear la lista de clientes."));
            log.info(e.getMessage());
        }

        return lc;
    }

    public boolean agregar(Cliente cliente) {
        boolean exito = false;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call addCliente(?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, cliente.getCedula());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido1());
            stmt.setString(4, cliente.getApellido2());
            stmt.setInt(5, cliente.getTelefono());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getDireccion());

            exito = stmt.executeUpdate() == 1;
            log.log(
                    Level.INFO,
                    "Agregando cliente a la base de datos: {0}",
                    Boolean.toString(exito));
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró agregar el cliente a la base de datos."));
            log.info(e.getMessage());
        }

        return exito;
    }

    public leerId(int id) {
        Cliente cliente = null;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call getCliente(?)");
                    stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs != null) {
                rs.next();
                cliente = new Cliente(
                    rs.getInt("idCliente"),
                    rs.getInt("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido1"),
                    rs.getString("apellido1"),
                    rs.getInt("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion")
                );
            }
        } catch (Exception e) {
            log.log(
                    Level.WARNING,
                    "No se logró leer el cliente con id "
                    + "{0} de la base de datos: " + e.getMessage(),
                    id);
            log.info(e.getMessage());
            setError("No se logró leer el cliente con id "
                    + id + " de la base de datos: " + e.getMessage() + "<br>");
            for (StackTraceElement stackTrace : e.getStackTrace()) {
                setError(getError() + stackTrace + "<br>");
            }
        }
        return cliente;
    }

    public Cliente leer(int cedula) {
        return leerId(getIdFromCedula(cedula));
    }

    public ArrayList<Cliente> leer() {
        return leer(0, 0);
    }

    public ArrayList<Cliente> leer(int cantidad, int pagina) {
        ArrayList<Cliente> lc = null;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call getClientes(?, ?)");
            stmt.setInt(1, cantidad);
            stmt.setInt(2, pagina);
            lc = rsToListaClientes(stmt.executeQuery());
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró leer los clientes de "
                    + "la base de datos: " + e.getMessage()));
        }
        return lc;
    }

    public ArrayList<Cliente> clientesMorosos() {
        ArrayList<Cliente> lc = null;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call getClientesMorosos()");
            lc = rsToListaClientes(stmt.executeQuery());
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró obtener la lista de los clientes morososs."));
            log.info(e.getMessage());
        }
        return lc;
    }

    public boolean actualizar(Cliente cliente) {
        boolean exito = false;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call updateCliente(?, ?, ?, ?, ?, ?, ?, ?");
            stmt.setInt(1, cliente.getId());
            stmt.setInt(2, cliente.getCedula());
            stmt.setString(3, cliente.getNombre());
            stmt.setString(4, cliente.getApellido1());
            stmt.setString(5, cliente.getApellido2());
            stmt.setString(6, cliente.getEmail());
            stmt.setInt(7, cliente.getTelefono());
            stmt.setString(8, cliente.getDireccion());

            exito = stmt.executeUpdate() == 1;
            log.log(
                    Level.INFO,
                    "Actualizando cliente en la base de datos: {0}",
                    Boolean.toString(exito));
            close();
        } catch (Exception e) {
            log.log(
                    Level.WARNING,
                    "No se logró actualizar el cliente con cedula {0} de la base de datos.",
                    cliente.getCedula());
            log.info(e.getMessage());
            setError("No se logró actualizar el cliente con cedula {0} de la base de datos.");
        }
        return exito;
    }

    public boolean eliminar(int cedula) {
        boolean exito = false;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call deleteCliente(?)");
            stmt.setInt(1, getIdFromCedula(cedula));

            exito = stmt.executeUpdate() == 1;
            log.log(
                    Level.INFO,
                    "Eliminando cliente en la base de datos: {0}",
                    Boolean.toString(exito)
            );
            close();
        } catch (Exception e) {
            log.log(
                    Level.WARNING,
                    "No se logró eliminar el cliente con cedula {0} de la base de datos.",
                    cedula
            );
            log.info(e.getMessage());
            setError("No se logró eliminar el cliente con cedula "
                    + cedula + " de la base de datos: " + e.getMessage());
        }
        return exito;
    }

    public boolean esMoroso(int cedula) {
        boolean moroso = false;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call esMoroso(?)");
            stmt.setInt(1, getIdFromCedula(cedula));
            moroso = stmt.executeQuery().getInt("moroso") > 0;
            close();
        } catch (Exception e) {
            log.log(
                    Level.WARNING,
                    "No se logró determinar la morosidad del cliente con cedula {0}.",
                    cedula);
            log.info(e.getMessage());
        }
        return moroso;
    }

    private int getIdFromCedula(int cedula) {
    int id = 0;
    try {
        PreparedStatement stmt = getCon()
                .prepareStatement("call getIdFromCedula(?)");
        stmt.setInt(1, cedula);
        id = stmt.executeQuery().getInt("idCliente");
        if (id <= 0) {
            log.log(
                    Level.WARNING,
                    "No se encontró el cliente con cédula {0}",
                    cedula
            );
        }
        close();
    } catch (Exception e) {
        log.warning("No se logró crear la lista de clientes.");
        log.info(e.getMessage());
    }
    return id;
}

    public int contarPrestamosCliente(int cedula) {
        int prestamos = 0;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call contarPrestamosCliente(?)");
            stmt.setInt(1, getIdFromCedula(cedula));
            prestamos = stmt.executeQuery().getInt("prestamos");
            close();
        } catch (Exception e) {
            log.log(
                    Level.WARNING,
                    "No se logró contar los prestamos del cliente con cedula {0}",
                    cedula
            );
            log.info(e.getMessage());
        }
        return prestamos;
    }
}
