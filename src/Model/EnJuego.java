package Model;

public class EnJuego implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        partido.setEstado(new Finalizado());
    }

    @Override
    public String toString() {
        return "En Juego";
    }
}
