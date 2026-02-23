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

    public Partido crearPartido(AbstractDeporte deporte, int nJugadores, AbstractNivelDeporte nivelJugadores,
            int duracion, String ubicacion, String codigoPostal, Date horario, Jugador organizador) {
        Partido partido = new Partido();
        partido.setDeporte(deporte);
        partido.setNJugadores(nJugadores);
        partido.setNivelJugadores(nivelJugadores);
        partido.setDuracion(duracion);
        partido.setUbicacion(ubicacion);
        partido.setCodigoPostal(codigoPostal);
        partido.setHorario(horario);
        partido.setOrganizador(organizador);
        partido.setEstado(new NecesitamosJugadores());
        partido.agregarJugador(organizador);
        partidos.add(partido);
        return partido;
    }

    public boolean agregarJugador(Partido partido, Jugador jugador) {
        boolean agregado = partido.agregarJugador(jugador);
        if (agregado) {
            notificacionController.notificarJugador(jugador, "Te uniste al partido de " + partido.getDeporte());
        }
        return agregado;
    }

    public void removerJugador(Partido partido, Jugador jugador) {
        partido.removerJugador(jugador);
        notificacionController.notificarJugador(jugador, "Fuiste removido del partido de " + partido.getDeporte());
    }

    public void avanzarEstado(Partido partido) {
        IEstadoPartido estadoAnterior = partido.getEstado();
        partido.avanzarEstado();
        if (!estadoAnterior.equals(partido.getEstado())) {
            notificacionController.notificarTodos(partido,
                    "El partido de " + partido.getDeporte() + " cambio a estado: " + partido.getEstado());
        }
    }

    public void cancelarPartido(Partido partido) {
        partido.setEstado(new Cancelado());
        notificacionController.notificarTodos(partido,
                "El partido de " + partido.getDeporte() + " en " + partido.getUbicacion() + " fue cancelado.");
    }

    public List<Partido> buscarPartidos(Jugador jugador) {
        return buscadorPartidos.buscarPartidos(partidos, jugador);
    }

    public List<Partido> buscarTodosPartidos(Jugador jugador) {
        List<Partido> resultado = new ArrayList<>();
        for (Partido p : partidos) {
            if (p.getEstado() instanceof NecesitamosJugadores && !p.getJugadores().contains(jugador)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public void cambiarStrategyBuscador(IStrategyBuscadorPartidos strategy) {
        buscadorPartidos.cambiarStrategy(strategy);
    }

    public String getNombreStrategyBuscador() {
        return buscadorPartidos.getStrategy().getClass().getSimpleName();
    }

    public List<Partido> getPartidos() {
        return partidos;
    }
}
