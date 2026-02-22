package Model;

/**
 * 
 */
public class NecesitamosJugadores implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        partido.setEstado(new PartidoArmado());
    }
}