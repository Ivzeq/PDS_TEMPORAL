package Model;

import java.util.*;

public class BuscadorPartidos {

    private IStrategyBuscadorPartidos strategy;

    private List<Partido> partidosAbiertos;

    public BuscadorPartidos(IStrategyBuscadorPartidos strategy) {
        this.strategy = strategy;
        this.partidosAbiertos = new ArrayList<>();
    }

    public void agregarPartidoAbierto(Partido partido) {
        partidosAbiertos.add(partido);
    }

    public void removerPartidoAbierto (Partido partido) {
        partidosAbiertos.remove(partido);
    }

    public List<Partido> getPartidosAbiertos() {
        return partidosAbiertos;
    }

    public List<Partido> buscarPartido(Jugador jugador) {
        return strategy.buscarPartido(jugador, getPartidosAbiertos());
    }

    public void cambiarStrategy(IStrategyBuscadorPartidos strategy) {
        this.strategy = strategy;
    }

}