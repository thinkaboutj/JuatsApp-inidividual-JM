/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import Conexion.ConexionBD;
import dao.UsuarioDAO;
import dominio.Usuario;
import interfaces.IUsuarioDAO;
import java.util.List;

/**
 * Clase de negocio para operaciones relacionadas con usuarios.
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    /**
     * Constructor que inicializa el objeto UsuarioBO con un objeto UsuarioDAO
     * específico.
     *
     * @param usuarioDAO El objeto UsuarioDAO a utilizar para las operaciones de
     * datos del usuario.
     */
    public UsuarioBO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Constructor por defecto que inicializa el objeto UsuarioBO con un objeto
     * UsuarioDAO por defecto.
     */
    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAO(new ConexionBD());
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto Usuario que se va a crear.
     * @return El objeto Usuario creado con los datos persistidos.
     */
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioDAO.createUsuario(usuario);
    }

    /**
     * Crea un nuevo usuario en la base de datos sin imagen.
     *
     * @param usuario El objeto Usuario que se va a crear sin imagen.
     * @return El objeto Usuario creado con los datos persistidos.
     */
    public Usuario crearUsuariosinImagen(Usuario usuario) {
        return usuarioDAO.createUsuarioSimagen(usuario);
    }

    /**
     * Obtiene un usuario por su ID desde la base de datos.
     *
     * @param usuario El objeto Usuario con el ID del usuario a obtener.
     * @return El objeto Usuario obtenido con los datos persistidos.
     */
    public Usuario obtenerUsuarioPorId(Usuario usuario) {
        return usuarioDAO.readUsuario(usuario);
    }

    /**
     * Obtiene todos los usuarios desde la base de datos.
     *
     * @return Una lista de objetos Usuario con todos los usuarios de la base de
     * datos.
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.readAllUsuario();
    }

    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param usuario El objeto Usuario con los datos actualizados.
     * @return El objeto Usuario actualizado con los datos persistidos.
     */
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDAO.updateUsuario(usuario);
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param usuario El objeto Usuario a eliminar.
     */
    public void eliminarUsuario(Usuario usuario) {
        usuarioDAO.deleteUsuario(usuario);
    }
}
