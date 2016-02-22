package videoClub.bd;

import java.sql.Connection;
import java.util.logging.Logger;

/**
 *
 * @author Emilio Rojas
 */
public abstract class Consultor {
    protected static final Logger log = Logger.getLogger(ClientesBD.class.getName());
    protected Connection con = null;
    private String error;
    
    public String getError() { 
        return this.error; 
    }
    
    protected String setError(String error) { 
        this.error = error; 
        return this.error;
    }
    
    protected Connection getCon() {
        if (con == null) {
            BD bd;
            bd = new BD(true);
            con = bd.getCon();
        }
        return con;
    }
    
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
