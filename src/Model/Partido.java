package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partido {

    private AbstractDeporte deporte;
    private int nJugadores;
    private AbstractNivelDeporte nivelJugadores;
    private int duracion;
    private String ubicacion;
    private Date horario;
    private IEstadoPartido estado;
    private List<Jugador> jugadores;
    private Jugador organizador;
    private List<IObserverPartido> observers;

    public Partido() {
        this.jugadores = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void avanzarEstado() {
        // TODO implement here
    }

    public void agregarJugador(Jugador jugador) {
        // TODO implement here
    }

    public void removerJugador(Jugador jugador) {
        // TODO implement here
    }

    // Getters needed by notification system

    public AbstractDeporte getDeporte() {
        return deporte;
    }

    public IEstadoPartido getEstado() {
        return estado;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador getOrganizador() {
        return organizador;
    }

    public List<IObserverPartido> getObservers() {
        return observers;
    }

    public int getNJugadores() {
        return nJugadores;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Date getHorario() {
        return horario;
    }

    public int getDuracion() {
        return duracion;
    }

    public AbstractNivelDeporte getNivelJugadores() {
        return nivelJugadores;
    }
}
