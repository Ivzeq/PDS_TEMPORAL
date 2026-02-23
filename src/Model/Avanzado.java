package Model;

public class Avanzado implements IEstadoNivelDeporte {

    @Override
    public void subirNivelJugador(Jugador jugador) {
        System.out.println("Jugador en nivel maximo");
    }

    @Override
    public void bajarNivelJugador(Jugador jugador) {
        jugador.setNivelDeporteFavorito(new Intermedio());
    }

    @Override
    public void subirNivelPartido(Partido partido) {
        System.out.println("Partido en nivel maximo");
    }

    @Override
    public void bajarNivelPartido(Partido partido) {
        partido.setNivelPartido(new Intermedio());
    }
}
