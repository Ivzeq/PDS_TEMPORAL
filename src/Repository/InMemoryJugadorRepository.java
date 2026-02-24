package Repository;

import Model.Jugador;

import java.util.ArrayList;
import java.util.List;

public class InMemoryJugadorRepository implements IJugadorRepository {

    private List<Jugador> storage = new ArrayList<>();

    @Override
    public Jugador save(Jugador jugador) {
        storage.add(jugador);
        return jugador;
    }

    @Override
    public Jugador findByUsername(String username) {
        if (username == null) return null;
        for (Jugador j : storage) {
            if (username.equals(j.getUsername())) return j;
        }
        return null;
    }

    @Override
    public List<Jugador> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public void delete(Jugador jugador) {
        storage.remove(jugador);
    }
}
