package Model;

import java.util.ArrayList;
import java.util.List;

public class PorUbicacion implements IStrategyBuscadorPartidos {

    @Override
    public List<Partido> buscarPartido(Jugador jugador, List<Partido> partidosAbiertos) {
        List <Partido> partidosFiltro = new ArrayList<>();

        for (Partido partido : partidosAbiertos) {
            if (jugador.getCodigoPostal()
                    .equals(partido.getUbicacion())) {
                partidosFiltro.add(partido);
            }
        }
        return partidosFiltro;
    }
}
