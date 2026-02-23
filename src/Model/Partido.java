package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partido {

    private AbstractDeporte deporte;
    private int nJugadores;
    private IEstadoNivelDeporte nivelPartido;
    private int duracion;
    private String codigoPostal;
    private Date horario;
    private IEstadoPartido estado;
    private List<Jugador> jugadores;
    private Jugador organizador;
    private List<IObserverPartido> observers;

    public Partido() {
        this.deporte = deporte;
        this.nJugadores = deporte.getCantidadJugadores();
        this.nivelPartido = nivelPartido;
        this.duracion = deporte.getDuracionPartido();
        this.codigoPostal = codigoPostal;
        this.horario = horario;
        this.organizador = organizador;
        this.estado = new NecesitamosJugadores();
        this.jugadores = new ArrayList<>();
        this.observers = new ArrayList<>();

        agregarJugador(organizador);
    }

    public void avanzarEstado() {
        estado.avanzarEstado(this);
        notificarObservers();
    }

    public void agregarJugador(Jugador jugador) {
        if (jugadores.size() < nJugadores) {
            jugadores.add(jugador);
            if (jugadores.size() == nJugadores && estado instanceof NecesitamosJugadores) {
                avanzarEstado();
            }
        }
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


    public void cancelarPartido() {
        estado.cancelarPartido(this);
    }

    public void subirNivel() {
        nivelPartido.subirNivelPartido(this);
    }

    public void bajarNivel() {
        nivelPartido.bajarNivelPartido(this);
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
        return codigoPostal;
    }

    public Date getHorario() {
        return horario;
    }

    public int getDuracion() {
        return duracion;
    }

    public IEstadoNivelDeporte getNivelPartido() {
        return nivelPartido;
    }

    // Setters

    public void setDeporte(AbstractDeporte deporte) {
        this.deporte = deporte;
    }

    public void setNJugadores(int nJugadores) {
        this.nJugadores = nJugadores;
    }

    public void setNivelPartido(IEstadoNivelDeporte nivelPartido) {
        this.nivelPartido = nivelPartido;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setUbicacion(String ubicacion) {
        this.codigoPostal = ubicacion;
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
