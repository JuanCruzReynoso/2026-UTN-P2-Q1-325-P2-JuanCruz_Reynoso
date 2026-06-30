package biblioteca;

/**
 *
 * @author Juan Cruz Reynoso
 */
public class Socio extends Persona implements Identificable {
    private String email;

    public Socio(String dni, String nombre, String apellido, String email) throws Exception {
        super(dni, nombre, apellido);
        this.email = email;
    }


    @Override
    public String getId() {
        return this.dni; // El DNI funciona como clave única en el HashMap
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (DNI: " + dni + ") -" + email;
    }
}