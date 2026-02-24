package Repository;

import Model.Partido;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPartidoRepository implements IPartidoRepository {

    private List<Partido> storage = new ArrayList<>();

    @Override
    public Partido save(Partido partido) {
        storage.add(partido);
        return partido;
    }

    @Override
    public List<Partido> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public void delete(Partido partido) {
        storage.remove(partido);
    }
}
