package Controller;

import Model.*;
import java.time.LocalDateTime;
import java.util.List;
import Repository.IPartidoRepository;
import Repository.InMemoryPartidoRepository;
import service.PartidoService;

public class PartidoController {

    private PartidoService partidoService;

    public PartidoController(BuscadorPartidos buscadorPartidos, NotificacionController notificacionController) {
        this(buscadorPartidos, notificacionController, new InMemoryPartidoRepository());
    }

    public PartidoController(BuscadorPartidos buscadorPartidos, NotificacionController notificacionController,
                             IPartidoRepository partidoRepository) {
        this.partidoService = new PartidoService(partidoRepository, buscadorPartidos, notificacionController);
    }

    public Partido crearPartido(AbstractDeporte deporte, int nJugadores, AbstractNivelDeporte nivelJugadores,
                               int duracion, String ubicacion, String codigoPostal, LocalDateTime horario,
                               Jugador organizador) {
        return partidoService.crearPartido(deporte, nJugadores, nivelJugadores, duracion, ubicacion, codigoPostal, horario, organizador);
    }

    public boolean agregarJugador(Partido partido, Jugador jugador) {
        return partidoService.agregarJugador(partido, jugador);
    }

    public void removerJugador(Partido partido, Jugador jugador) {
        partidoService.removerJugador(partido, jugador);
    }

    public void avanzarEstado(Partido partido) {
        partidoService.avanzarEstado(partido);
    }

    public boolean confirmarAsistencia(Partido partido, Jugador jugador) {
        return partidoService.confirmarAsistencia(partido, jugador);
    }

    public void rechazarAsistencia(Partido partido, Jugador jugador) {
        partidoService.rechazarAsistencia(partido, jugador);
    }

    public void cancelarPartido(Partido partido) {
        partidoService.cancelarPartido(partido);
    }

    public List<Partido> buscarPartidos(Jugador jugador) {
        return partidoService.buscarPartidos(jugador);
    }

    public List<Partido> buscarTodosPartidos(Jugador jugador) {
        return partidoService.buscarTodosPartidos(jugador);
    }

    public void cambiarStrategyBuscador(IStrategyBuscadorPartidos strategy) {
        partidoService.cambiarStrategyBuscador(strategy);
    }

    public String getNombreStrategyBuscador() {
        return partidoService.getNombreStrategyBuscador();
    }

    public void actualizarEstadosPorTiempo() {
        partidoService.actualizarEstadosPorTiempo();
    }

    public List<Partido> getPartidos() {
        return partidoService.getPartidos();
    }
}
