package Model;

public class Futbol extends AbstractDeporte {
    
    private static final int DURACION_PARTIDO_FUTBOL = 60;
    private static final int CANTIDAD_JUGADORES_FUTBOL = 10;

    public Futbol() {
        super(DURACION_PARTIDO_FUTBOL, CANTIDAD_JUGADORES_FUTBOL);
    }

    public Futbol(int duracionPartido, int cantidadJugadores) {
        super(duracionPartido, cantidadJugadores);
    }
}
