package Model;

import java.util.ArrayList;
import java.util.List;

public class PorNivel implements IStrategyBuscadorPartidos {

    @Override
    public List<Partido> buscarPartidos(List<Partido> partidos, Jugador jugador) {
        System.out.println("[BuscadorPartidos] Buscando partidos de " + jugador.getDeporteFavorito()
                + " por nivel (" + jugador.getNivelDeporteFavorito() + ")...");
        List<Partido> resultado = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getEstado() instanceof NecesitamosJugadores && !p.getJugadores().contains(jugador)
                    && p.getDeporte().getClass().equals(jugador.getDeporteFavorito().getClass())) {
                if (p.getNivelJugadores() == null
                        || p.getNivelJugadores().getClass().equals(jugador.getNivelDeporteFavorito().getClass())) {
                    resultado.add(p);
                }
            }
        }
        return resultado;
    }
}
