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

    public Partido buscarPartido() {
        return strategy.buscarPartido();
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
