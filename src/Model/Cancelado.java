package Model;

/**
 * 
 */
public class Cancelado implements IEstadoPartido {
    
    @Override
    public void avanzarEstado(Partido partido) {
        System.out.println("Partido en estado final Cancelado");
    }

    @Override
    public void cancelarPartido(Partido partido) {
        System.out.println("Partido ya Cancelado");
    }
}