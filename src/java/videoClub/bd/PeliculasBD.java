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
                    rs.getInt("idPelicula"),
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

    public ArrayList<Pelicula> obtener() {
        return obtener(0, 0);
    }
            
    public ArrayList<Pelicula> obtener(int cantidad, int pagina) {
        ArrayList<Pelicula> lp = null;
        try {
            PreparedStatement stmt = preparar(
                "call getPeliculas(?, ?)",
                cantidad,
                pagina
            );
            lp = rsToListaPeliculas(stmt.executeQuery());
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró leer las películas de "
                    + "la base de datos: " + e.getMessage()));
        }
        return lp;
    }
    
    public Pelicula obtener(int id) {
            Pelicula pelicula = null;
        try {
            PreparedStatement stmt = preparar(
                "call getPelicula(?)",
                id
            );
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pelicula = new Pelicula(
                    rs.getInt("idPelicula"),
                    rs.getString("titulo"),
                    rs.getString("direccion"),
                    rs.getString("produccion"),
                    rs.getInt("ano"),
                    rs.getString("genero"),
                    rs.getInt("duracion"),
                    rs.getString("sinopsis"),
                    rs.getInt("cantidad")
                );
            }
        } catch (Exception e) {
            log.log(
                    Level.WARNING,
                    "No se logró leer la película con id "
                    + "{0} de la base de datos: " + e.getMessage() + ". ",
                    id);
            log.info(e.getMessage());
            setError(getError() + "No se logró leer la película con id "
                    + id + " de la base de datos: " + e.getMessage() + ". <br>");
            for (StackTraceElement stackTrace : e.getStackTrace()) {
                setError(getError() + stackTrace + "<br>");
            }
        }
        return pelicula;
    }
    
    public Pelicula obtener(String titulo) {
            Pelicula pelicula = null;
        try {
            PreparedStatement stmt = preparar(
                "call getPeliculaPorTitulo(?)",
                titulo
            );
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pelicula = new Pelicula(
                    rs.getInt("idPelicula"),
                    rs.getString("titulo"),
                    rs.getString("direccion"),
                    rs.getString("produccion"),
                    rs.getInt("ano"),
                    rs.getString("genero"),
                    rs.getInt("duracion"),
                    rs.getString("sinopsis"),
                    rs.getInt("cantidad")
                );
            }
        } catch (Exception e) {
            log.log(
                    Level.WARNING,
                    "No se logró leer la película con titulo "
                    + "{0} de la base de datos: " + e.getMessage() + ". ",
                    titulo);
            log.info(e.getMessage());
            setError(getError() + "No se logró leer la película con titulo "
                    + titulo + " de la base de datos: " + e.getMessage() + ". <br>");
            for (StackTraceElement stackTrace : e.getStackTrace()) {
                setError(getError() + stackTrace + "<br>");
            }
        }
        return pelicula;
    }
    
    public ArrayList<Pelicula> peliculasEnMora() {
    ArrayList<Pelicula> lc = null;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call getPeliculasEnMora()");
            lc = rsToListaPeliculas(stmt.executeQuery());
            close();
        } catch (Exception e) {
            log.warning(setError("No se logró obtener la lista de las películas en mora."));
            log.info(e.getMessage());
        }
        return lc;
    }

    public boolean actualizar(Pelicula pelicula) {
        return false;
    }

    public boolean eliminar(int id) {
        return false;
    }
}
