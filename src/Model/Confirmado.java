package Model;

public class Confirmado implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        partido.setEstado(new EnJuego());
    }

    @Override
    public String toString() {
        return "Confirmado";
    }
}
