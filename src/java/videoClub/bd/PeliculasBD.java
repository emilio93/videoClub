package videoClub.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import videoClub.log.Log;
import java.util.logging.Level;
import videoClub.sistema.Pelicula;

/**
 *
 * @author Emilio Rojas
 */
public class PeliculasBD extends Consultor{

    public PeliculasBD() {
        Log.start(log);
    }

    private ArrayList<Pelicula> rsToListaPeliculas(ResultSet rs) {
        ArrayList<Pelicula> lp = null;
        try {
            lp = new ArrayList<>();
            while(rs.next()) {
                lp.add(new Pelicula(
                    rs.getString("titulo"),
                    rs.getString("direccion"),
                    rs.getString("produccion"),
                    rs.getInt("ano"),
                    rs.getString("genero"),
                    rs.getInt("duracion"),
                    rs.getString("sinopsis"),
                    rs.getInt("cantidad")
                ));
            }
        } catch (Exception e) {
            log.warning("No se logró crear la lista de peliculas.");
            log.info(e.getMessage());
        }

        return lp;
    }

    public boolean agregar(Pelicula pelicula) {
        boolean exito = false;
        try {
            PreparedStatement stmt = preparar(
                "call addPelicula(?, ?, ?, ?, ?, ?, ?, ?)",
                pelicula.getTitulo(),
                pelicula.getDireccion(),
                pelicula.getProduccion(),
                pelicula.getAno(),
                pelicula.getGenero(),
                pelicula.getDuracion(),
                pelicula.getSinopsis(),
                pelicula.getCantidad()
            );
            exito = stmt.executeUpdate() == 1;
            log.log(
                    Level.INFO,
                    "Agregando película a la base de datos: {0}",
                    Boolean.toString(exito));
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró agregar la película a la base de datos."));
            log.info(e.getMessage());
        }
        return exito;
    }

    Pelicula obtener(int aInt) {
        return null;
    }
}
