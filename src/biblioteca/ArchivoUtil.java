package biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Cruz Reynoso
 */
public class ArchivoUtil {
    
    // Serialización genérica
    public static <T> void guardarDatos(String ruta, ArrayList<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(lista);
        }
    }

    // Deserialización genérica
    @SuppressWarnings("unchecked")
    public static <T> List<T> cargarDatos(String ruta) throws IOException, ClassNotFoundException {
        File file = new File(ruta);
        if (!file.exists()) return new ArrayList<>(); // Si no existe el archivo, retorna lista vacía
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se pudo cargar " + ruta + " (se iniciara vacio).");
            return new ArrayList<>();
        }
    }
}