package Repository;

import Model.Jugador;
import java.util.List;

public interface IJugadorRepository {
    Jugador save(Jugador jugador);
    Jugador findByUsername(String username);
    List<Jugador> findAll();
    void delete(Jugador jugador);
}
