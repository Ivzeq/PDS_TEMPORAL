package Model;

public class Futbol extends AbstractDeporte {

    public Futbol() {
        super(60, 10);
    }

    public Futbol(int duracionPartido, int cantidadJugadores) {
        super(duracionPartido, cantidadJugadores);
    }
}
