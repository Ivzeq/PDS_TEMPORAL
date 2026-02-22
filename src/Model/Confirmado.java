package Model;

/**
 * 
 */
public class Confirmado implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        partido.setEstado(new EnJuego());
    }

    @Override
    public void cancelarPartido(Partido partido) {
        partido.setEstado(new Cancelado());
    }
}