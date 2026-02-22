package Model;

import java.io.*;
import java.util.*;

/**
 * 
 */
public interface IStrategyBuscadorPartidos {

    List<Partido> buscarPartido(Jugador jugador, List<Partido> partidosAbiertos);
}