package Model;

public class PartidoArmado implements IEstadoPartido {

    @Override
    public void avanzarEstado(Partido partido) {
        if (partido.todosConfirmados()) {
            partido.setEstado(new Confirmado());
        } else {
            int confirmados = partido.getJugadoresConfirmados().size();
            int total = partido.getNJugadores();
            System.out.println("No se puede avanzar: no todos confirmaron asistencia ("
                    + confirmados + "/" + total + " confirmados).");
        }
    }

    @Override
    public String toString() {
        return "Partido Armado";
    }
}
