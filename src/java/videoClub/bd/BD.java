package videoClub.bd;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.util.logging.Logger;

import videoClub.log.Log;

/**
 * Maneja la conexión con la base de datos.
 * @author Emilio Rojas
 */
public final class BD {
    private static final Logger log = Logger.getLogger(BD.class.getName());

    private Connection con;
    private Properties config;
    private String url;
    private String user;
    private String pass;
    private String error;

    /**
    * Inicia un objeto BD con o sin conexión establecida según el parámetro
    * <pre>conectar</pre>.
    * @param conectar True para crear la conexión, false para no crearla.
    */
    public BD(boolean conectar) {
        Log.start(log);
        if (conectar) conectar();
    }

    /**
     * Inicia un objeto BD sin una conexión establecida.
     */
    public BD() { this(false); }

    /**
     * Obtiene el url para la conexión con la base de datos.
     */
    public String getUrl() { return url; }

    /**
     * Obtiene el string de error al instante solicitado.
     */
    public String getError() { return error; }

    /**
     * Asigna los parámetros para la conexión con la base de datos.
     */
    public void setConfig() {
        Properties conf = new Properties();
        try (InputStream p = this.getClass().getClassLoader().getResourceAsStream("db.properties")) {
            conf.load(p);
        } catch (Exception e) {
            error = e.getMessage();
            log.warning("No se logró obtener la configuración de la base de datos.");
            log.info(e.getMessage());
        }

        String driver = "jdbc:mysql://"; // conf.getProperty("db.driver");
        String host = "localhost"; // conf.getProperty("db.host");
        String port = "3306"; // conf.getProperty("db.port");
        String name = "videoclub"; // conf.getProperty("db.name");
        user = "emilio"; // conf.getProperty("db.user");
        pass = "pass"; // conf.getProperty("db.pass");
        url = driver + host + ":" + port + "/" + name;
    }

    /**
     * Se conecta con la base de datos según los parámetros establecidos.
     * @return La conexión a la base de datos.
     */
    public Connection conectar() {
        setConfig();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(url, user, pass);
            log.info("Conexión con la bse de datos creada.");
            error = "Conexion creada";
        } catch (Exception e) {
            log.warning("No se logró conectar a la base de datos.");
            log.info(e.getMessage());
            error = "Conexion no creada" + e.getMessage();
        }
        return con;
    }

    /**
     * Devuelve el objeto Connection con la conexión a la base de datos. De ser
     * necesario se hace la conexión.
     * @return La conexión a la base de datos.
     */
    public Connection getCon() {
        if(con == null) conectar();
        return con;
    }
}
