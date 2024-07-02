package interfaces;

import entidades.Chat;
import entidades.Mensaje;
import excepciones.PersistenciaException;
import java.util.List;

import org.bson.types.ObjectId;

public interface IChatDAO {

    public List<Chat> getAllChats() throws PersistenciaException;

    public Boolean createChat(Chat chat) throws PersistenciaException;

    public List<Chat> getChatsByUsuario(ObjectId usuario) throws PersistenciaException;

    public Chat getChatById(ObjectId chat) throws PersistenciaException;

    public void updateChat(Chat chat, Mensaje mensaje) throws PersistenciaException;

    public void deleteChat(ObjectId chat) throws PersistenciaException;
}
