package videoClub.bd;

import java.sql.ResultSet;
import java.util.ArrayList;
import videoClub.log.Log;
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
}
