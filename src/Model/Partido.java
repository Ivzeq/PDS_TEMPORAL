package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Partido {

    private AbstractDeporte deporte;
    private int nJugadores;
    private AbstractNivelDeporte nivelJugadores;
    private int duracion;
    private String ubicacion;
    private String codigoPostal;
    private LocalDateTime horario;
    private IEstadoPartido estado;
    private List<Jugador> jugadores;
    private Jugador organizador;
    private List<IObserverPartido> observers;
    private Set<Jugador> jugadoresConfirmados;

    public Partido() {
        this.jugadores = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.jugadoresConfirmados = new HashSet<>();
        this.estado = new NecesitamosJugadores();
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
            // Registrar el notificador del jugador como observer
            if (jugador.getNotificador() != null) {
                agregarObserver(jugador.getNotificador());
            }
            if (jugadores.size() == nJugadores && estado instanceof NecesitamosJugadores) {
                avanzarEstado();
            }
            return true;
        }
        return false;
    }

    public void removerJugador(Jugador jugador) {
        jugadores.remove(jugador);
        jugadoresConfirmados.remove(jugador);
    }

    public boolean confirmarAsistencia(Jugador jugador) {
        if (jugadores.contains(jugador)) {
            jugadoresConfirmados.add(jugador);
            return true;
        }
        return false;
    }

    public boolean todosConfirmados() {
        return !jugadores.isEmpty()
                && jugadores.size() == nJugadores
                && jugadoresConfirmados.containsAll(jugadores);
    }

    public boolean isConfirmado(Jugador jugador) {
        return jugadoresConfirmados.contains(jugador);
    }

    public Set<Jugador> getJugadoresConfirmados() {
        return jugadoresConfirmados;
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

    public LocalDateTime getHorario() {
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

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public void setEstado(IEstadoPartido estado) {
        this.estado = estado;
    }

    public void setOrganizador(Jugador organizador) {
        this.organizador = organizador;
    }
}
