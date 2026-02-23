package Model;

import java.util.List;

public interface IStrategyBuscadorPartidos {

    List<Partido> buscarPartidos(List<Partido> partidos, Jugador jugador);
}
