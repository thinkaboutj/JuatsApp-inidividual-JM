/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Jesus Medina (╹ڡ╹ ) ID:00000247527
 */
public class ConexionBD {

    private final MongoDatabase DATABASE;

    public ConexionBD() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        ConnectionString cadenaConexion = new ConnectionString("mongodb://localhost/27017");

        MongoClientSettings clientsSettings = MongoClientSettings.builder().applyConnectionString(cadenaConexion).codecRegistry(codecRegistry).build();

        MongoClient dbServer = MongoClients.create(clientsSettings);

        this.DATABASE = dbServer.getDatabase("juatsappdb");
    }

    public MongoDatabase getBaseDatos() {
        return DATABASE;
    }
}
