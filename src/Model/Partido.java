package Model;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Partido {

    /**
     * Default constructor
     */
    public Partido() {
    }

    /**
     * 
     */
    private AbstractDeporte deporte;

    /**
     * 
     */
    private int nJugadores;

    /**
     * 
     */
    private AbstractNivelDeporte nivelJugadores;

    /**
     * 
     */
    private int duracion;

    /**
     * 
     */
    private String ubicacion;

    /**
     * 
     */
    private Date horario;

    /**
     * 
     */
    private EstadoPartido estado;

    /**
     * 
     */
    private List<Jugador> jugadores;

    /**
     * 
     */
    private Jugador organizador;

    /**
     * 
     */
    private List<IObserverPartido> observers;












    /**
     * @return
     */
    public void avanzarEstado() {
        // TODO implement here
    }

    /**
     * @param jugador
     * @return
     */
    public void agregarJugador( Jugador jugador) {
        // TODO implement here
    }

    /**
     * @param jugador
     * @return
     */
    public void removerJugador( Jugador jugador) {
        // TODO implement here
    }

}