package biblioteca;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 *
 * @author Juan Cruz Reynoso
 */
public class Biblioteca {

    public Repositorio<Libro> libros = new Repositorio<>();
    public Repositorio<Socio> socios = new Repositorio<>();
    public Repositorio<Prestamo> prestamos = new Repositorio<>();

    public void realizarPrestamo(String codigoLibro, String dniSocio) throws Exception {
        Libro libro = libros.buscar(codigoLibro);
        Socio socio = socios.buscar(dniSocio);

        if (libro == null) {
            throw new Exception("El libro no existe.");
        }
        if (socio == null) {
            throw new Exception("El socio no existe.");
        }
        if (!libro.isDisponible()) {
            throw new Exception("El libro no está disponible actualmente.");
        }

        libro.setDisponible(false);
        libro.registrarPrestamo();

        String id = UUID.randomUUID().toString().substring(0, 6);
        Prestamo prestamo = new Prestamo(id, libro, socio);
        prestamos.agregar(prestamo);
        System.out.println("Préstamo registrado con éxito. ID de préstamo: " + prestamo.getId());
    }

    public void registrarDevolucion(String idPrestamo) throws Exception {
        Prestamo prestamo = prestamos.buscar(idPrestamo);
        if (prestamo == null || !prestamo.isActivo()) {
            throw new Exception("Préstamo inexistente o ya devuelto.");
        }
        prestamo.registrarDevolucion();
        System.out.println("Devolución registrada. El libro '" + prestamo.getLibro().getTitulo() + "' vuelve a estar disponible.");
    }

    public void generarInforme(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            int prestamosActivos = (int) prestamos.listar().stream().filter(Prestamo::isActivo).count();
            int librosDisponibles = (int) libros.listar().stream().filter(Libro::isDisponible).count();

            Libro masPrestado = null;
            for (Libro l : libros.listar()) {
                if (masPrestado == null || l.getVecesPrestado() > masPrestado.getVecesPrestado()) {
                    masPrestado = l;
                }
            }

            bw.write("=== INFORME INSTITUCIONAL DE BIBLIOTECA UTN ===");
            bw.newLine();
            bw.write("Fecha y hora de generación: " + dtf.format(LocalDateTime.now()));
            bw.newLine();
            bw.write("----------------------------------------");
            bw.newLine();
            bw.write("Total de socios registrados: " + socios.listar().size());
            bw.newLine();
            bw.write("Total de libros en catalogo: " + libros.listar().size());
            bw.newLine();
            bw.write("Cantidad de prestamos activos: " + prestamosActivos);
            bw.newLine();
            bw.write("Cantidad de libros disponibles: " + librosDisponibles);
            bw.newLine();
            bw.write("----------------------------------------");
            bw.newLine();

            if (masPrestado != null && masPrestado.getVecesPrestado() > 0) {
                bw.write("Libro mas solicitado: " + masPrestado.getTitulo() + " (" + masPrestado.getVecesPrestado() + " prestamos)");
                bw.newLine();
            } else {
                bw.write("Libro mas solicitado: Aun no hay registros suficientes");
                bw.newLine();
            }

            System.out.println("Informe generado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error generando informe: " + e.getMessage());
        }
    }
}
