package Model;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class PorUbicacion implements IStrategyBuscadorPartidos{

    public List<Partido> buscarPartido(Jugador jugador, List<Partido> partidosAbiertos) {
        List <Partido> partidosFiltro = new ArrayList<>();

        for (Partido partido : partidosAbiertos) {
            if (jugador.getCodigoPostal()
                    .equals(partido.getCodigoPostal())) {
                partidosFiltro.add(partido);
            }
        }
        return partidosFiltro;
    }

}