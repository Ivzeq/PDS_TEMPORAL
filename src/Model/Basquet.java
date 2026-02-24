package Model;

public class Basquet extends AbstractDeporte {
    
    private static final int DURACION_PARTIDO_BASQUET = 48;
    private static final int CANTIDAD_JUGADORES_BASQUET = 10;

    public Basquet() {
        super(DURACION_PARTIDO_BASQUET, CANTIDAD_JUGADORES_BASQUET);
    }

    public Basquet(int duracionPartido, int cantidadJugadores) {
        super(duracionPartido, cantidadJugadores);
    }
}
