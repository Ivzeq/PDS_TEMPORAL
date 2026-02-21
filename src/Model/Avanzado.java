package Model;

public class Avanzado extends AbstractNivelDeporte {

    public Avanzado(Jugador jugador) {
        super(jugador);
    }

    @Override
    public void bajarNivel() {
        jugador.setNivelDeporteFavorito(new Intermedio(jugador));
    }
}
