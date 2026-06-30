package biblioteca;

import java.io.Serializable;

/**
 *
 * @author Juan Cruz Reynoso
 */
public class Libro implements Serializable, Identificable {
    private String codigo;
    private String titulo;
    private String autor;
    private boolean disponible;
    private int vecesPrestado; //Requisito clave para el informe institucional

    public Libro(String codigo, String titulo, String autor, int anioPublicacion) throws Exception {
        if (codigo == null || codigo.trim().isEmpty())
            throw new IllegalArgumentException("El código del libro no puede estar vacío.");
        if (titulo == null || titulo.trim().isEmpty())
            throw new IllegalArgumentException("El título del libro no puede estar vacío.");
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
        this.vecesPrestado = 0;
    }

    public String getTitulo() { return titulo; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public int getVecesPrestado() { return vecesPrestado; }
    public void registrarPrestamo() { this.vecesPrestado++; }
    
    @Override
    public String getId() {
        return this.codigo; // El código del libro funciona como clave
    }

    @Override
    public String toString() {
        return String.format("[%s] %s por %s - %s (Prestado %d veces)",
                codigo, titulo, autor, disponible ? "DISPONIBLE" : "PRESTADO", vecesPrestado);
    }
}