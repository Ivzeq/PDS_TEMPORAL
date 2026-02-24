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

    public boolean confirmarAsistencia(Partido partido, Jugador jugador) {
        boolean ok = partido.confirmarAsistencia(jugador);
        if (ok) {
            notificacionController.notificarTodos(partido,
                    jugador.getUsername() + " confirmo asistencia al partido de " + partido.getDeporte() + ".");
            if (partido.todosConfirmados() && partido.getEstado() instanceof PartidoArmado) {
                partido.setEstado(new Confirmado());
                notificacionController.notificarTodos(partido,
                        "Todos confirmaron! El partido de " + partido.getDeporte() + " esta confirmado.");
            }
        }
        return ok;
    }

    public void rechazarAsistencia(Partido partido, Jugador jugador) {
        partido.removerJugador(jugador);
        partido.setEstado(new NecesitamosJugadores());
        notificacionController.notificarTodos(partido,
                jugador.getUsername() + " no podra asistir al partido de " + partido.getDeporte()
                        + ". Se buscan jugadores nuevamente.");
        notificacionController.notificarJugador(jugador,
                "Fuiste removido del partido de " + partido.getDeporte() + " por no confirmar asistencia.");
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

    public void actualizarEstadosPorTiempo() {
        Date ahora = new Date();
        for (Partido p : partidos) {
            if (p.getHorario() == null) continue;

            if (p.getEstado() instanceof PartidoArmado && !p.todosConfirmados()) {
                long unaHoraAntes = p.getHorario().getTime() - (60 * 60 * 1000);
                if (ahora.getTime() >= unaHoraAntes) {
                    p.setEstado(new Cancelado());
                    notificacionController.notificarTodos(p,
                            "El partido de " + p.getDeporte() + " en " + p.getUbicacion()
                                    + " fue cancelado: no todos confirmaron asistencia a menos de 1 hora del inicio.");
                }
            } else if (p.getEstado() instanceof Confirmado && !ahora.before(p.getHorario())) {
                p.setEstado(new EnJuego());
                notificacionController.notificarTodos(p,
                        "El partido de " + p.getDeporte() + " en " + p.getUbicacion() + " ha comenzado!");
            } else if (p.getEstado() instanceof EnJuego) {
                long finMs = p.getHorario().getTime() + (long) p.getDuracion() * 60 * 1000;
                if (ahora.getTime() >= finMs) {
                    p.setEstado(new Finalizado());
                    notificacionController.notificarTodos(p,
                            "El partido de " + p.getDeporte() + " en " + p.getUbicacion() + " ha finalizado!");
                }
            }
        }
    }

    public List<Partido> getPartidos() {
        return partidos;
    }
}
