package Model;

public interface IEstadoNivelDeporte {
    void subirNivelJugador(Jugador jugador);

    void bajarNivelJugador(Jugador jugador);

    void subirNivelPartido(Partido partido);

    void bajarNivelPartido(Partido partido);
}
