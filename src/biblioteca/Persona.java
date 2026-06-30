
package biblioteca;
import java.io.Serializable;

/**
 *
 * @author Juan Cruz Reynoso
 */
public abstract class Persona implements Serializable {
    protected String dni;
    protected String nombre;
    protected String apellido;

    public Persona(String dni, String nombre, String apellido) {
        if (dni == null || dni.trim().isEmpty())
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        if (apellido == null || apellido.trim().isEmpty())
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
}