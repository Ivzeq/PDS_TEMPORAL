package Model;

public abstract class AbstractNivelDeporte {

    protected Jugador jugador;

    public AbstractNivelDeporte(Jugador jugador) {
        this.jugador = jugador;
    }

    public void subirNivel() {
    }

    public void bajarNivel() {
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
