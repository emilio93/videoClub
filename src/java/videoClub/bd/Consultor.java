package videoClub.bd;

import java.sql.Connection;
import java.util.logging.Logger;

/**
 * Un consultor es una entidad encargada de realizar consultas a la base de
 * datos. Idealmente se quiere que estas entidades tengan un cierto rango de
 * consultas que tengan características comunes.
 * @author Emilio Rojas
 */
public abstract class Consultor {
    protected static final Logger log = Logger.getLogger(ClientesBD.class.getName());
    protected Connection con = null;
    private String error;

    /**
     * Obtiene el string de error al instante solicitado.
     */
    public String getError() { return error; }

    /**
     * Asigna y devuelve el string de error.
     * Se devuelve el string asignado.
     * Notese que si se quisiera concatenar con el error anterior, debe hacerse
     * lo siguiente: <tt>setError(getError()+"mi error");</tt>.
     */
    protected String setError(String error) {
        this.error = error;
        return error;
    }

    /**
     * Se crea una conexión con la base de datos si es necesario, y se obtiene
     * esta.
     */
    protected Connection getCon() {
        if (con == null) {
            BD bd;
            bd = new BD(true);
            con = bd.getCon();
        }
        return con;
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    public boolean close() {
        boolean r = false;
        try {
            con.close();
            r = true;
        } catch (Exception e) {
            log.warning(setError("No se logró cerrar la conexión con la base de datos."));
            log.info(e.getMessage());
        }
        return r;
    }
}
