package Model;

public class Intermedio implements IEstadoNivelDeporte {

    @Override
    public void subirNivelJugador(Jugador jugador) {
        jugador.setNivelDeporteFavorito(new Avanzado());
    }

    @Override
    public void bajarNivelJugador(Jugador jugador) {
        jugador.setNivelDeporteFavorito(new Principiante());
    }

    @Override
    public void subirNivelPartido(Partido partido) {
        partido.setNivelPartido(new Avanzado());
    }

    @Override
    public void bajarNivelPartido(Partido partido) {
        partido.setNivelPartido(new Principiante());
    }
}
