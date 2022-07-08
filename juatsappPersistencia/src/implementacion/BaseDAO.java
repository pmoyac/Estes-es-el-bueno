package implementacion;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dominio.Usuario;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class BaseDAO {

    protected CodecRegistry pojoCodecRegistry;
    protected MongoClient conexion;
    protected MongoDatabase basedatos;
    protected MongoCollection<Usuario> coleccionUsuarios;

    public BaseDAO() {
        this.pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        this.conexion = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        this.basedatos = conexion.getDatabase("juatsappdb");
        this.coleccionUsuarios = this.basedatos.getCollection("Usuarios", Usuario.class);
    }
}
