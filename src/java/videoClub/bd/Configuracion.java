package videoClub.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import videoClub.log.Log;

/**
 * Se encarga de manejar los datos de configuración para el sistema.
 * Actualmente se permite únicamente la obtención de datos, estando 
 * deshabilitada la actualización y eliminación de registros.
 * @author Emilio Rojas.
 */
public class Configuracion extends Consultor{
    
    public Configuracion() {
        Log.start(log);
    }
    
    private ResultSet getRs(String nombre) {
        ResultSet rs = null;
        try {
            PreparedStatement stmt = getCon()
                    .prepareStatement("call getConfiguracion(?)");
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            log.log(
                Level.WARNING, 
                "No se logró leer la configuración solicitada: {0}", 
                e.getMessage()
            );
            setError("No se logró leer la configuración solicitada: " + 
                    e.getMessage());
            for (StackTraceElement stackTrace : e.getStackTrace()) {
                setError(getError() + stackTrace + "<br>");
            }
        }
        return rs;
    }

    public int getInt(String nombre) {
        int valor = Integer.MIN_VALUE;
        ResultSet rs = getRs(nombre);
        try {
            if (rs.next()) { 
                valor = Integer.parseInt(rs.getString("valor"));
            }
        } catch (Exception e) {
            log.log(
                Level.WARNING, 
                "No se logró leer la configuración solicitada: {0}", 
                e.getMessage()
            );
            setError("No se logró leer la configuración solicitada: " + 
                    e.getMessage());
            for (StackTraceElement stackTrace : e.getStackTrace()) {
                setError(getError() + stackTrace + "<br>");
            }
        }
        
        return valor;
    }
    
    public String getString(String nombre) {
        String valor = null;
        ResultSet rs = getRs(nombre);
        try {
            if (rs.next()) { 
                valor = rs.getString("valor");
            }
        } catch (Exception e) {
            log.log(
                Level.WARNING, 
                "No se logró leer la configuración solicitada: {0}", 
                e.getMessage()
            );
            setError("No se logró leer la configuración solicitada: " + 
                    e.getMessage());
            for (StackTraceElement stackTrace : e.getStackTrace()) {
                setError(getError() + stackTrace + "<br>");
            }
        }
        return valor;
    }
    
    public String getDescripcion(String nombre) {
        String descripcion = null;
        ResultSet rs = getRs(nombre);
        try {
            if (rs.next()) { 
                descripcion = rs.getString("descripcion");
            }
        } catch (Exception e) {
            log.log(
                Level.WARNING, 
                "No se logró leer la configuración solicitada: {0}", 
                e.getMessage()
            );
            setError("No se logró leer la configuración solicitada: " + 
                    e.getMessage());
            for (StackTraceElement stackTrace : e.getStackTrace()) {
                setError(getError() + stackTrace + "<br>");
            }
        }
        return descripcion;
    }
    
}
