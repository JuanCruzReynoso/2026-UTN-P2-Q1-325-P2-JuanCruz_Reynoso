package biblioteca;

import java.util.Scanner;

/**
 * Clase dedicada a la interfaz de usuario por consola. Extrae toda la lógica
 * del menú de Main para mantener separación de responsabilidades.
 *
 * @author Juan Cruz Reynoso
 */
public class ConsolaUI {

    private final Biblioteca biblioteca;
    private final Scanner scanner;

    public ConsolaUI(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;

        do {
            System.out.println("\n===== BIBLIOTECA UTN AVELLANEDA =====");
            System.out.println("1. Registrar Socio");
            System.out.println("2. Registrar Libro");
            System.out.println("3. Listar Socios");
            System.out.println("4. Listar Libros Disponibles");
            System.out.println("5. Buscar Libro por Código");
            System.out.println("6. Registrar Préstamo");
            System.out.println("7. Registrar Devolución");
            System.out.println("8. Mostrar Préstamos Activos");
            System.out.println("9. Generar Informe Institucional (txt)");
            System.out.println("10. Guardar y Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        registrarSocio();
                        break;
                    case 2:
                        registrarLibro();
                        break;
                    case 3:
                        biblioteca.socios.listar().forEach(System.out::println);
                        break;
                    case 4:
                        biblioteca.libros.listar().stream().filter(Libro::isDisponible).forEach(System.out::println);
                        break;
                    case 5:
                        buscarLibro();
                        break;
                    case 6:
                        realizarPrestamo();
                        break;
                    case 7:
                        registrarDevolucion();
                        break;
                    case 8:
                        biblioteca.prestamos.listar().stream()
                                .filter(Prestamo::isActivo)
                                .forEach(System.out::println);
                        break;
                    case 9:
                        biblioteca.generarInforme("informe.txt");
                        break;
                    case 10:
                        ArchivoUtil.guardarDatos("socios.dat", biblioteca.socios.listar());
                        ArchivoUtil.guardarDatos("libros.dat", biblioteca.libros.listar());
                        ArchivoUtil.guardarDatos("prestamos.dat", biblioteca.prestamos.listar());

                        System.out.println("Datos guardados, Nos vemos!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("Error de negocio: " + e.getMessage());
            }
        } while (opcion != 10);

        scanner.close();
    }

    private void registrarSocio() throws Exception {
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Nombre: ");
        String nom = scanner.nextLine();
        System.out.print("Apellido: ");
        String ape = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        biblioteca.socios.agregar(new Socio(dni, nom, ape, email));
        System.out.println("Socio registrado.");
    }

    private void registrarLibro() throws Exception {
        System.out.print("Código: ");
        String cod = scanner.nextLine();
        System.out.print("Título: ");
        String tit = scanner.nextLine();
        System.out.print("Autor: ");
        String aut = scanner.nextLine();
        System.out.print("Año Publicación: ");
        int anio = Integer.parseInt(scanner.nextLine());
        biblioteca.libros.agregar(new Libro(cod, tit, aut, anio));
        System.out.println("Libro registrado.");
    }

    private void buscarLibro() {
        System.out.print("Ingrese código del libro a buscar: ");
        Libro l = biblioteca.libros.buscar(scanner.nextLine());
        System.out.println(l != null ? l : "Libro no encontrado.");
    }

    private void realizarPrestamo() throws Exception {
        System.out.print("Código del libro: ");
        String codLib = scanner.nextLine();
        System.out.print("DNI del socio: ");
        String dniSocio = scanner.nextLine();
        biblioteca.realizarPrestamo(codLib, dniSocio);
    }

    private void registrarDevolucion() throws Exception {
        System.out.print("ID del préstamo a devolver: ");
        biblioteca.registrarDevolucion(scanner.nextLine());
    }
}
