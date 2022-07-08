package implementacion;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import dominio.Mensaje;
import dominio.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import org.bson.types.ObjectId;

public class MensajeDAO extends BaseDAO {

     private MongoCollection<Mensaje> coleccionMensaje;
    
    public MensajeDAO() {
        this.coleccionMensaje = this.basedatos.getCollection("Mensaje", Mensaje.class);
    }
    
    public void agregar(Mensaje mensaje) {
        try {
            coleccionMensaje.insertOne(mensaje);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    
    public ArrayList<Mensaje> buscarTodas() {
        FindIterable<Mensaje> UsuariosEncontrados = coleccionMensaje.find();
        ArrayList<Mensaje> MensajesEncontradosArrayList = new ArrayList<>();
        coleccionMensaje.find().into(MensajesEncontradosArrayList);
        return MensajesEncontradosArrayList;
    }

    public Mensaje buscarPorId(ObjectId id) {
        Mensaje mensaje = coleccionMensaje.find(eq("_id", id)).first();
        return mensaje;
    }
   
}
