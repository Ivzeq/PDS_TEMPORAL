package Model;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class PorNivel implements IStrategyBuscadorPartidos{

    public List<Partido> buscarPartido(Jugador jugador, List<Partido> partidosAbiertos) {
        List <Partido> partidosFiltro = new ArrayList<>();

        for (Partido partido : partidosAbiertos) {
            if (jugador.getNivelDeporteFavorito()
                    .equals(partido.getNivelJugadores())) {
                partidosFiltro.add(partido);
            }
        }
        return partidosFiltro;
    }

}