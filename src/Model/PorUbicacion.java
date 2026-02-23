package Model;

import java.util.ArrayList;
import java.util.List;

public class PorUbicacion implements IStrategyBuscadorPartidos {

    @Override
    public List<Partido> buscarPartidos(List<Partido> partidos, Jugador jugador) {
        System.out.println("[BuscadorPartidos] Buscando partidos de " + jugador.getDeporteFavorito()
                + " por ubicacion (CP: " + jugador.getCodigoPostal() + ")...");
        List<Partido> resultado = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getEstado() instanceof NecesitamosJugadores && !p.getJugadores().contains(jugador)
                    && p.getDeporte().getClass().equals(jugador.getDeporteFavorito().getClass())
                    && p.getCodigoPostal() != null
                    && p.getCodigoPostal().equals(jugador.getCodigoPostal())) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
