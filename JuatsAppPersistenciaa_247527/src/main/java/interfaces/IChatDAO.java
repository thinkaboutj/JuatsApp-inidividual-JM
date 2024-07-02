
package interfaces;

import dominio.Chat;
import java.sql.SQLException;
import java.util.List;
/**
 * Interfaz para el acceso a datos de la entidad Chat.
 * Define métodos para operaciones CRUD (crear, leer, actualizar, eliminar) en la base de datos.
 * 
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public interface IChatDAO {
    
    /**
     * Inserta un nuevo chat en la base de datos.
     * 
     * @param chat El chat a insertar.
     * @throws SQLException Si ocurre un error durante la inserción.
     */
    void insertar(Chat chat) throws SQLException;

    /**
     * Obtiene un chat de la base de datos por su ID.
     * 
     * @param id El ID del chat a obtener.
     * @return El chat encontrado, o null si no existe.
     * @throws SQLException Si ocurre un error durante la obtención.
     */
    Chat obtenerPorId(Long id) throws SQLException;

    /**
     * Obtiene todos los chats almacenados en la base de datos.
     * 
     * @return Una lista de chats, o una lista vacía si no hay chats.
     * @throws SQLException Si ocurre un error durante la obtención.
     */
    List<Chat> obtenerTodos() throws SQLException;

    /**
     * Actualiza un chat en la base de datos.
     * 
     * @param chat El chat a actualizar.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
    void actualizar(Chat chat) throws SQLException;

    /**
     * Elimina un chat de la base de datos por su ID.
     * 
     * @param id El ID del chat a eliminar.
     * @throws SQLException Si ocurre un error durante la eliminación.
     */
    void eliminar(Long id) throws SQLException;
    
    /**
     * Inserta un nuevo chat en la base de datos sin imagen.
     * 
     * @param chat El chat a insertar sin imagen.
     * @throws SQLException Si ocurre un error durante la inserción.
     */
    void insertarSinImagen(Chat chat) throws SQLException;
    
    /**
     * Inserta un nuevo chat en la base de datos sin imagen y con un usuario asociado.
     * 
     * @param chat El chat a insertar sin imagen.
     * @param idUsuario El ID del usuario asociado al chat.
     * @throws SQLException Si ocurre un error durante la inserción.
     */
    void insertarSinImagenYconUsuario(Chat chat, int idUsuario) throws SQLException;
}
