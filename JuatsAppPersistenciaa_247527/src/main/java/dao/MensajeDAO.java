package dao;

import Conexion.ConexionBD;
import dominio.Mensaje;
import dominio.Usuario;
import interfaces.IMensajeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Clase de acceso a datos para la entidad Mensaje.
 * 
 * Implementa operaciones de creación, lectura, actualización y eliminación de mensajes en la base de datos.
 * 
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public class MensajeDAO implements IMensajeDAO {

    private final ConexionBD conexionBD;

    /**
     * Constructor que inicializa MensajeDAO con una conexión a la base de datos.
     * 
     * @param conexionBD La conexión a la base de datos.
     */
    public MensajeDAO(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public MensajeDAO() {
        this.conexionBD = new ConexionBD();
    }

     /**
     * Crea un nuevo mensaje en la base de datos.
     * 
     * @param mensaje El mensaje a crear.
     * @return El mensaje creado con su ID asignado, o null si no se pudo crear.
     */
    @Override
    public Mensaje createMensaje(Mensaje mensaje) {
        try (Connection conexion = conexionBD.crearConexion()) {
            String query;
            if (mensaje.getImagen() != null) {
                query = "INSERT INTO mensajes (texto, fechaHoraRegistro, id_usuario, id_chat, imagen_id) VALUES (?, ?, ?, ?, ?)";
            } else {
                query = "INSERT INTO mensajes (texto, fechaHoraRegistro, id_usuario, id_chat) VALUES (?, ?, ?, ?)";
            }
            PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, mensaje.getTexto());
            statement.setTimestamp(2, new Timestamp(mensaje.getFechaHoraRegistro().getTimeInMillis()));
            statement.setInt(3, mensaje.getUsuario().getId());
            statement.setLong(4, mensaje.getChat().getIdChat());
            if (mensaje.getImagen() != null) {
                statement.setLong(5, mensaje.getImagen().getIdImagen());
            }
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    mensaje.setId(generatedKeys.getInt(1));
                }
                return mensaje;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Lee un mensaje de la base de datos por su ID.
     * 
     * @param mensaje El mensaje a leer (se utiliza el ID).
     * @return El mensaje encontrado, o null si no se encontró.
     */
    @Override
    public Mensaje readMensaje(Mensaje mensaje) {
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "SELECT * FROM mensajes WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, mensaje.getId());
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Mensaje mensajeEncontrado = new Mensaje();
                mensajeEncontrado.setId(resultado.getInt("id"));
                mensajeEncontrado.setTexto(resultado.getString("texto"));
                Calendar fechaHoraRegistro = Calendar.getInstance();
                fechaHoraRegistro.setTime(resultado.getTimestamp("fechaHoraRegistro"));
                mensajeEncontrado.setFechaHoraRegistro(fechaHoraRegistro);
                // Set the usuario and imagen fields
                // mensajeEncontrado.setUsuario(usuarioDAO.readUsuarioById(resultado.getInt("usuario_id")));
                // mensajeEncontrado.setImagen(imagenDAO.readImagenById(resultado.getLong("imagen_id")));
                return mensajeEncontrado;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Actualiza un mensaje en la base de datos.
     * 
     * @param mensaje El mensaje a actualizar.
     * @return El mensaje actualizado, o null si no se pudo actualizar.
     */
    @Override
    public Mensaje updateMensaje(Mensaje mensaje) {
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "UPDATE mensajes SET texto = ?, fechaHoraRegistro = ?, id_usuario = ?, imagen_id = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, mensaje.getTexto());
            statement.setTimestamp(2, new Timestamp(mensaje.getFechaHoraRegistro().getTimeInMillis()));
            statement.setInt(3, mensaje.getUsuario().getId());
            if (mensaje.getImagen() != null) {
                statement.setLong(4, mensaje.getImagen().getIdImagen());
            } else {
                statement.setNull(4, Types.BIGINT);
            }
            statement.setInt(5, mensaje.getId());
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                return mensaje;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

     /**
     * Elimina un mensaje de la base de datos.
     * 
     * @param mensaje El mensaje a eliminar.
     */
    @Override
    public void deleteMensaje(Mensaje mensaje) {
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "DELETE FROM mensajes WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, mensaje.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Lee todos los mensajes de la base de datos.
     * 
     * @return Una lista de mensajes, o null si no se pudieron leer.
     */
    @Override
    public List<Mensaje> readAllMensaje() {
        List<Mensaje> mensajes = new ArrayList<>();
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "SELECT * FROM mensajes";
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(query);
            while (resultado.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.setId(resultado.getInt("id"));
                mensaje.setTexto(resultado.getString("texto"));
                Calendar fechaHoraRegistro = Calendar.getInstance();
                fechaHoraRegistro.setTime(resultado.getTimestamp("fechaHoraRegistro"));
                mensaje.setFechaHoraRegistro(fechaHoraRegistro);
                // Set the usuario and imagen fields
                // mensaje.setUsuario(usuarioDAO.readUsuarioById(resultado.getInt("usuario_id")));
                // mensaje.setImagen(imagenDAO.readImagenById(resultado.getLong("imagen_id")));
                mensajes.add(mensaje);
            }
            return mensajes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

     /**
     * Lee todos los mensajes de un chat específico por su ID de chat.
     * 
     * @param chatId El ID del chat del cual leer los mensajes.
     * @return Una lista de mensajes del chat, o null si no se pudieron leer.
     */
    @Override
    public List<Mensaje> readMensajesByChatId(long chatId) {
        List<Mensaje> mensajes = new ArrayList<>();
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "SELECT * FROM mensajes WHERE id_chat = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setLong(1, chatId);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.setId(resultado.getInt("id"));
                mensaje.setTexto(resultado.getString("texto"));
                Calendar fechaHoraRegistro = Calendar.getInstance();
                fechaHoraRegistro.setTime(resultado.getTimestamp("fechaHoraRegistro"));
                mensaje.setFechaHoraRegistro(fechaHoraRegistro);
                // Set the usuario and imagen fields if needed
                mensajes.add(mensaje);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mensajes;
    }
}
