package Model;

public class Intermedio extends AbstractNivelDeporte {

    public Intermedio(Jugador jugador) {
        super(jugador);
    }

    @Override
    public void subirNivel() {
        jugador.setNivelDeporteFavorito(new Avanzado(jugador));
    }

    @Override
    public void bajarNivel() {
        jugador.setNivelDeporteFavorito(new Principiante(jugador));
    }
}
