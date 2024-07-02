/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Usuario;
import java.util.List;

/**
 * Interfaz para el acceso a datos de la entidad Usuario. Define métodos para
 * operaciones CRUD (crear, leer, actualizar, eliminar) en la base de datos.
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public interface IUsuarioDAO {

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario El usuario a crear.
     * @return El usuario creado, o null si ocurre un error.
     */
    public Usuario createUsuario(Usuario usuario);

    /**
     * Lee un usuario de la base de datos.
     *
     * @param usuario El usuario a leer (debe tener el ID del usuario).
     * @return El usuario leído, o null si no se encuentra.
     */
    public Usuario readUsuario(Usuario usuario);

    /**
     * Actualiza un usuario en la base de datos.
     *
     * @param usuario El usuario a actualizar.
     * @return El usuario actualizado, o null si ocurre un error.
     */
    public Usuario updateUsuario(Usuario usuario);

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param usuario El usuario a eliminar.
     */
    public void deleteUsuario(Usuario usuario);

    /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     *
     * @return Una lista de usuarios.
     */
    public List<Usuario> readAllUsuario();

    /**
     * Crea un nuevo usuario en la base de datos sin incluir una imagen de
     * perfil.
     *
     * @param usuario El usuario a crear.
     * @return El usuario creado, o null si ocurre un error.
     */
    public Usuario createUsuarioSimagen(Usuario usuario);

}
