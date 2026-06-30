package biblioteca;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Juan Cruz Reynoso
 */
public class Prestamo implements Serializable, Identificable {
    private String id;
    private Libro libro;
    private Socio socio;
    private LocalDate fechaPrestamo;
    private boolean activo;

    public Prestamo(String id, Libro libro, Socio socio) {
        this.id = id;
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = LocalDate.now();
        this.activo = true;
    }

    public Libro getLibro() { return libro; }
    public Socio getSocio() { return socio; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public boolean isActivo() { return activo; }
    
    public void registrarDevolucion() {
        this.activo = false;
        this.libro.setDisponible(true);
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public String toString() {
        return "Prestamo #" + id + " | Socio: " + socio.getDni() + " | Libro: " + libro.getId() + " | Activo: " + (activo ? "Sí" : "No");
    }
}