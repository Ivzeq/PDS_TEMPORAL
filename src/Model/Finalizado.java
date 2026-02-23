package Model;

/**
 * 
 */
public class Finalizado implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        System.out.println("Partido en estado final Finalizado");
    }

    @Override
    public void cancelarPartido(Partido partido) {
        partido.setEstado(new Cancelado());
    }
}