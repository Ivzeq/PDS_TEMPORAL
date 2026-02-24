package Repository;

import Model.Partido;
import java.util.List;

public interface IPartidoRepository {
    Partido save(Partido partido);
    List<Partido> findAll();
    void delete(Partido partido);
}
