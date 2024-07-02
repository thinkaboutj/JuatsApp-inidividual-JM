package interfaces;

import dominio.Mensaje;
import java.util.List;

/**
 * Interfaz para el acceso a datos de la entidad Mensaje.
 * Define métodos para operaciones CRUD (crear, leer, actualizar, eliminar) en la base de datos.
 * 
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public interface IMensajeDAO {

    /**
     * Crea un nuevo mensaje en la base de datos.
     * 
     * @param mensaje El mensaje a crear.
     * @return El mensaje creado, o null si ocurre un error.
     */
    public Mensaje createMensaje(Mensaje mensaje);

    /**
     * Lee un mensaje de la base de datos.
     * 
     * @param mensaje El mensaje a leer (debe tener el ID del mensaje).
     * @return El mensaje leído, o null si no se encuentra.
     */
    public Mensaje readMensaje(Mensaje mensaje);

    /**
     * Actualiza un mensaje en la base de datos.
     * 
     * @param mensaje El mensaje a actualizar.
     * @return El mensaje actualizado, o null si ocurre un error.
     */
    public Mensaje updateMensaje(Mensaje mensaje);

    /**
     * Elimina un mensaje de la base de datos.
     * 
     * @param mensaje El mensaje a eliminar.
     */
    public void deleteMensaje(Mensaje mensaje);

    /**
     * Obtiene todos los mensajes almacenados en la base de datos.
     * 
     * @return Una lista de mensajes.
     */
    public List<Mensaje> readAllMensaje();

    /**
     * Obtiene todos los mensajes de un chat específico por su ID.
     * 
     * @param chatId El ID del chat para el cual se obtienen los mensajes.
     * @return Una lista de mensajes del chat especificado.
     */
    List<Mensaje> readMensajesByChatId(long chatId);

}
