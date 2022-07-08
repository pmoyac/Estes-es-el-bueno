package Interfaces;

import java.util.ArrayList;
import org.bson.types.ObjectId;

public interface CRUD<T> {

    public abstract void agregar(T entidad);

    public abstract void EliminarPorId(ObjectId id);

    public abstract void actualizar(T entidad, ObjectId id);

    public abstract T buscarPorId(ObjectId id);

    public abstract ArrayList<T> buscarTodas();
}
