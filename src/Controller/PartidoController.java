package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartidoController {

    private List<Partido> partidos;
    private BuscadorPartidos buscadorPartidos;
    private NotificacionController notificacionController;

    public PartidoController(BuscadorPartidos buscadorPartidos, NotificacionController notificacionController) {
        this.partidos = new ArrayList<>();
        this.buscadorPartidos = buscadorPartidos;
        this.notificacionController = notificacionController;
    }

    public Partido crearPartido(AbstractDeporte deporte, int nJugadores, IEstadoNivelDeporte nivelJugadores,
                                int duracion, String ubicacion, Date horario, Jugador organizador) {
        Partido partido = new Partido();
        partido.setDeporte(deporte);
        partido.setNJugadores(nJugadores);
        partido.setNivelPartido(nivelJugadores);
        partido.setDuracion(duracion);
        partido.setUbicacion(ubicacion);
        partido.setHorario(horario);
        partido.setOrganizador(organizador);
        partido.setEstado(new NecesitamosJugadores());
        partido.agregarJugador(organizador);
        partidos.add(partido);
        return partido;
    }

    public void agregarJugador(Partido partido, Jugador jugador) {
        partido.agregarJugador(jugador);
        notificacionController.notificarJugador(jugador, "Te uniste al partido de " + partido.getDeporte());
    }

    public void removerJugador(Partido partido, Jugador jugador) {
        partido.removerJugador(jugador);
        notificacionController.notificarJugador(jugador, "Fuiste removido del partido de " + partido.getDeporte());
    }

    public void avanzarEstado(Partido partido) {
        partido.avanzarEstado();
        notificacionController.notificarTodos(partido,
                "El partido de " + partido.getDeporte() + " cambio a estado: " + partido.getEstado());
    }

    public List<Partido> buscarPartido(Jugador jugador) {
        return buscadorPartidos.buscarPartido(jugador);
    }

    public void cambiarStrategyBuscador(IStrategyBuscadorPartidos strategy) {
        buscadorPartidos.cambiarStrategy(strategy);
    }

    public List<Partido> getPartidos() {
        return partidos;
    }
}
