package global.modelo;
import java.io.*;
import java.util.ArrayList;

public class Persistencia {

    // Guarda una lista de objetos en un archivo
    public static <T extends Serializable> void guardarLista(ArrayList<T> lista, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga una lista de objetos desde un archivo
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> cargarLista(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>(); // Retorna una lista vacía si el archivo no existe o está vacío
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (ArrayList<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

