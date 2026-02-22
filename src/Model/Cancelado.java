package Model;

/**
 * 
 */
public class Cancelado implements IEstadoPartido {
    
    @Override
    public void avanzarEstado(Partido partido) {
        partido.setEstado(new Cancelado());
    }
}