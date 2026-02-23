package Model;

import java.util.ArrayList;
import java.util.List;

public class PorNivel implements IStrategyBuscadorPartidos {

    public List<Partido> buscarPartido(Jugador jugador, List<Partido> partidosAbiertos) {
        List <Partido> partidosFiltro = new ArrayList<>();

        for (Partido partido : partidosAbiertos) {
            if (jugador.getNivelDeporteFavorito()
                    .equals(partido.getNivelPartido())) {
                partidosFiltro.add(partido);
            }
        }
        return partidosFiltro;
    }
}
