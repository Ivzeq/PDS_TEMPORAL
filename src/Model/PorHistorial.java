package Model;

import java.util.ArrayList;
import java.util.List;

public class PorHistorial implements IStrategyBuscadorPartidos {

    @Override
    public List<Partido> buscarPartidos(List<Partido> partidos, Jugador jugador) {
        System.out.println("[BuscadorPartidos] Buscando partidos por historial (deporte: " + jugador.getDeporteFavorito() + ")...");
        List<Partido> resultado = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getEstado() instanceof NecesitamosJugadores && !p.getJugadores().contains(jugador)
                    && p.getDeporte().getClass().equals(jugador.getDeporteFavorito().getClass())) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
