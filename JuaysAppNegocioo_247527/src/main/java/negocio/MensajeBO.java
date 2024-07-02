/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dao.MensajeDAO;
import dominio.Mensaje;
import interfaces.IMensajeDAO;
import java.util.List;

/**
 * Clase de negocio para operaciones relacionadas con mensajes.
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public class MensajeBO {

    IMensajeDAO mensajeDAO;

    /**
     * Constructor que inicializa el objeto MensajeBO con un objeto IMensajeDAO
     * específico.
     *
     * @param mensajeDAO El objeto IMensajeDAO a utilizar para las operaciones
     * de datos del mensaje.
     */
    public MensajeBO(IMensajeDAO mensajeDAO) {
        this.mensajeDAO = mensajeDAO;
    }

    /**
     * Constructor por defecto que inicializa el objeto MensajeBO con un objeto
     * MensajeDAO por defecto.
     */
    public MensajeBO() {
        this.mensajeDAO = new MensajeDAO();
    }

    /**
     * Crea un nuevo mensaje en la base de datos.
     *
     * @param mensaje El objeto Mensaje que se va a crear.
     * @return El objeto Mensaje creado con los datos persistidos.
     */
    public Mensaje crearMensaje(Mensaje mensaje) {
        return mensajeDAO.createMensaje(mensaje);
    }

    /**
     * Obtiene un mensaje por su ID desde la base de datos.
     *
     * @param mensaje El objeto Mensaje con el ID del mensaje a obtener.
     * @return El objeto Mensaje obtenido con los datos persistidos.
     */
    public Mensaje obtenerMensajePorId(Mensaje mensaje) {
        return mensajeDAO.readMensaje(mensaje);
    }

    /**
     * Obtiene todos los mensajes desde la base de datos.
     *
     * @return Una lista de objetos Mensaje con todos los mensajes de la base de
     * datos.
     */
    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeDAO.readAllMensaje();
    }

    /**
     * Actualiza un mensaje existente en la base de datos.
     *
     * @param mensaje El objeto Mensaje con los datos actualizados.
     * @return El objeto Mensaje actualizado con los datos persistidos.
     */
    public Mensaje actualizarMensaje(Mensaje mensaje) {
        return mensajeDAO.updateMensaje(mensaje);
    }

    /**
     * Elimina un mensaje de la base de datos.
     *
     * @param mensaje El objeto Mensaje a eliminar.
     */
    public void eliminarMensaje(Mensaje mensaje) {
        mensajeDAO.deleteMensaje(mensaje);
    }

    /**
     * Obtiene todos los mensajes de un chat específico desde la base de datos.
     *
     * @param chatId El ID del chat cuyos mensajes se van a obtener.
     * @return Una lista de objetos Mensaje con todos los mensajes del chat
     * especificado.
     */
    public List<Mensaje> obtenerMensajesPorChatId(long chatId) {
        return mensajeDAO.readMensajesByChatId(chatId);
    }
}
