package Controller;

import Model.IStrategyNotificador;
import Model.Jugador;
import Model.Notificacion;
import Model.Notificador;
import Model.Partido;

import java.util.ArrayList;
import java.util.List;

public class NotificacionController {

    private List<Notificador> notificadores;

    public NotificacionController() {
        this.notificadores = new ArrayList<>();
    }

    public void agregarNotificador(Notificador notificador) {
        notificadores.add(notificador);
    }

    public void removerNotificador(Notificador notificador) {
        notificadores.remove(notificador);
    }

    public void notificarJugador(Jugador jugador, String mensaje) {
        Notificacion notificacion = new Notificacion(mensaje, jugador);
        if (jugador.getNotificador() != null) {
            jugador.getNotificador().enviarNotificacion(notificacion);
        } else {
            for (Notificador notificador : notificadores) {
                notificador.enviarNotificacion(notificacion);
            }
        }
    }

    public void notificarTodos(Partido partido, String mensaje) {
        for (Jugador jugador : partido.getJugadores()) {
            notificarJugador(jugador, mensaje);
        }
    }

    public void cambiarStrategyNotificador(Notificador notificador, IStrategyNotificador strategy) {
        notificador.cambiarStrategy(strategy);
    }
}
