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
    private String codigoPostal;
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
        estado.avanzarEstado(this);
        notificarObservers();
    }

    public boolean agregarJugador(Jugador jugador) {
        if (jugadores.contains(jugador)) {
            return false;
        }
        if (jugadores.size() < nJugadores) {
            jugadores.add(jugador);
            if (jugadores.size() == nJugadores && estado instanceof NecesitamosJugadores) {
                avanzarEstado();
            }
            return true;
        }
        return false;
    }

    public void removerJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }

    private void notificarObservers() {
        for (IObserverPartido observer : observers) {
            observer.update(this);
        }
    }

    public void agregarObserver(IObserverPartido observer) {
        observers.add(observer);
    }

    public void removerObserver(IObserverPartido observer) {
        observers.remove(observer);
    }

    // Getters

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

    public String getCodigoPostal() {
        return codigoPostal;
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

    // Setters

    public void setDeporte(AbstractDeporte deporte) {
        this.deporte = deporte;
    }

    public void setNJugadores(int nJugadores) {
        this.nJugadores = nJugadores;
    }

    public void setNivelJugadores(AbstractNivelDeporte nivelJugadores) {
        this.nivelJugadores = nivelJugadores;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public void setEstado(IEstadoPartido estado) {
        this.estado = estado;
    }

    public void setOrganizador(Jugador organizador) {
        this.organizador = organizador;
    }
}
