package Model;

import java.util.List;

public class BuscadorPartidos {

    private IStrategyBuscadorPartidos strategy;

    public BuscadorPartidos() {
        this.strategy = new PorUbicacion();
    }

    public List<Partido> buscarPartidos(List<Partido> partidos, Jugador jugador) {
        return strategy.buscarPartidos(partidos, jugador);
    }

    public void cambiarStrategy(IStrategyBuscadorPartidos strategy) {
        this.strategy = strategy;
    }

    public IStrategyBuscadorPartidos getStrategy() {
        return strategy;
    }
}
