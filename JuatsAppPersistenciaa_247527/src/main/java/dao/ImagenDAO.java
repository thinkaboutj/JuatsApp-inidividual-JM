/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Conexion.ConexionBD;
import dominio.Imagen;
import dominio.Mensaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Clase de acceso a datos para la entidad Imagen.
 *
 * Implementa operaciones de inserción, obtención, actualización y eliminación
 * de imágenes en la base de datos.
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public class ImagenDAO {

    private ConexionBD conexionBD;

    /**
     * Constructor por defecto de ImagenDAO.
     */
    public ImagenDAO() {
    }

    /**
     * Constructor que inicializa ImagenDAO con una conexión a la base de datos.
     *
     * @param conexionBD La conexión a la base de datos.
     */
    public ImagenDAO(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Inserta una nueva imagen en la base de datos.
     *
     * @param imagen La imagen a insertar.
     * @throws SQLException Si ocurre un error durante la inserción.
     */
    public void insertarImagen(Imagen imagen) throws SQLException {
        Connection conn = conexionBD.crearConexion();

        String sql = "INSERT INTO imagen (nombre, datos_imagen) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, imagen.getNombre());
            pstmt.setBytes(2, imagen.getDatosImagen());
            pstmt.executeUpdate();

            // Obtener el ID generado
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    imagen.setIdImagen(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado.");
                }
            }
        }
    }

    /**
     * Obtiene una imagen de la base de datos por su ID.
     *
     * @param idImagen El ID de la imagen a obtener.
     * @return La imagen encontrada, o null si no se encuentra.
     * @throws SQLException Si ocurre un error durante la obtención.
     */
    public Imagen obtenerImagenPorId(Long idImagen) throws SQLException {
        Connection conn = conexionBD.crearConexion();

        String sql = "SELECT nombre, datos_imagen FROM imagenes WHERE id_imagen = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, idImagen);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    byte[] datosImagen = rs.getBytes("datos_imagen");
                    return new Imagen(idImagen, nombre, datosImagen);
                }
            }
        }
        return null;
    }

    /**
     * Actualiza una imagen en la base de datos.
     *
     * @param imagen La imagen actualizada.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
    public void actualizarImagen(Imagen imagen) throws SQLException {
        Connection conn = conexionBD.crearConexion();

        String sql = "UPDATE imagen SET nombre = ?, datos_imagen = ? WHERE id_imagen = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, imagen.getNombre());
            pstmt.setBytes(2, imagen.getDatosImagen());
            pstmt.setLong(3, imagen.getIdImagen());
            pstmt.executeUpdate();
        }
    }

    /**
     * Elimina una imagen de la base de datos por su ID.
     *
     * @param idImagen El ID de la imagen a eliminar.
     * @throws SQLException Si ocurre un error durante la eliminación.
     */
    public void eliminarImagen(Long idImagen) throws SQLException {
        Connection conn = conexionBD.crearConexion();

        String sql = "DELETE FROM imagen WHERE id_imagen = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, idImagen);
            pstmt.executeUpdate();
        }
    }
}
