package Model;

import java.util.ArrayList;
import java.util.List;

public class BuscadorPartidos {

    private List<Partido> partidosAbiertos;
    private IStrategyBuscadorPartidos strategy;

    public BuscadorPartidos() {
        this.partidosAbiertos = new ArrayList<>();
        this.strategy = new PorUbicacion();
    }

    public void agregarPartidoAbierto(Partido partido) {
        partidosAbiertos.add(partido);
    }

    public void removerPartidoAbierto (Partido partido) {
        partidosAbiertos.remove(partido);
    }

    public List<Partido> buscarPartido(Jugador jugador) {
        return strategy.buscarPartido(jugador, getPartidosAbiertos());
    }

    public void cambiarStrategy(IStrategyBuscadorPartidos strategy) {
        this.strategy = strategy;
    }

    public List<Partido> getPartidosAbiertos() {
        return partidosAbiertos;
    }

    public void setPartidosAbiertos(List<Partido> partidosAbiertos) {
        this.partidosAbiertos = partidosAbiertos;
    }
}
