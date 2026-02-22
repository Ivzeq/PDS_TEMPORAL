package Model;

/**
 * 
 */
public interface IEstadoPartido {
    void avanzarEstado(Partido partido);

    void cancelarPartido(Partido partido);
}