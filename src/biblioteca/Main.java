package biblioteca;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Biblioteca biblioteca = new Biblioteca();

        // Intentamos cargar datos serializados previos
        biblioteca.socios.cargarDesdeLista(ArchivoUtil.cargarDatos("socios.dat"));
        biblioteca.libros.cargarDesdeLista(ArchivoUtil.cargarDatos("libros.dat"));
        biblioteca.prestamos.cargarDesdeLista(ArchivoUtil.cargarDatos("prestamos.dat"));

        // Si no hay datos (ej. primera ejecución), precargamos un par para facilitar la corrección
        if (biblioteca.libros.listar().isEmpty()) {
            cargarDatosPrueba(biblioteca);
        }

        ConsolaUI consola = new ConsolaUI(biblioteca);
        consola.iniciar();
    }

    private static void cargarDatosPrueba(Biblioteca biblioteca) {
        // Libros
        try {
            biblioteca.libros.agregar(new Libro("LIB001", "El señor de los anillos", "J.R.R. Tolkien", 1954));
            biblioteca.libros.agregar(new Libro("LIB002", "Cien años de soledad", "Gabriel García Márquez", 1967));
            biblioteca.libros.agregar(new Libro("LIB003", "Don Quijote de la Mancha", "Miguel de Cervantes", 1605));
            biblioteca.libros.agregar(new Libro("LIB004", "La sombra del viento", "Carlos Ruiz Zafón", 2001));
            biblioteca.libros.agregar(new Libro("LIB005", "1984", "George Orwell", 1949));
        } catch (Exception e) {
            System.out.println("Error precargando libros: " + e.getMessage());
        }

        // Socios
        try {
            biblioteca.socios.agregar(new Socio("30111222", "Juan", "Pérez", "juan.perez@email.com"));
            biblioteca.socios.agregar(new Socio("30222333", "María", "García", "maria.garcia@email.com"));
            biblioteca.socios.agregar(new Socio("30333444", "Carlos", "López", "carlos.lopez@email.com"));
        } catch (Exception e) {
            System.out.println("Error precargando socios: " + e.getMessage());
        }

        System.out.println("Datos de prueba precargados correctamente.");
    }
}
