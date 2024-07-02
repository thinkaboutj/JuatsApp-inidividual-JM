/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dao.ChatDAO;
import dominio.Chat;
import interfaces.IChatDAO;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase de negocio para operaciones relacionadas con chats.
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public class ChatBO {

    IChatDAO chatDAO;

    /**
     * Constructor que inicializa el objeto ChatBO con un objeto IChatDAO
     * específico.
     *
     * @param chatDAO El objeto IChatDAO a utilizar para las operaciones de
     * datos del chat.
     */
    public ChatBO(IChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    /**
     * Constructor por defecto que inicializa el objeto ChatBO con un objeto
     * ChatDAO por defecto.
     */
    public ChatBO() {
        this.chatDAO = new ChatDAO();
    }

    /**
     * Inserta un nuevo chat en la base de datos.
     *
     * @param chat El objeto Chat que se va a insertar.
     * @throws SQLException Si ocurre un error durante la inserción.
     */
    public void insertarChat(Chat chat) throws SQLException {
        chatDAO.insertar(chat);
    }

    /**
     * Inserta un nuevo chat sin imagen en la base de datos.
     *
     * @param chat El objeto Chat que se va a insertar sin imagen.
     * @throws SQLException Si ocurre un error durante la inserción.
     */
    public void insertarChatsinImagen(Chat chat) throws SQLException {
        chatDAO.insertarSinImagen(chat);
    }

    /**
     * Inserta un nuevo chat sin imagen y con un usuario específico en la base
     * de datos.
     *
     * @param chat El objeto Chat que se va a insertar sin imagen.
     * @param idUsuario El ID del usuario asociado al chat.
     * @throws SQLException Si ocurre un error durante la inserción.
     */
    public void insertarChatsinImagenYconUsuario(Chat chat, int idUsuario) throws SQLException {
        chatDAO.insertarSinImagenYconUsuario(chat, idUsuario);
    }

    /**
     * Obtiene un chat por su ID desde la base de datos.
     *
     * @param id El ID del chat a obtener.
     * @return El objeto Chat obtenido con los datos persistidos.
     * @throws SQLException Si ocurre un error durante la obtención.
     */
    public Chat obtenerChatPorId(Long id) throws SQLException {
        return chatDAO.obtenerPorId(id);
    }

    /**
     * Obtiene todos los chats desde la base de datos.
     *
     * @return Una lista de objetos Chat con todos los chats de la base de
     * datos.
     * @throws SQLException Si ocurre un error durante la obtención.
     */
    public List<Chat> obtenerTodosLosChats() throws SQLException {
        return chatDAO.obtenerTodos();
    }

    /**
     * Actualiza un chat existente en la base de datos.
     *
     * @param chat El objeto Chat con los datos actualizados.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
    public void actualizarChat(Chat chat) throws SQLException {
        chatDAO.actualizar(chat);
    }

    /**
     * Elimina un chat de la base de datos.
     *
     * @param id El ID del chat a eliminar.
     * @throws SQLException Si ocurre un error durante la eliminación.
     */
    public void eliminarChat(Long id) throws SQLException {
        chatDAO.eliminar(id);
    }
}
