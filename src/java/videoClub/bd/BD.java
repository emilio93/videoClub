package videoClub.bd;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

import videoClub.log.Log;

/**
 * Maneja la conexión con la base de datos.
 */
public final class BD {
    private static final Logger log = Logger.getLogger(BD.class.getName());
    
    private Connection con;
    private Properties config;
    private String url;
    private String user;
    private String pass;
    private String error;
    public String getError() { return error; }
    
    /**
     * Inicia un objeto BD sin una conexión establecida.
     */
    public BD() {
        this(false);
    }
    
    /**
     * Inicia un objeto BD con o sin conexión establecida según el parámetro
     * <pre>conectar</pre>.
     * @param conectar True para crear la conexión, false para no crearla.
     */
    public BD(boolean conectar) {
        Log.start(log);
        if (conectar) conectar();
    }
    
    public String getUrl() { return url; }
    
    /**
     * Asigna los parámetros para la conexión con la base de datos.
     */
    public void setConfig() {
        java.util.Properties conf = new java.util.Properties();
        try (java.io.InputStream is = this.getClass().getClassLoader().getResourceAsStream("/db.properties")) {
            conf.load(is);
        } catch (Exception e) {
            error = e.getMessage();
            log.warning("No se logró obtener la configuración de la base de datos.");
            log.info(e.getMessage());
        }
        
        String driver = conf.getProperty("db.driver");
        String host = conf.getProperty("db.host");
        String port = conf.getProperty("db.port");
        String name = conf.getProperty("db.name");
        user = conf.getProperty("db.user");
        pass = conf.getProperty("db.pass");
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
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vieoclub", "emilio", "pass");
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
     * Devuelve el objeto Connection con la conexión a la base de datos.
     * @return La conexión a la base de datos.
     */
    public Connection getCon() {
        if(con == null) conectar();
        return con;
    }
}
