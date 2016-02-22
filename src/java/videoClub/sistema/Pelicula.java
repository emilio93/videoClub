package videoClub.sistema;

/**
 * Representación de una película.
 * @author Emilio Rojas
 */
public class Pelicula {
    
    private int id;
    private String titulo;
    private String dirección;
    private String producción;
    private int año;
    private String genero;
    private int duración; // En minutos.
    private String sinopsis;
    private int cantidad; // De ejemplares.

    public Pelicula() {}
    
    public Pelicula(
            String titulo, 
            String dirección, 
            String producción, 
            int año, 
            String genero, 
            int duración, 
            String sinopsis, 
            int cantidad
    ) {
        this.titulo = titulo;
        this.dirección = dirección;
        this.producción = producción;
        this.año = año;
        this.genero = genero;
        this.duración = duración;
        this.sinopsis = sinopsis;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public String getProducción() {
        return producción;
    }

    public void setProducción(String producción) {
        this.producción = producción;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuración() {
        return duración;
    }

    public void setDuración(int duración) {
        this.duración = duración;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

