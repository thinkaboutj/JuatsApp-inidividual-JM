package dao;

import Conexion.ConexionBD;
import Conexion.IConexion;
import dominio.Chat;
import dominio.GeneroUsuario;
import dominio.Usuario;
import interfaces.IUsuarioDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase de acceso a datos para la entidad Usuario.
 *
 * Implementa operaciones de creación, lectura, actualización y eliminación de
 * usuarios en la base de datos.
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public class UsuarioDAO implements IUsuarioDAO {

    private IConexion conexionBD;

    /**
     * Constructor que inicializa UsuarioDAO con una conexión a la base de
     * datos.
     *
     * @param conexionBD La conexión a la base de datos.
     */
    public UsuarioDAO(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario El usuario a crear.
     * @return El usuario creado con su ID asignado, o null si no se pudo crear.
     */
    @Override
    public Usuario createUsuario(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "INSERT INTO Usuarios (telefono, genero, perfil, fechaNacimiento, contrasenya, domicilio_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getTelefono());
            ps.setString(2, usuario.getGenero().toString());
            ps.setBytes(3, usuario.getPerfil().getDatosImagen());
            ps.setTimestamp(4, new Timestamp(usuario.getFechaNacimiento().getTimeInMillis()));
            ps.setString(5, usuario.getContrasenya());
            ps.setInt(6, usuario.getDomicilio().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }
            rs.close();
            ps.close();
            return usuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al crear el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }

    /**
     * Crea un nuevo usuario en la base de datos sin imagen de perfil.
     *
     * @param usuario El usuario a crear.
     * @return El usuario creado con su ID asignado, o null si no se pudo crear.
     */
    @Override
    public Usuario createUsuarioSimagen(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "INSERT INTO Usuarios (telefono, generoUsuario, fechaNacimiento, contrasenya, id_domicilio) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getTelefono());
            ps.setString(2, usuario.getGenero().toString());
            ps.setTimestamp(3, new Timestamp(usuario.getFechaNacimiento().getTimeInMillis()));
            ps.setString(4, usuario.getContrasenya());
            ps.setInt(5, usuario.getDomicilio().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }
            rs.close();
            ps.close();
            return usuario;
        } catch (SQLException ex) {
            System.out.println("Error al crear el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }

    /**
     * Lee un usuario de la base de datos por su ID.
     *
     * @param usuario El usuario a leer (se utiliza el ID).
     * @return El usuario encontrado, o null si no se encontró.
     */
    @Override
    public Usuario readUsuario(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "SELECT * FROM Usuarios WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuarioEncontrado = new Usuario();
                usuarioEncontrado.setId(rs.getInt("id"));
                usuarioEncontrado.setTelefono(rs.getString("telefono"));
                // Resto de la asignación de atributos
                return usuarioEncontrado;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }

    /**
     * Actualiza un usuario en la base de datos.
     *
     * @param usuario El usuario a actualizar.
     * @return El usuario actualizado, o null si no se pudo actualizar.
     */
    @Override
    public Usuario updateUsuario(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "UPDATE Usuarios SET telefono = ?, genero = ?, perfil = ?, fechaNacimiento = ?, contrasenya = ?, domicilio_id = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getTelefono());
            ps.setString(2, usuario.getGenero().toString());
            ps.setBytes(3, usuario.getPerfil().getDatosImagen());
            ps.setTimestamp(4, new Timestamp(usuario.getFechaNacimiento().getTimeInMillis()));
            ps.setString(5, usuario.getContrasenya());
            ps.setInt(6, usuario.getDomicilio().getId());
            ps.setInt(7, usuario.getId());
            ps.executeUpdate();
            ps.close();
            return usuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param usuario El usuario a eliminar.
     */
    @Override
    public void deleteUsuario(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "DELETE FROM Usuarios WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
    }

    /**
     * Lee todos los usuarios de la base de datos.
     *
     * @return Una lista de usuarios, o null si no se pudieron leer.
     */
    @Override
    public List<Usuario> readAllUsuario() {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "SELECT * FROM Usuarios";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setContrasenya(rs.getString("contrasenya"));
                // Obtener el género del usuario directamente del ResultSet
                String genero = rs.getString("generoUsuario");
                if (genero != null) {
                    usuario.setGenero(GeneroUsuario.valueOf(genero));
                } else {
                    usuario.setGenero(GeneroUsuario.OTRO); // Valor por defecto si el campo está vacío en la base de datos
                }
                // Obtener la fecha de nacimiento del usuario directamente del ResultSet
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                if (fechaNacimiento != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fechaNacimiento);
                    usuario.setFechaNacimiento(cal);
                }
                // Obtener la lista de chats del usuario
                usuario.setChats(obtenerChatsUsuario(usuario.getId()));
                usuarios.add(usuario);
            }
            rs.close();
            stmt.close();
            return usuarios;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener todos los usuarios: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }

    /**
     * Método privado para obtener la lista de chats de un usuario según su ID.
     *
     * @param idUsuario El ID del usuario.
     * @return Una lista de chats del usuario, o null si no se pudieron obtener.
     */
    private List<Chat> obtenerChatsUsuario(int idUsuario) {
        List<Chat> chats = new ArrayList<>();
        // Lógica para obtener la lista de chats del usuario según su ID en la base de datos
        String sqlUsuarioChat = "SELECT id_chat FROM usuario_chat WHERE id_usuario = ?";
        String sqlChat = "SELECT * FROM chat WHERE id = ?";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement pstmtUsuarioChat = conn.prepareStatement(sqlUsuarioChat)) {

            pstmtUsuarioChat.setInt(1, idUsuario);

            try (ResultSet rsUsuarioChat = pstmtUsuarioChat.executeQuery()) {
                while (rsUsuarioChat.next()) {
                    long chatId = rsUsuarioChat.getLong("id_chat");

                    try (PreparedStatement pstmtChat = conn.prepareStatement(sqlChat)) {
                        pstmtChat.setLong(1, chatId);

                        try (ResultSet rsChat = pstmtChat.executeQuery()) {
                            if (rsChat.next()) {
                                Chat chat = new Chat();
                                chat.setIdChat(rsChat.getLong("id"));
                                chat.setNombre(rsChat.getString("nombre"));
                                // Otros atributos del chat según tu estructura de base de datos
                                chats.add(chat);
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los chats del usuario: " + ex.getMessage());
        }

        return chats;
    }
}
