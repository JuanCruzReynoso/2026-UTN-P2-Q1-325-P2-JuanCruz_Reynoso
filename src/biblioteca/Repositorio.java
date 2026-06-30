package biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Juan Cruz Reynoso
 */
public class Repositorio<T extends Identificable> {
    // Usamos HashMap para búsquedas ultra rápidas como pide el modelo 
    private HashMap<String, T> mapa = new HashMap<>();

    public void agregar(T objeto) throws Exception {
        if (mapa.containsKey(objeto.getId())) {
            throw new Exception("Error: El registro con ID '" + objeto.getId() + "' ya existe."); // Validación requerida
        }
        mapa.put(objeto.getId(), objeto);
    }

    public ArrayList<T> listar() {
        return new ArrayList<>(mapa.values()); // Retorna ArrayList para serializar
    }

    public T buscar(String id) {
        return mapa.get(id); // O(1) Búsqueda instantánea
    }

    public void eliminar(String id) {
        mapa.remove(id);
    }

    // Método para restaurar datos después de deserializar
    public void cargarDesdeLista(List<T> lista) {
        mapa.clear();
        if (lista != null) {
            for (T obj : lista) {
                mapa.put(obj.getId(), obj);
            }
        }
    }
}