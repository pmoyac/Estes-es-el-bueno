package implementacion;

import Interfaces.CRUD;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import dominio.Usuario;
import java.time.LocalDate;
import org.bson.types.ObjectId;

public class UsuarioDAO extends BaseDAO implements CRUD<Usuario> {

    private MongoCollection<Usuario> coleccionUsuarios;

    public UsuarioDAO() {
        this.coleccionUsuarios = this.basedatos.getCollection("Usuarios", Usuario.class);
    }

    @Override
    public void agregar(Usuario usuario) {
        try {
            coleccionUsuarios.insertOne(usuario);
        } catch (Error e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    @Override
    public void EliminarPorId(ObjectId id) {
        coleccionUsuarios.deleteOne(eq("_id", id));
    }

    @Override
    public void actualizar(Usuario usuario, ObjectId id) {
        String correo_electronico = usuario.getCorreo();
        

        String sexo = usuario.getSexo();
        LocalDate fechaNacimiento = usuario.getFechaNacimiento();
        String Contraseña = usuario.getContra();
        


        coleccionUsuarios.updateOne(
                eq("_id", id),
                combine(set("correo_electronico", correo_electronico),
                        set("contraseña", Contraseña),
                        set("fechaeDeNacimiento", fechaNacimiento),
                        set("sexo", sexo)

                     //   set("posts", posts)  // DATOS A ACTUALIZAR  
                        )
        );
        
    }

    @Override
    public Usuario buscarPorId(ObjectId id) {
        Usuario usuario = coleccionUsuarios.find(eq("_id", id)).first();
        return usuario;
    }

    @Override
    public ArrayList<Usuario> buscarTodas() {
        FindIterable<Usuario> UsuariosEncontrados = coleccionUsuarios.find();
        ArrayList<Usuario> UsuariosEncontradosArrayList = new ArrayList<>();
        coleccionUsuarios.find().into(UsuariosEncontradosArrayList);
        return UsuariosEncontradosArrayList;
    }

}
