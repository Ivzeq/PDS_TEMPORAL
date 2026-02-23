package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partido {

    private final AbstractDeporte deporte;
    private final int nJugadores;
    private IEstadoNivelDeporte nivelPartido;
    private final int duracion;
    private String codigoPostal;
    private Date horario;
    private IEstadoPartido estado;
    private List<Jugador> jugadores;
    private final Jugador organizador;
    private List<IObserverPartido> observers;

    public Partido(AbstractDeporte deporte, IEstadoNivelDeporte nivelDeporte, String codigoPostal, Date horario, Jugador organizador) {
        this.deporte = deporte;
        this.nJugadores = deporte.getCantidadJugadores();
        this.nivelPartido = nivelDeporte;
        this.duracion = deporte.getDuracionPartido();
        this.codigoPostal = codigoPostal;
        this.horario = horario;
        this.organizador = organizador;
        this.estado = new NecesitamosJugadores();
        this.jugadores = new ArrayList<>();
        this.observers = new ArrayList<>();

        agregarJugador(organizador);
    }



    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void removerJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }

    public void agregarObserver(IObserverPartido observer) {
        observers.add(observer);
    }

    public void removerObservers(IObserverPartido observer) {
        observers.remove(observer);
    }

    public void avanzarEstado() {
        estado.avanzarEstado(this);
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

    // Setters

    public void setEstado(IEstadoPartido estado) {
        this.estado = estado;
    }

    public void setNivelPartido (IEstadoNivelDeporte nivelPartido) {
        this.nivelPartido = nivelPartido;
    }

    public void setCodigoPostal (String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
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

    public String getCodigoPostal() {
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
}
