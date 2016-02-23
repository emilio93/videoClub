package videoClub.bd;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import videoClub.log.Log;
import videoClub.sistema.Cliente;
import videoClub.sistema.Pelicula;
import videoClub.sistema.Prestamo;

/**
 * Se encarga de realizar las consultas pertinentes a los préstamos de
 * películas.
 * @author Emilio Rojas
 */
public class PrestamosBD extends Consultor{

    public PrestamosBD() {
        Log.start(log);
    }

    private ArrayList<Prestamo> rsToListaPrestamos(ResultSet rs) {
        ArrayList<Prestamo> lp = null;
        ClientesBD cbd = new ClientesBD();
        PeliculasBD pbd = new PeliculasBD();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        try {
            lp = new ArrayList<>();
            while(rs.next()) {
                Cliente c = cbd.leerId(rs.getInt("idCliente"));
                Pelicula p = pbd.leer(rs.getInt("idPelicula"));
                lp.add(new Prestamo(
                    rs.getInt("idPrestamo"),
                    c,
                    p,
                    LocalDate.parse(rs.getString("salida"), formatter),
                    LocalDate.parse(rs.getString("devolucion"), formatter),
                    rs.getInt("devuelta") == 1
                ));
            }
        } catch (Exception e) {
            log.warning("No se logró crear la lista de peliculas.");
            log.info(e.getMessage());
        }
        return lp;
    }

    public boolean agregar(Prestamo prestamo) {
        boolean exito = false;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call addPrestamo(?, ?, ?, ?)");
            stmt.setInt(1, prestamo.getCliente().getId());
            stmt.setInt(2, prestamo.getPelicula().getId());
            stmt.setString(3, prestamo.getSalida().toString());
            stmt.setString(4, prestamo.getDevolucion().toString());

            exito = stmt.executeUpdate() == 1;
            log.log(
                    Level.INFO,
                    "Agregando préstamo a la base de datos: {0}",
                    Boolean.toString(exito));
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró agregar el préstamo a la base de datos."));
            log.info(e.getMessage());
        }

        return exito;
    }

    public ArrayList<Prestamo> getPrestamosCliente(Cliente cliente) {
        return getPrestamosCliente(cliente.getId());
    }

    public ArrayList<Prestamo> getPrestamosCliente(int idCliente) {
        ArrayList<Prestamo> lp = null;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call getPrestamosCliente(?)");
            stmt.setInt(1, idCliente);
            lp = rsToListaPrestamos(stmt.executeQuery());
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró obtener los préstamos de la base de datos."));
            log.info(e.getMessage());
        }
        return lp;
    }

    public ArrayList<Prestamo> getPrestamosPelicula(Pelicula pelicula) {
        return getPrestamosPelicula(pelicula.getId());
    }

    public ArrayList<Prestamo> getPrestamosPelicula(int idPelicula) {
        ArrayList<Prestamo> lp = null;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call getPrestamosPelicula(?)");
            stmt.setInt(1, idPelicula);
            lp = rsToListaPrestamos(stmt.executeQuery());
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró obtener los préstamos de la base de datos."));
            log.info(e.getMessage());
        }
        return lp;
    }

    public ArrayList<Prestamo> getPrestamosClientePelicula(Cliente cliente, Pelicula pelicula) {
        return getPrestamosPelicula(cliente.getId, pelicula.getId());
    }

    public ArrayList<Prestamo> getPrestamosClientePelicula(int idCliente, int idPelicula) {
        ArrayList<Prestamo> lp = null;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call getPrestamosClientePelicula(?, ?)");
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idPelicula);
            lp = rsToListaPrestamos(stmt.executeQuery());
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró obtener los préstamos de la base de datos."));
            log.info(e.getMessage());
        }
        return lp;
    }

    public boolean finalizarPrestamo(Prestamo prestamo) {
        return finalizarPrestamo(prestamo.getIdPrestamo());
    }

    public boolean finalizarPrestamo(int idPrestamo) {
        boolean exito = false;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call finalizarPrestamo(?)");
            stmt.setInt(1, idPrestamo);
            exito = stmt.executeUpdate() == 1;
            log.log(
                    Level.INFO,
                    "Finalizando préstamo en la base de datos: {0}",
                    Boolean.toString(exito));
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró finalizar el préstamo en la base de datos."));
            log.info(e.getMessage());
        }
        return exito;
    }

    public boolean reactivarPrestamo(Prestamo prestamo) {
        return reactivarPrestamo(prestamo.getIdPrestamo());
    }

    public boolean reactivarPrestamo(int idPrestamo) {
        boolean exito = false;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call reactivarPrestamo(?)");
            stmt.setInt(1, idPrestamo);
            exito = stmt.executeUpdate() == 1;
            log.log(
                    Level.INFO,
                    "Reactivando préstamo en la base de datos: {0}",
                    Boolean.toString(exito));
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró reactivar el préstamo en la base de datos."));
            log.info(e.getMessage());
        }
        return exito;
    }
}
