package Model;

public class Principiante extends AbstractNivelDeporte {

    private int maxPartidos;

    public Principiante(Jugador jugador) {
        super(jugador);
        this.maxPartidos = 10;
    }

    public Principiante(Jugador jugador, int maxPartidos) {
        super(jugador);
        this.maxPartidos = maxPartidos;
    }

    @Override
    public void subirNivel() {
        jugador.setNivelDeporteFavorito(new Intermedio(jugador));
    }

    public int getMaxPartidos() {
        return maxPartidos;
    }

    public void setMaxPartidos(int maxPartidos) {
        this.maxPartidos = maxPartidos;
    }
}
