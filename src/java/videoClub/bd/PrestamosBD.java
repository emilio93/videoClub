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
 *
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
}
