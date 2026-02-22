package Model;

/**
 * 
 */
public class PartidoArmado implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        partido.setEstado(new Confirmado());
    }
}