/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import Conexion.ConexionBD;
import java.sql.Connection;

/**
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ConexionBD conexionBD = new ConexionBD();
        
        // Crear la conexión
        Connection conexion = conexionBD.crearConexion();
        
        // Realizar alguna operación con la conexión aquí (opcional)
        
        // Cerrar la conexión
        conexionBD.cerrarConexion();
    }
    
}
