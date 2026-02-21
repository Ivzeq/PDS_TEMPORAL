package Model;

public abstract class AbstractDeporte {

    private int duracionPartido;
    private int cantidadJugadores;

    public AbstractDeporte(int duracionPartido, int cantidadJugadores) {
        this.duracionPartido = duracionPartido;
        this.cantidadJugadores = cantidadJugadores;
    }

    public int getDuracionPartido() {
        return duracionPartido;
    }

    public void setDuracionPartido(int duracionPartido) {
        this.duracionPartido = duracionPartido;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
