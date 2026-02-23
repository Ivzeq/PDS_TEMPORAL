package Model;

import java.util.List;

public interface IStrategyBuscadorPartidos {

    List<Partido> buscarPartido(Jugador jugador, List<Partido> partidosAbiertos);
}
