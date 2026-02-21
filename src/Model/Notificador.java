package Model;

import java.util.List;

public class Notificador implements IObserverPartido {

    private IStrategyNotificador strategy;

    public Notificador(IStrategyNotificador strategy) {
        this.strategy = strategy;
    }

    public void enviarNotificacion(Notificacion notificacion) {
        strategy.enviarNotificacion(notificacion);
    }

    public void cambiarStrategy(IStrategyNotificador strategy) {
        this.strategy = strategy;
    }

    public IStrategyNotificador getStrategy() {
        return strategy;
    }

    @Override
    public void update(Partido partido) {
        List<Jugador> jugadores = partido.getJugadores();
        String estadoActual = partido.getEstado().toString();
        String deporte = partido.getDeporte().toString();

        String mensaje = "El partido de " + deporte + " ha cambiado a estado: " + estadoActual;

        for (Jugador jugador : jugadores) {
            Notificacion notificacion = new Notificacion(mensaje, jugador);
            enviarNotificacion(notificacion);
        }
    }
}
