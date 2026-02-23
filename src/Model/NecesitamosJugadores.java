package Model;

public class NecesitamosJugadores implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        if (partido.getJugadores().size() < partido.getNJugadores()) {
            System.out.println("No se puede avanzar: faltan jugadores ("
                    + partido.getJugadores().size() + "/" + partido.getNJugadores() + ").");
            return;
        }
        partido.setEstado(new PartidoArmado());
    }

    @Override
    public String toString() {
        return "Necesitamos Jugadores";
    }
}
