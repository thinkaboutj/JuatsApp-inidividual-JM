package dao;

import Conexion.ConexionBD;
import interfaces.IChatDAO;
import Conexion.IConexion;
import dominio.Chat;
import dominio.Imagen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos para la entidad Chat. Implementa la interfaz IChatDAO
 * para operaciones CRUD en la base de datos.
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public class ChatDAO implements IChatDAO {

    private final ConexionBD conexionBD;

    /**
     * Constructor que inicializa el ChatDAO con una conexión a la base de
     * datos.
     *
     * @param conexionBD La conexión a la base de datos.
     */
    public ChatDAO(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * constructor sin parametros
     */
    public ChatDAO() {
        conexionBD = new ConexionBD();
    }

    /**
     * Metodo para insertar un usuario a la base de datos
     *
     * @param chat
     * @throws SQLException
     */
    @Override
    public void insertar(Chat chat) throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "INSERT INTO Chat (nombre, miniatura) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, chat.getNombre());
        ps.setBytes(2, chat.getMiniatura().getDatosImagen());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            chat.setIdChat(rs.getLong(1));
        }
        rs.close();
        ps.close();
        conexionBD.cerrarConexion();
    }

    /**
     * Inserta un nuevo chat en la base de datos sin incluir la imagen.
     *
     * @param chat El objeto Chat a insertar.
     * @throws SQLException Si ocurre un error durante la inserción en la base
     * de datos.
     */
    @Override
    public void insertarSinImagen(Chat chat) throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "INSERT INTO Chat (nombre) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, chat.getNombre());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            chat.setIdChat(rs.getLong(1));
        }

        rs.close();
        ps.close();
        conexionBD.cerrarConexion();
    }

    /**
     * Inserta un nuevo chat en la base de datos sin incluir la imagen y
     * asociado a un usuario específico.
     *
     * @param chat El objeto Chat a insertar.
     * @param idUsuario El ID del usuario asociado al chat.
     * @throws SQLException Si ocurre un error durante la inserción en la base
     * de datos.
     */
    @Override
    public void insertarSinImagenYconUsuario(Chat chat, int idUsuario) throws SQLException {
        Connection conn = null;
        PreparedStatement psChat = null;
        PreparedStatement psUsuarioChat = null;
        ResultSet rs = null;

        try {
            conn = conexionBD.crearConexion();
            conn.setAutoCommit(false); // Iniciar transacción

            // Insertar en la tabla Chat
            String sqlChat = "INSERT INTO Chat (nombre) VALUES (?)";
            psChat = conn.prepareStatement(sqlChat, Statement.RETURN_GENERATED_KEYS);
            psChat.setString(1, chat.getNombre());
            psChat.executeUpdate();

            // Obtener la clave generada
            rs = psChat.getGeneratedKeys();
            if (rs.next()) {
                chat.setIdChat(rs.getLong(1));
            }

            // Insertar en la tabla usuario_chat
            String sqlUsuarioChat = "INSERT INTO usuario_chat (id_usuario, id_chat) VALUES (?, ?)";
            psUsuarioChat = conn.prepareStatement(sqlUsuarioChat);
            psUsuarioChat.setInt(1, idUsuario);
            psUsuarioChat.setLong(2, chat.getIdChat());
            psUsuarioChat.executeUpdate();

            conn.commit(); // Confirmar transacción
        } catch (SQLException ex) {
            if (conn != null) {
                conn.rollback(); // Revertir transacción en caso de error
            }
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psChat != null) {
                psChat.close();
            }
            if (psUsuarioChat != null) {
                psUsuarioChat.close();
            }
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
    }

    /**
     * Obtiene un chat de la base de datos por su ID.
     *
     * @param id El ID del chat a obtener.
     * @return El objeto Chat obtenido desde la base de datos, o null si no se
     * encuentra.
     * @throws SQLException Si ocurre un error durante la obtención desde la
     * base de datos.
     */
    @Override
    public Chat obtenerPorId(Long id) throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "SELECT * FROM Chat WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();
        Chat chat = null;
        if (rs.next()) {
            chat = new Chat();
            chat.setIdChat(rs.getLong("id"));
            chat.setNombre(rs.getString("nombre"));
            Imagen imagen = new Imagen();
            imagen.setDatosImagen(rs.getBytes("miniatura"));
            chat.setMiniatura(imagen);
        }
        rs.close();
        ps.close();
        conexionBD.cerrarConexion();
        return chat;
    }

    /**
     * Obtiene todos los chats de la base de datos.
     *
     * @return Una lista de objetos Chat con todos los chats de la base de
     * datos.
     * @throws SQLException Si ocurre un error durante la obtención desde la
     * base de datos.
     */
    @Override
    public List<Chat> obtenerTodos() throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "SELECT * FROM Chat";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Chat> chats = new ArrayList<>();
        while (rs.next()) {
            Chat chat = new Chat();
            chat.setIdChat(rs.getLong("idChat"));
            chat.setNombre(rs.getString("nombre"));
            Imagen imagen = new Imagen();
            imagen.setDatosImagen(rs.getBytes("miniatura"));
            chat.setMiniatura(imagen);
            chats.add(chat);
        }
        rs.close();
        ps.close();
        conexionBD.cerrarConexion();
        return chats;
    }

    /**
     * Actualiza un chat en la base de datos.
     *
     * @param chat El objeto Chat con los datos actualizados.
     * @throws SQLException Si ocurre un error durante la actualización en la
     * base de datos.
     */
    @Override
    public void actualizar(Chat chat) throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "UPDATE Chat SET nombre = ?, miniatura = ? WHERE idChat = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, chat.getNombre());
        ps.setBytes(2, chat.getMiniatura().getDatosImagen());
        ps.setLong(3, chat.getIdChat());
        ps.executeUpdate();
        ps.close();
        conexionBD.cerrarConexion();
    }

    /**
     * Elimina un chat de la base de datos por su ID.
     *
     * @param id El ID del chat a eliminar.
     * @throws SQLException Si ocurre un error durante la eliminación en la base
     * de datos.
     */
    @Override
    public void eliminar(Long id) throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "DELETE FROM Chat WHERE idChat = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.executeUpdate();
        ps.close();
        conexionBD.cerrarConexion();
    }
}
