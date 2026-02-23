package Model;

public class Finalizado implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        System.out.println("El partido ya finalizo, no se puede avanzar.");
    }

    @Override
    public String toString() {
        return "Finalizado";
    }
}
