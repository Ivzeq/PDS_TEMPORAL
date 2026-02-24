package Model;

public class Voley extends AbstractDeporte {
    
    private static final int DURACION_PARTIDO_VOLEY = 60;
    private static final int CANTIDAD_JUGADORES_VOLEY = 12;

    public Voley() {
        super(DURACION_PARTIDO_VOLEY, CANTIDAD_JUGADORES_VOLEY);
    }

    public Voley(int duracionPartido, int cantidadJugadores) {
        super(duracionPartido, cantidadJugadores);
    }
}
