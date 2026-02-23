package Model;

import java.util.ArrayList;
import java.util.List;

public class PorHistorial implements IStrategyBuscadorPartidos {

    @Override
    public List<Partido> buscarPartido(Jugador jugador, List<Partido> partidosAbiertos) {
        List <Partido> partidosFiltro = new ArrayList<>();
        int minimoCoincidencias = 3;

        for (Partido partido : partidosAbiertos) {
            int contadorCoincidencias = 0;
            for (Jugador jugadorPartidosAbiertos : partido.getJugadores()) {
                if(jugador.equals(jugadorPartidosAbiertos)){
                    contadorCoincidencias ++;
                }
            }
            if (contadorCoincidencias >= minimoCoincidencias) {
                partidosFiltro.add(partido);
            }
        }
        return partidosFiltro;
    }
}
