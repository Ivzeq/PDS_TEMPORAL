package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partido {

    private final AbstractDeporte deporte;
    private final int nJugadores;
    private AbstractNivelDeporte nivelJugadores;
    private final int duracion;
    private String codigoPostal;
    private Date horario;
    private IEstadoPartido estado;
    private List<Jugador> jugadores;
    private final Jugador organizador;
    private List<IObserverPartido> observers;

    public Partido(AbstractDeporte deporte, AbstractNivelDeporte nivelDeporte, String codigoPostal, Date horario, Jugador organizador) {
        this.deporte = deporte;
        this.nJugadores = deporte.getCantidadJugadores();
        this.nivelJugadores = nivelDeporte;
        this.duracion = deporte.getDuracionPartido();
        this.codigoPostal = codigoPostal;
        this.horario = horario;
        this.organizador = organizador;
        this.estado = new NecesitamosJugadores();
        this.jugadores = new ArrayList<>();
        this.observers = new ArrayList<>();

        agregarJugador(organizador);
    }

    // Setters

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

    public void setEstado(IEstadoPartido estado) {
        this.estado = estado;
    }

    public void setNivelJugadores (AbstractNivelDeporte nivelJugadores) {
        this.nivelJugadores = nivelJugadores;
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

    public AbstractNivelDeporte getNivelJugadores() {
        return nivelJugadores;
    }
}
