package Model;

public class Cancelado implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        System.out.println("El partido fue cancelado, no se puede avanzar.");
    }

    @Override
    public void cancelarPartido(Partido partido) {
        System.out.println("Partido ya Cancelado");
    }

    @Override
    public String toString() {
        return "Cancelado";
    }
}
