package videoClub.sistema;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import videoClub.bd.Configuracion;

/**
 * Representación de un préstamos.
 * @author Emilio Rojas
 */
public class Prestamo {
    
    private int idPrestamo;// El id del préstamo.
    private Cliente cliente; // El cliente que realizo el préstamo.
    private Pelicula pelicula; // La pelicula del préstamo.
    private LocalDate salida; // La fecha de salida del préstamo(entrega al cliente).
    private LocalDate devolucion; // La fecha máxima de devolución del préstamo.
    private boolean devuelta; // Indica si se ha devuelto el préstamo.
    
    public Prestamo() {}
    
    /**
     * Mediante este constructor se puede realizar un préstamo el día actual.
     * @param cliente El cliente que realiza el préstamo.
     * @param pelicula La película del préstamo.
     */
    public Prestamo(
        Cliente cliente,
        Pelicula pelicula
    ) {
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.salida = LocalDate.now();
        int dias = new Configuracion().getInt("MAX_DIAS_RENTA");
        this.devolucion = LocalDate.now().plusDays(dias);
        this.devuelta = false;
    }
    
    public Prestamo(
        int idPrestamo,
        Cliente cliente,
        Pelicula pelicula,
        LocalDate salida,
        LocalDate devolucion,
        boolean devuelta
    ) {
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.salida = salida;
        this.devolucion = devolucion;
        this.devuelta = devuelta;
    }
    
    public int obtenerCobro() {
        return new Configuracion().getInt("COSTO_RENTA") + obtenerCobroExtra();
    }
    
    public int obtenerCobroExtra() {
        long diasExtra = getDevolucion().until(LocalDate.now(), ChronoUnit.DAYS);
        diasExtra = diasExtra > 0? diasExtra: 0;
        return (int) (diasExtra * new Configuracion().getInt("MULTA"));
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public LocalDate getSalida() {
        return salida;
    }

    public void setSalida(LocalDate salida) {
        this.salida = salida;
    }

    public LocalDate getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(LocalDate devolucion) {
        this.devolucion = devolucion;
    }

    public boolean isDevuelta() {
        return devuelta;
    }

    public void setDevuelta(boolean devuelta) {
        this.devuelta = devuelta;
    }
}
