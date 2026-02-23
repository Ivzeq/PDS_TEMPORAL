package Model;

public class Cancelado implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        System.out.println("El partido fue cancelado, no se puede avanzar.");
    }

    @Override
    public String toString() {
        return "Cancelado";
    }
}
