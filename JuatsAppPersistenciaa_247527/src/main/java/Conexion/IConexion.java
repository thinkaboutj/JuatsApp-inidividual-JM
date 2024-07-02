/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interfaz para la gestión de conexiones a la base de datos. Define métodos
 * para crear y cerrar conexiones.
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public interface IConexion {

    /**
     * Crea una nueva conexión a la base de datos.
     *
     * @return Una instancia de Connection representando la nueva conexión.
     */
    public Connection crearConexion();

    /**
     * Cierra la conexión actual a la base de datos.
     *
     * Este método debe ser llamado para liberar recursos y cerrar la conexión
     * después de que ya no sea necesaria.
     */
    public void cerrarConexion();

}
